package org.dakralex.pricevista.parser.billa

import kotlinx.serialization.SerialName

/**
 * Enumeration of the different seals of quality (ger. "Gütesiegel")
 */
enum class BillaJsonBadges {
    /**
     * In-store label for new products
     */
    @SerialName("eg-neu")
    EG_NEW,

    /**
     * Austrian "AMA Gütesiegel" organic food certification
     */
    @SerialName("gs-amabio")
    GS_AMA_BIO,

    /**
     * French/Swiss Appellation d’Origine Contrôlée certification
     */
    @SerialName("gs-aoc")
    GS_AOC,

    /**
     * French/Swiss Appellation d’Origine Protégée certification
     *
     * @see GS_PDO
     */
    @SerialName("gs-aop")
    GS_AOP,

    /**
     * Austrian "ARGE Heumilch" certification
     */
    @SerialName("gs-argeheu")
    GS_ARGE_HEUMILCH,

    /**
     * International Aquaculture Stewardship Council certification
     */
    @SerialName("gs-asc")
    GS_ASC,

    /**
     * Generic organic food certification
     */
    @SerialName("gs-bio")
    GS_BIO,

    /**
     * International Demeter (organic food) certification
     */
    @SerialName("gs-demeter")
    GS_DEMETER,

    /**
     * Italian Denominazione di origine controllata (DOC) certification
     */
    @SerialName("gs-doc")
    GS_DOC,

    /**
     * Italian Denominazione d’Origine Protetta (DOP) certification
     *
     * @see GS_PDO
     */
    @SerialName("gs-dop")
    GS_DOP,

    /**
     * European Union's organic food certification
     */
    @SerialName("gs-eubio")
    GS_EU_BIO,

    /**
     * International Forest Stewardship Council certification
     */
    @SerialName("gs-fsc")
    GS_FSC,

    /**
     * International Fairtrade certification
     */
    @SerialName("gs-ft")
    GS_FT,

    /**
     * Generic certification for genetic engineering
     *
     * TODO Free of genetic engineering or not?
     */
    @SerialName("gs-gentech")
    GS_GENTECH,

    /**
     * European protected geographical indication (PGI) ceritification
     *
     * - German: geschützte geografische Angabe (ggA)
     * - French: indication géographique protégée (IGP)
     * - Italian: indicazione geografica protetta (IGP)
     * - Portuguese: indicação geográfica protegida (IGP)
     * - Spanish: indicación geográfica protegida (IGP)
     */
    @SerialName("gs-gga")
    GS_PGI,

    /**
     * European traditional speciality guaranteed (TSG) certification
     *
     * - German: garantiert traditionelle Spezialität (gtS)
     * - French: spécialité traditionnelle garantie (STG)
     * - Italian: specialità tradizionale garantita (STG)
     * - Spanish: especialidad tradicional garantizada (ETG)
     *
     * Note: The Billa API has probably an spelling error ("gta" instead of "gts")
     */
    @SerialName("gs-gta")
    GS_TSG,

    /**
     * European protected designation of origin (PDO) certification
     *
     * - German: geschützte Ursprungsbezeichnung (gU)
     * - French: Appellation d’Origine Contrôlée (AOP)
     * - Italian: Denominazione d’Origine Protetta (DOP)
     * - Portuguese: Denominação de Origem Protegida (DOP)
     * - Spanish: denominación de origen protegida (DOP)
     */
    @SerialName("gs-gu")
    GS_PDO,

    /** International Marine Stewardship Council certification **/
    @SerialName("gs-msc")
    GS_MSC,

    /**
     * Proprietary PRO PLAN certification for quality pet food
     */
    @SerialName("gs-proplan")
    GS_PROPLAN,

    /**
     * International Rainforest Alliance certification
     */
    @SerialName("gs-rainfor")
    GS_RAINFOR,

    /**
     * International UTZ Certified certification
     *
     * Note: Merged with Rainforest Alliance.
     */
    @SerialName("gs-utz")
    GS_UTZ,

    /**
     * Generic label for vegan products
     */
    @SerialName("gs-vegan")
    GS_VEGAN,

    /**
     * Generic label for Austrian products
     */
    @SerialName("homecountry")
    GS_AUT,

    /**
     * Generic label for products that should be stored cool
     */
    @SerialName("kuehlung")
    GS_COOLED,

    /**
     * Generic label for organic products
     */
    @SerialName("pp-bio")
    PP_BIO,

    /**
     * Generic label for gluten free products
     */
    @SerialName("pp-glutenfr")
    PP_GLUTENFREI,

    /**
     * Generic label for lactose-free products
     */
    @SerialName("pp-lakto")
    PP_LAKTOSEFREI,

    /**
     * Generic label for vegan products
     */
    @SerialName("pp-vegan")
    PP_VEGAN,

    /**
     * Generic label for vegetarian products
     */
    @SerialName("pp-veget")
    PP_VEGETARIAN,

    /**
     * Generic label for products that should be stored deep frozen
     */
    @SerialName("tiefkuehlung")
    PP_DEEP_FROZEN,
}