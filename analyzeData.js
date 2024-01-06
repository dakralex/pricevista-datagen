const fs = require("fs");
const NodeCache = require("node-cache");

const anCache = new NodeCache({
  useClones: false,
});

const dataDir = "./data";

/**
 * Helper function to cache larger requests with NodeCache
 *
 * @param func {function} cacheable function (won't bind "this")
 * @param args {*} variable amount of arguments for func parameter
 * @returns {*}
 */
const cacheCall = (func, ...args) => {
  const cacheKey = `${func.name}-${args.join("-")}`;
  const cached = anCache.get(cacheKey);

  if (cached) {
    return cached;
  }

  const res = func(...args);
  anCache.set(cacheKey, res);

  return res;
};

/**
 * Helper method to get a proper overview of an object's type.
 *
 * @param obj {object} any object
 * @returns {string}   the object's type as a string, including all standard
 *                     typeof values + constructor names, array (with value
 *                     types), integer and float
 */
const getType = (obj) => {
  if (Array.isArray(obj)) {
    const elementTypes = obj.map(e => getType(e)).filter(unique);

    return `array<[${elementTypes.join(", ")}]>`;
  }

  if (Number.isInteger(obj)) {
    return "integer";
  } else if (typeof obj === "number") {
    return "float";
  }

  return ({}).toString.call(obj).match(/\s([a-zA-Z]+)/)[1].toLowerCase();
};

// Dynamic import functions

const getImportFileList = (name) => {
  const dirFiles = fs.readdirSync(dataDir);

  return dirFiles.filter(filename => filename.startsWith(`${name}-`) &&
      filename.endsWith(".json")).map(filename => `${dataDir}/${filename}`);
};

const getFullImport = (name) => {
  const allFilesContent = getImportFileList(name).
      map(f => JSON.parse(fs.readFileSync(f).toString()));

  return allFilesContent.concat().flat();
};

const getLatestImport = (name) => {
  const latestFile = getImportFileList(name).sort().slice(-1);

  return latestFile.map(f => JSON.parse(fs.readFileSync(f).toString()));
};

// Transform functions for retailer's API output

const transformEntries = {
  billa: (obj) => obj,
  hofer: (obj) => obj,
  spar: (obj) => obj.flat().map(s => s.masterValues),
};

// Entries functions for retailer's API output

const getAllEntries = (name) => {
  const data = cacheCall(getFullImport, name);

  return transformEntries[name](data);
};

const getLatestEntries = (name) => {
  const data = cacheCall(getLatestImport, name);

  return transformEntries[name](data);
};

// Helper functions for filtering

const unique = (val, idx, arr) => arr.indexOf(val) === idx;

// Helper functions for object traversal

const getDescendant = (obj, path = null) => {
  if (path == null) {
    return obj;
  }

  return path.split(".").reduce((curr, prop) => curr?.[prop], obj);
};

// Helper functions for overviewing objects

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param path {String?} dot-separated path to desired property
 * @returns {*}
 */
const getValidEntries = (obj, path = null) => {
  return obj.map(entry => getDescendant(entry, path)).filter(entry => entry);
};

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param path {String?} dot-separated path to desired property
 * @returns {*}
 */
const getAllProps = (obj, path = null) => {
  return cacheCall(getValidEntries, obj, path).
      flatMap(entry => Object.keys(entry));
};

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param path {String?} dot-separated path to desired property
 * @returns {*}
 */
const getUniqueProps = (obj, path = null) => {
  return cacheCall(getAllProps, obj, path).filter(unique).sort();
};

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param path {String?} dot-separated path to desired property
 * @returns {*}
 */
const getPropTypes = (obj, path = null) => {
  const uniqueProps = cacheCall(getUniqueProps, obj, path);
  const propTypesMap = uniqueProps.map(prop => [
    String(prop),
    obj.map(entry => getType(getDescendant(entry, path)?.[prop])).
        filter(unique),
  ]);

  return Object.fromEntries(propTypesMap);
};

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param path {String?} dot-separated path to desired property
 * @param peek {Number} amount of entries to show
 * @returns {*}
 */
const getUniqueValues = (obj, path = null, peek = -1) => {
  const uniqueProps = cacheCall(getUniqueProps, obj, path);

  const propValuesMap = uniqueProps.map(prop => [
    String(prop),
    obj.slice(0, peek).
        map(entry => getDescendant(entry, path)?.[prop]).
        filter(unique),
  ]);

  return Object.fromEntries(propValuesMap);
};

/**
 *
 * @param obj {Array} Object to be evaluated
 * @param prop {String} property to evaluate
 * @param peek {Number} amount of entries to show
 * @returns {{[p: string]: any}}
 */
const getUniquePropValues = (obj, prop, peek = -1) => {
  const propValues = obj.slice(0, peek).
      map(entry => entry?.[prop]).
      flat().
      filter(unique);

  return Object.fromEntries([
    [
      String(prop),
      propValues,
    ]
  ]);
};