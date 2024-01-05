package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Currency

//@formatter:off
enum class ECurrency(val currency: Currency) {
    ALL(Currency(  8, "ALL", "008", 2, "L",      "q",   "Albanian lek")),
    DZD(Currency( 12, "DZD", "012", 2, "دج",     "",    "Algerian dinar")),
    ARS(Currency( 32, "ARS", "032", 2, "$",      "",    "Argentine peso")),
    AUD(Currency( 36, "AUD", "036", 2, "$",      "c",   "Australian dollar")),
    BSD(Currency( 44, "BSD", "044", 2, "$",      "",    "Bahamian dollar")),
    BHD(Currency( 48, "BHD", "048", 3, ".د.ب",   "",    "Bahraini dinar")),
    BDT(Currency( 50, "BDT", "050", 2, "৳",      "",    "Bangladeshi taka")),
    AMD(Currency( 51, "AMD", "051", 2, "֏",      "",    "Armenian dram")),
    BBD(Currency( 52, "BBD", "052", 2, "Bds$",   "",    "Barbados dollar")),
    BMD(Currency( 60, "BMD", "060", 2, "$",      "",    "Bermudian dollar")),
    BTN(Currency( 64, "BTN", "064", 2, "Nu.",    "Ch.", "Bhutanese ngultrum")),
    BOB(Currency( 68, "BOB", "068", 2, "Bs.",    "",    "Boliviano")),
    BWP(Currency( 72, "BWP", "072", 2, "P",      "",    "Botswana pula")),
    BZD(Currency( 84, "BZD", "084", 2, "$",      "",    "Belize dollar")),
    SBD(Currency( 90, "SBD", "090", 2, "S$",     "",    "Solomon Islands dollar")),
    BND(Currency( 96, "BND", "096", 2, "B$",     "",    "Brunei dollar")),
    MMK(Currency(104, "MMK", "104", 2, "K",      "",    "Myanmar kyat")),
    BIF(Currency(108, "BIF", "108", 0, "FBu",    "",    "Burundian franc")),
    KHR(Currency(116, "KHR", "116", 2, "៛",      "",    "Cambodian riel")),
    CAD(Currency(124, "CAD", "124", 2, "$",      "¢",   "Canadian dollar")),
    CVE(Currency(132, "CVE", "132", 2, "Esc",    "",    "Cape Verdean escudo")),
    KYD(Currency(136, "KYD", "136", 2, "$",      "",    "Cayman Islands dollar")),
    LKR(Currency(144, "LKR", "144", 2, "₨",      "",    "Sri Lankan rupee")),
    CLP(Currency(152, "CLP", "152", 0, "$",      "",    "Chilean peso")),
    CNY(Currency(156, "CNY", "156", 2, "¥",      "",    "Renminbi (Chinese) yuan")),
    COP(Currency(170, "COP", "170", 2, "$",      "",    "Colombian peso")),
    KMF(Currency(174, "KMF", "174", 0, "₣",      "",    "Comoro franc")),
    CRC(Currency(188, "CRC", "188", 2, "₡",      "",    "Costa Rican colon")),
    HRK(Currency(191, "HRK", "191", 2, "kn",     "lp",  "Croatian kuna")),
    CUP(Currency(192, "CUP", "192", 2, "₱",      "",    "Cuban peso")),
    CZK(Currency(203, "CZK", "203", 2, "Kč",     "h",   "Czech koruna")),
    DKK(Currency(208, "DKK", "208", 2, "kr",     "",    "Danish krone")),
    DOP(Currency(214, "DOP", "214", 2, "RD$",    "",    "Dominican peso")),
    SVC(Currency(222, "SVC", "222", 2, "¤",      "",    "Salvadoran colón")),
    ETB(Currency(230, "ETB", "230", 2, "Br",     "",    "Ethiopian birr")),
    ERN(Currency(232, "ERN", "232", 2, "Nfk",    "",    "Eritrean nakfa")),
    FKP(Currency(238, "FKP", "238", 2, "£",      "",    "Falkland Islands pound")),
    FJD(Currency(242, "FJD", "242", 2, "FJ$",    "",    "Fiji dollar")),
    DJF(Currency(262, "DJF", "262", 0, "₣",      "",    "Djiboutian franc")),
    GMD(Currency(270, "GMD", "270", 2, "D",      "",    "Gambian dalasi")),
    GIP(Currency(292, "GIP", "292", 2, "£",      "",    "Gibraltar pound")),
    GTQ(Currency(320, "GTQ", "320", 2, "Q",      "",    "Guatemalan quetzal")),
    GNF(Currency(324, "GNF", "324", 0, "₣",      "",    "Guinean franc")),
    GYD(Currency(328, "GYD", "328", 2, "G$",     "",    "Guyanese dollar")),
    HTG(Currency(332, "HTG", "332", 2, "G",      "",    "Haitian gourde")),
    HNL(Currency(340, "HNL", "340", 2, "L",      "",    "Honduran lempira")),
    HKD(Currency(344, "HKD", "344", 2, "HK$",    "",    "Hong Kong dollar")),
    HUF(Currency(348, "HUF", "348", 2, "Ft",     "",    "Hungarian forint")),
    ISK(Currency(352, "ISK", "352", 0, "kr",     "",    "Icelandic króna")),
    INR(Currency(356, "INR", "356", 2, "₹",      "",    "Indian rupee")),
    IDR(Currency(360, "IDR", "360", 2, "Rp",     "",    "Indonesian rupiah")),
    IRR(Currency(364, "IRR", "364", 2, "﷼",     "",    "Iranian rial")),
    IQD(Currency(368, "IQD", "368", 3, "د.ع",    "",    "Iraqi dinar")),
    ILS(Currency(376, "ILS", "376", 2, "₪",      "",    "Israeli new shekel")),
    JMD(Currency(388, "JMD", "388", 2, "$",      "",    "Jamaican dollar")),
    JPY(Currency(392, "JPY", "392", 0, "¥",      "",    "Japanese yen")),
    KZT(Currency(398, "KZT", "398", 2, "₸",      "",    "Kazakhstani tenge")),
    JOD(Currency(400, "JOD", "400", 3, "JD",     "",    "Jordanian dinar")),
    KES(Currency(404, "KES", "404", 2, "Ksh",    "",    "Kenyan shilling")),
    KPW(Currency(408, "KPW", "408", 2, "₩",      "",    "North Korean won")),
    KRW(Currency(410, "KRW", "410", 0, "₩",      "",    "South Korean won")),
    KWD(Currency(414, "KWD", "414", 3, "د.ك",    "",    "Kuwaiti dinar")),
    KGS(Currency(417, "KGS", "417", 2, "С̲",     "",    "Kyrgyzstani som")),
    LAK(Currency(418, "LAK", "418", 2, "₭",      "",    "Lao kip")),
    LBP(Currency(422, "LBP", "422", 2, "LL",     "",    "Lebanese pound")),
    LSL(Currency(426, "LSL", "426", 2, "M",      "",    "Lesotho loti")),
    LRD(Currency(430, "LRD", "430", 2, "L$",     "",    "Liberian dollar")),
    LYD(Currency(434, "LYD", "434", 3, "ل.د",    "",    "Libyan dinar")),
    MOP(Currency(446, "MOP", "446", 2, "MOP$",   "",    "Macanese pataca")),
    MWK(Currency(454, "MWK", "454", 2, "K",      "",    "Malawian kwacha")),
    MYR(Currency(458, "MYR", "458", 2, "RM",     "",    "Malaysian ringgit")),
    MVR(Currency(462, "MVR", "462", 2, "Rf.",    "",    "Maldivian rufiyaa")),
    MUR(Currency(480, "MUR", "480", 2, "₨",      "",    "Mauritian rupee")),
    MXN(Currency(484, "MXN", "484", 2, "$",      "¢",   "Mexican peso")),
    MNT(Currency(496, "MNT", "496", 2, "₮",      "",    "Mongolian tögrög")),
    MDL(Currency(498, "MDL", "498", 2, "¤",      "",    "Moldovan leu")),
    MAD(Currency(504, "MAD", "504", 2, "د.م.",   "",    "Moroccan dirham")),
    OMR(Currency(512, "OMR", "512", 3, "ر.ع.",   "",    "Omani rial")),
    NAD(Currency(516, "NAD", "516", 2, "N$",     "NA",  "Namibian dollar")),
    NPR(Currency(524, "NPR", "524", 2, "₨",      "",    "Nepalese rupee")),
    ANG(Currency(532, "ANG", "532", 2, "ƒ",      "",    "Netherlands Antillean guilder")),
    AWG(Currency(533, "AWG", "533", 2, "ƒ",      "",    "Aruban florin")),
    VUV(Currency(548, "VUV", "548", 0, "VT",     "",    "Vanuatu vatu")),
    NZD(Currency(554, "NZD", "554", 2, "$",      "c",   "New Zealand dollar")),
    NIO(Currency(558, "NIO", "558", 2, "C$",     "",    "Nicaraguan córdoba")),
    NGN(Currency(566, "NGN", "566", 2, "₦",      "",    "Nigerian naira")),
    NOK(Currency(578, "NOK", "578", 2, "kr",     "",    "Norwegian krone")),
    PKR(Currency(586, "PKR", "586", 2, "₨",      "",    "Pakistani rupee")),
    PAB(Currency(590, "PAB", "590", 2, "B/.",    "",    "Panamanian balboa")),
    PGK(Currency(598, "PGK", "598", 2, "K",      "",    "Papua New Guinean kina")),
    PYG(Currency(600, "PYG", "600", 0, "₲",      "",    "Paraguayan guaraní")),
    PEN(Currency(604, "PEN", "604", 2, "S/",     "",    "Peruvian sol")),
    PHP(Currency(608, "PHP", "608", 2, "₱",      "",    "Philippine peso")),
    QAR(Currency(634, "QAR", "634", 2, "ر.ق",    "",    "Qatari riyal")),
    RUB(Currency(643, "RUB", "643", 2, "₽",      "",    "Russian ruble")),
    RWF(Currency(646, "RWF", "646", 0, "FRw",    "",    "Rwandan franc")),
    SHP(Currency(654, "SHP", "654", 2, "£",      "",    "Saint Helena pound")),
    SAR(Currency(682, "SAR", "682", 2, "ر.س",    "",    "Saudi riyal")),
    SCR(Currency(690, "SCR", "690", 2, "SRe",    "",    "Seychelles rupee")),
    SLL(Currency(694, "SLL", "694", 2, "Le",     "",    "Sierra Leonean leone")),
    SGD(Currency(702, "SGD", "702", 2, "S$",     "",    "Singapore dollar")),
    VND(Currency(704, "VND", "704", 0, "₫",      "",    "Vietnamese đồng")),
    SOS(Currency(706, "SOS", "706", 2, "Sh.So.", "",    "Somali shilling")),
    ZAR(Currency(710, "ZAR", "710", 2, "R",      "",    "South African rand")),
    SSP(Currency(728, "SSP", "728", 2, "¤",      "",    "South Sudanese pound")),
    SZL(Currency(748, "SZL", "748", 2, "E",      "",    "Swazi lilangeni")),
    SEK(Currency(752, "SEK", "752", 2, "kr",     "",    "Swedish krona/kronor")),
    CHF(Currency(756, "CHF", "756", 2, "₣",      "",    "Swiss franc")),
    SYP(Currency(760, "SYP", "760", 2, "LS",     "",    "Syrian pound")),
    THB(Currency(764, "THB", "764", 2, "฿",      "",    "Thai baht")),
    TOP(Currency(776, "TOP", "776", 2, "T$",     "",    "Tongan paʻanga")),
    TTD(Currency(780, "TTD", "780", 2, "$",      "",    "Trinidad and Tobago dollar")),
    AED(Currency(784, "AED", "784", 2, "د.إ",    "",    "United Arab Emirates dirham")),
    TND(Currency(788, "TND", "788", 3, "د.ت",    "",    "Tunisian dinar")),
    UGX(Currency(800, "UGX", "800", 0, "USh",    "",    "Ugandan shilling")),
    MKD(Currency(807, "MKD", "807", 2, "ден",    "",    "Macedonian denar")),
    EGP(Currency(818, "EGP", "818", 2, "£",      "pt",  "Egyptian pound")),
    GBP(Currency(826, "GBP", "826", 2, "£",      "p",   "Pound sterling")),
    TZS(Currency(834, "TZS", "834", 2, "Tsh",    "",    "Tanzanian shilling")),
    USD(Currency(840, "USD", "840", 2, "$",      "¢",   "United States dollar")),
    UYU(Currency(858, "UYU", "858", 2, "\$U",    "",    "Uruguayan peso")),
    UZS(Currency(860, "UZS", "860", 2, "¤",      "",    "Uzbekistan som")),
    WST(Currency(882, "WST", "882", 2, "WS$",    "",    "Samoan tala")),
    YER(Currency(886, "YER", "886", 2, "ر.ي",    "",    "Yemeni rial")),
    TWD(Currency(901, "TWD", "901", 2, "NT$",    "",    "New Taiwan dollar")),
    SLE(Currency(925, "SLE", "925", 2, "Le",     "",    "Sierra Leonean leone")),
    VED(Currency(926, "VED", "926", 2, "Bs.",    "",    "Venezuelan bolívar soberano")),
    UYW(Currency(927, "UYW", "927", 4, "¤",      "",    "Unidad previsional")),
    VES(Currency(928, "VES", "928", 2, "Bs.",    "",    "Venezuelan bolívar soberano")),
    MRU(Currency(929, "MRU", "929", 2, "UM",     "",    "Mauritanian ouguiya")),
    STN(Currency(930, "STN", "930", 2, "Db",     "",    "São Tomé and Príncipe dobra")),
    CUC(Currency(931, "CUC", "931", 2, "$",      "",    "Cuban convertible peso")),
    ZWL(Currency(932, "ZWL", "932", 2, "¤",      "",    "Zimbabwean dollar")),
    BYN(Currency(933, "BYN", "933", 2, "Br",     "",    "Belarusian ruble")),
    TMT(Currency(934, "TMT", "934", 2, "¤",      "",    "Turkmenistan manat")),
    GHS(Currency(936, "GHS", "936", 2, "GH₵",    "",    "Ghanaian cedi")),
    SDG(Currency(938, "SDG", "938", 2, "¤",      "",    "Sudanese pound")),
    UYI(Currency(940, "UYI", "940", 0, "¤",      "",    "Uruguay Peso en Unidades Indexadas (URUIURUI)")),
    RSD(Currency(941, "RSD", "941", 2, "дин",    "",    "Serbian dinar")),
    MZN(Currency(943, "MZN", "943", 2, "MT",     "",    "Mozambican metical")),
    AZN(Currency(944, "AZN", "944", 2, "₼",      "",    "Azerbaijani manat")),
    RON(Currency(946, "RON", "946", 2, "L",      "",    "Romanian leu")),
    CHE(Currency(947, "CHE", "947", 2, "¤",      "",    "WIR Euro")),
    CHW(Currency(948, "CHW", "948", 2, "¤",      "",    "WIR Franc")),
    TRY(Currency(949, "TRY", "949", 2, "₺",      "",    "Turkish lira")),
    XAF(Currency(950, "XAF", "950", 0, "FCFA",   "",    "CFA franc BEAC")),
    XCD(Currency(951, "XCD", "951", 2, "$",      "",    "East Caribbean dollar")),
    XOF(Currency(952, "XOF", "952", 0, "CFA",    "",    "CFA franc BCEAO")),
    XPF(Currency(953, "XPF", "953", 0, "₣",      "",    "CFP franc (franc Pacifique)")),
    XBA(Currency(955, "XBA", "955", 0, "¤",      "",    "European Composite Unit (EURCO)")),
    XBB(Currency(956, "XBB", "956", 0, "¤",      "",    "European Monetary Unit (E.M.U.-6)")),
    XBC(Currency(957, "XBC", "957", 0, "¤",      "",    "European Unit of Account 9 (E.U.A.-9)")),
    XBD(Currency(958, "XBD", "958", 0, "¤",      "",    "European Unit of Account 17 (E.U.A.-17)")),
    XAU(Currency(959, "XAU", "959", 0, "¤",      "",    "Gold (one troy ounce)")),
    XDR(Currency(960, "XDR", "960", 0, "SDR",    "",    "Special drawing rights")),
    XAG(Currency(961, "XAG", "961", 0, "¤",      "",    "Silver (one troy ounce)")),
    XPT(Currency(962, "XPT", "962", 0, "¤",      "",    "Platinum (one troy ounce)")),
    XTS(Currency(963, "XTS", "963", 0, "¤",      "",    "Code reserved for testing")),
    XPD(Currency(964, "XPD", "964", 0, "¤",      "",    "Palladium (one troy ounce)")),
    XUA(Currency(965, "XUA", "965", 0, "¤",      "",    "ADB Unit of Account")),
    ZMW(Currency(967, "ZMW", "967", 2, "K",      "",    "Zambian kwacha")),
    SRD(Currency(968, "SRD", "968", 2, "$",      "",    "Surinamese dollar")),
    MGA(Currency(969, "MGA", "969", 2, "Ar",     "",    "Malagasy ariary")),
    COU(Currency(970, "COU", "970", 2, "¤",      "",    "Unidad de Valor Real (UVR)")),
    AFN(Currency(971, "AFN", "971", 2, "؋",      "",    "Afghan afghani")),
    TJS(Currency(972, "TJS", "972", 2, "¤",      "",    "Tajikistani somoni")),
    AOA(Currency(973, "AOA", "973", 2, "Kz",     "",    "Angolan kwanza")),
    BGN(Currency(975, "BGN", "975", 2, "лв.",    "",    "Bulgarian lev")),
    CDF(Currency(976, "CDF", "976", 2, "₣",      "",    "Congolese franc")),
    BAM(Currency(977, "BAM", "977", 2, "KM",     "",    "Bosnia and Herzegovina convertible mark")),
    EUR(Currency(978, "EUR", "978", 2, "€",      "",    "Euro")),
    MXV(Currency(979, "MXV", "979", 2, "¤",      "",    "Mexican Unidad de Inversion (UDI)")),
    UAH(Currency(980, "UAH", "980", 2, "₴",      "",    "Ukrainian hryvnia")),
    GEL(Currency(981, "GEL", "981", 2, "ლ",      "",    "Georgian lari")),
    BOV(Currency(984, "BOV", "984", 2, "¤",      "",    "Bolivian Mvdol")),
    PLN(Currency(985, "PLN", "985", 2, "zł",     "gr",  "Polish złoty")),
    BRL(Currency(986, "BRL", "986", 2, "R$",     "",    "Brazilian real")),
    CLF(Currency(990, "CLF", "990", 4, "¤",      "",    "Unidad de Fomento")),
    XSU(Currency(994, "XSU", "994", 0, "¤",      "",    "SUCRE")),
    USN(Currency(997, "USN", "997", 2, "$",      "¢",   "United States dollar (next day)")),
    XXX(Currency(999, "XXX", "999", 0, "¤",      "",    "No currency")),
}
//@formatter:on
