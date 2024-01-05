package org.dakralex.pricevista.entities.data

import org.dakralex.pricevista.entities.Country

//@formatter:off
enum class ECountry(val country: Country) {
    AF(Country(  4, "AF", "AFG", "004", "Afghanistan")),
    AL(Country(  8, "AL", "ALB", "008", "Albania")),
    AQ(Country( 10, "AQ", "ATA", "010", "Antarctica")),
    DZ(Country( 12, "DZ", "DZA", "012", "Algeria")),
    AS(Country( 16, "AS", "ASM", "016", "American Samoa")),
    AD(Country( 20, "AD", "AND", "020", "Andorra")),
    AO(Country( 24, "AO", "AGO", "024", "Angola")),
    AG(Country( 28, "AG", "ATG", "028", "Antigua and Barbuda")),
    AZ(Country( 31, "AZ", "AZE", "031", "Azerbaijan")),
    AR(Country( 32, "AR", "ARG", "032", "Argentina")),
    AU(Country( 36, "AU", "AUS", "036", "Australia")),
    AT(Country( 40, "AT", "AUT", "040", "Austria")),
    BS(Country( 44, "BS", "BHS", "044", "Bahamas")),
    BH(Country( 48, "BH", "BHR", "048", "Bahrain")),
    BD(Country( 50, "BD", "BGD", "050", "Bangladesh")),
    AM(Country( 51, "AM", "ARM", "051", "Armenia")),
    BB(Country( 52, "BB", "BRB", "052", "Barbados")),
    BE(Country( 56, "BE", "BEL", "056", "Belgium")),
    BM(Country( 60, "BM", "BMU", "060", "Bermuda")),
    BT(Country( 64, "BT", "BTN", "064", "Bhutan")),
    BO(Country( 68, "BO", "BOL", "068", "Bolivia")),
    BA(Country( 70, "BA", "BIH", "070", "Bosnia and Herzegovina")),
    BW(Country( 72, "BW", "BWA", "072", "Botswana")),
    BV(Country( 74, "BV", "BVT", "074", "Bouvet Island")),
    BR(Country( 76, "BR", "BRA", "076", "Brazil")),
    BZ(Country( 84, "BZ", "BLZ", "084", "Belize")),
    IO(Country( 86, "IO", "IOT", "086", "British Indian Ocean Territory")),
    SB(Country( 90, "SB", "SLB", "090", "Solomon Islands")),
    VG(Country( 92, "VG", "VGB", "092", "British Virgin Islands")),
    BN(Country( 96, "BN", "BRN", "096", "Brunei Darussalam")),
    BG(Country(100, "BG", "BGR", "100", "Bulgaria")),
    MM(Country(104, "MM", "MMR", "104", "Myanmar")),
    BI(Country(108, "BI", "BDI", "108", "Burundi")),
    BY(Country(112, "BY", "BLR", "112", "Belarus")),
    KH(Country(116, "KH", "KHM", "116", "Cambodia")),
    CM(Country(120, "CM", "CMR", "120", "Cameroon")),
    CA(Country(124, "CA", "CAN", "124", "Canada")),
    CV(Country(132, "CV", "CPV", "132", "Cabo Verde")),
    KY(Country(136, "KY", "CYM", "136", "Cayman Islands")),
    CF(Country(140, "CF", "CAF", "140", "Central African Republic")),
    LK(Country(144, "LK", "LKA", "144", "Sri Lanka")),
    TD(Country(148, "TD", "TCD", "148", "Chad")),
    CL(Country(152, "CL", "CHL", "152", "Chile")),
    CN(Country(156, "CN", "CHN", "156", "China")),
    TW(Country(158, "TW", "TWN", "158", "Taiwan")),
    CX(Country(162, "CX", "CXR", "162", "Christmas Island")),
    CC(Country(166, "CC", "CCK", "166", "Cocos (Keeling) Islands")),
    CO(Country(170, "CO", "COL", "170", "Colombia")),
    KM(Country(174, "KM", "COM", "174", "Comoros")),
    YT(Country(175, "YT", "MYT", "175", "Mayotte")),
    CG(Country(178, "CG", "COG", "178", "Congo")),
    CD(Country(180, "CD", "COD", "180", "DR Congo")),
    CK(Country(184, "CK", "COK", "184", "Cook Islands")),
    CR(Country(188, "CR", "CRI", "188", "Costa Rica")),
    HR(Country(191, "HR", "HRV", "191", "Croatia")),
    CU(Country(192, "CU", "CUB", "192", "Cuba")),
    CY(Country(196, "CY", "CYP", "196", "Cyprus")),
    CZ(Country(203, "CZ", "CZE", "203", "Czechia")),
    BJ(Country(204, "BJ", "BEN", "204", "Benin")),
    DK(Country(208, "DK", "DNK", "208", "Denmark")),
    DM(Country(212, "DM", "DMA", "212", "Dominica")),
    DO(Country(214, "DO", "DOM", "214", "Dominican Republic")),
    EC(Country(218, "EC", "ECU", "218", "Ecuador")),
    SV(Country(222, "SV", "SLV", "222", "El Salvador")),
    GQ(Country(226, "GQ", "GNQ", "226", "Equatorial Guinea")),
    ET(Country(231, "ET", "ETH", "231", "Ethiopia")),
    ER(Country(232, "ER", "ERI", "232", "Eritrea")),
    EE(Country(233, "EE", "EST", "233", "Estonia")),
    FO(Country(234, "FO", "FRO", "234", "Faroe Islands")),
    FK(Country(238, "FK", "FLK", "238", "Falkland Islands")),
    GS(Country(239, "GS", "SGS", "239", "South Georgia and the South Sandwich Islands")),
    FJ(Country(242, "FJ", "FJI", "242", "Fiji")),
    FI(Country(246, "FI", "FIN", "246", "Finland")),
    AX(Country(248, "AX", "ALA", "248", "Åland Islands")),
    FR(Country(250, "FR", "FRA", "250", "France")),
    GF(Country(254, "GF", "GUF", "254", "French Guiana")),
    PF(Country(258, "PF", "PYF", "258", "French Polynesia")),
    TF(Country(260, "TF", "ATF", "260", "French Southern Territories")),
    DJ(Country(262, "DJ", "DJI", "262", "Djibouti")),
    GA(Country(266, "GA", "GAB", "266", "Gabon")),
    GE(Country(268, "GE", "GEO", "268", "Georgia")),
    GM(Country(270, "GM", "GMB", "270", "Gambia")),
    PS(Country(275, "PS", "PSE", "275", "Palestine")),
    DE(Country(276, "DE", "DEU", "276", "Germany")),
    GH(Country(288, "GH", "GHA", "288", "Ghana")),
    GI(Country(292, "GI", "GIB", "292", "Gibraltar")),
    KI(Country(296, "KI", "KIR", "296", "Kiribati")),
    GR(Country(300, "GR", "GRC", "300", "Greece")),
    GL(Country(304, "GL", "GRL", "304", "Greenland")),
    GD(Country(308, "GD", "GRD", "308", "Grenada")),
    GP(Country(312, "GP", "GLP", "312", "Guadeloupe")),
    GU(Country(316, "GU", "GUM", "316", "Guam")),
    GT(Country(320, "GT", "GTM", "320", "Guatemala")),
    GN(Country(324, "GN", "GIN", "324", "Guinea")),
    GY(Country(328, "GY", "GUY", "328", "Guyana")),
    HT(Country(332, "HT", "HTI", "332", "Haiti")),
    HM(Country(334, "HM", "HMD", "334", "Heard Island and McDonald Islands")),
    VA(Country(336, "VA", "VAT", "336", "Holy See")),
    HN(Country(340, "HN", "HND", "340", "Honduras")),
    HK(Country(344, "HK", "HKG", "344", "Hong Kong")),
    HU(Country(348, "HU", "HUN", "348", "Hungary")),
    IS(Country(352, "IS", "ISL", "352", "Iceland")),
    IN(Country(356, "IN", "IND", "356", "India")),
    ID(Country(360, "ID", "IDN", "360", "Indonesia")),
    IR(Country(364, "IR", "IRN", "364", "Iran")),
    IQ(Country(368, "IQ", "IRQ", "368", "Iraq")),
    IE(Country(372, "IE", "IRL", "372", "Ireland")),
    IL(Country(376, "IL", "ISR", "376", "Israel")),
    IT(Country(380, "IT", "ITA", "380", "Italy")),
    CI(Country(384, "CI", "CIV", "384", "Côte d'Ivoire")),
    JM(Country(388, "JM", "JAM", "388", "Jamaica")),
    JP(Country(392, "JP", "JPN", "392", "Japan")),
    KZ(Country(398, "KZ", "KAZ", "398", "Kazakhstan")),
    JO(Country(400, "JO", "JOR", "400", "Jordan")),
    KE(Country(404, "KE", "KEN", "404", "Kenya")),
    KP(Country(408, "KP", "PRK", "408", "North Korea")),
    KR(Country(410, "KR", "KOR", "410", "South Korea")),
    KW(Country(414, "KW", "KWT", "414", "Kuwait")),
    KG(Country(417, "KG", "KGZ", "417", "Kyrgyzstan")),
    LA(Country(418, "LA", "LAO", "418", "Laos")),
    LB(Country(422, "LB", "LBN", "422", "Lebanon")),
    LS(Country(426, "LS", "LSO", "426", "Lesotho")),
    LV(Country(428, "LV", "LVA", "428", "Latvia")),
    LR(Country(430, "LR", "LBR", "430", "Liberia")),
    LY(Country(434, "LY", "LBY", "434", "Libya")),
    LI(Country(438, "LI", "LIE", "438", "Liechtenstein")),
    LT(Country(440, "LT", "LTU", "440", "Lithuania")),
    LU(Country(442, "LU", "LUX", "442", "Luxembourg")),
    MO(Country(446, "MO", "MAC", "446", "Macao")),
    MG(Country(450, "MG", "MDG", "450", "Madagascar")),
    MW(Country(454, "MW", "MWI", "454", "Malawi")),
    MY(Country(458, "MY", "MYS", "458", "Malaysia")),
    MV(Country(462, "MV", "MDV", "462", "Maldives")),
    ML(Country(466, "ML", "MLI", "466", "Mali")),
    MT(Country(470, "MT", "MLT", "470", "Malta")),
    MQ(Country(474, "MQ", "MTQ", "474", "Martinique")),
    MR(Country(478, "MR", "MRT", "478", "Mauritania")),
    MU(Country(480, "MU", "MUS", "480", "Mauritius")),
    MX(Country(484, "MX", "MEX", "484", "Mexico")),
    MC(Country(492, "MC", "MCO", "492", "Monaco")),
    MN(Country(496, "MN", "MNG", "496", "Mongolia")),
    MD(Country(498, "MD", "MDA", "498", "Moldova")),
    ME(Country(499, "ME", "MNE", "499", "Montenegro")),
    MS(Country(500, "MS", "MSR", "500", "Montserrat")),
    MA(Country(504, "MA", "MAR", "504", "Morocco")),
    MZ(Country(508, "MZ", "MOZ", "508", "Mozambique")),
    OM(Country(512, "OM", "OMN", "512", "Oman")),
    NA(Country(516, "NA", "NAM", "516", "Namibia")),
    NR(Country(520, "NR", "NRU", "520", "Nauru")),
    NP(Country(524, "NP", "NPL", "524", "Nepal")),
    NL(Country(528, "NL", "NLD", "528", "Netherlands")),
    CW(Country(531, "CW", "CUW", "531", "Curaçao")),
    AW(Country(533, "AW", "ABW", "533", "Aruba")),
    SX(Country(534, "SX", "SXM", "534", "Sint Maarten")),
    BQ(Country(535, "BQ", "BES", "535", "Bonaire, Sint Eustatius and Saba")),
    NC(Country(540, "NC", "NCL", "540", "New Caledonia")),
    VU(Country(548, "VU", "VUT", "548", "Vanuatu")),
    NZ(Country(554, "NZ", "NZL", "554", "New Zealand")),
    NI(Country(558, "NI", "NIC", "558", "Nicaragua")),
    NE(Country(562, "NE", "NER", "562", "Niger")),
    NG(Country(566, "NG", "NGA", "566", "Nigeria")),
    NU(Country(570, "NU", "NIU", "570", "Niue")),
    NF(Country(574, "NF", "NFK", "574", "Norfolk Island")),
    NO(Country(578, "NO", "NOR", "578", "Norway")),
    MP(Country(580, "MP", "MNP", "580", "Northern Mariana Islands")),
    UM(Country(581, "UM", "UMI", "581", "United States Minor Outlying Islands")),
    FM(Country(583, "FM", "FSM", "583", "Micronesia")),
    MH(Country(584, "MH", "MHL", "584", "Marshall Islands")),
    PW(Country(585, "PW", "PLW", "585", "Palau")),
    PK(Country(586, "PK", "PAK", "586", "Pakistan")),
    PA(Country(591, "PA", "PAN", "591", "Panama")),
    PG(Country(598, "PG", "PNG", "598", "Papua New Guinea")),
    PY(Country(600, "PY", "PRY", "600", "Paraguay")),
    PE(Country(604, "PE", "PER", "604", "Peru")),
    PH(Country(608, "PH", "PHL", "608", "Philippines")),
    PN(Country(612, "PN", "PCN", "612", "Pitcairn")),
    PL(Country(616, "PL", "POL", "616", "Poland")),
    PT(Country(620, "PT", "PRT", "620", "Portugal")),
    GW(Country(624, "GW", "GNB", "624", "Guinea-Bissau")),
    TL(Country(626, "TL", "TLS", "626", "Timor-Leste")),
    PR(Country(630, "PR", "PRI", "630", "Puerto Rico")),
    QA(Country(634, "QA", "QAT", "634", "Qatar")),
    RE(Country(638, "RE", "REU", "638", "Réunion")),
    RO(Country(642, "RO", "ROU", "642", "Romania")),
    RU(Country(643, "RU", "RUS", "643", "Russia")),
    RW(Country(646, "RW", "RWA", "646", "Rwanda")),
    BL(Country(652, "BL", "BLM", "652", "Saint Barthélemy")),
    SH(Country(654, "SH", "SHN", "654", "Saint Helena, Ascension and Tristan da Cunha")),
    KN(Country(659, "KN", "KNA", "659", "Saint Kitts and Nevis")),
    AI(Country(660, "AI", "AIA", "660", "Anguilla")),
    LC(Country(662, "LC", "LCA", "662", "Saint Lucia")),
    MF(Country(663, "MF", "MAF", "663", "Saint Martin")),
    PM(Country(666, "PM", "SPM", "666", "Saint Pierre and Miquelon")),
    VC(Country(670, "VC", "VCT", "670", "Saint Vincent and the Grenadines")),
    SM(Country(674, "SM", "SMR", "674", "San Marino")),
    ST(Country(678, "ST", "STP", "678", "Sao Tome and Principe")),
    SA(Country(682, "SA", "SAU", "682", "Saudi Arabia")),
    SN(Country(686, "SN", "SEN", "686", "Senegal")),
    RS(Country(688, "RS", "SRB", "688", "Serbia")),
    SC(Country(690, "SC", "SYC", "690", "Seychelles")),
    SL(Country(694, "SL", "SLE", "694", "Sierra Leone")),
    SG(Country(702, "SG", "SGP", "702", "Singapore")),
    SK(Country(703, "SK", "SVK", "703", "Slovakia")),
    VN(Country(704, "VN", "VNM", "704", "Viet Nam")),
    SI(Country(705, "SI", "SVN", "705", "Slovenia")),
    SO(Country(706, "SO", "SOM", "706", "Somalia")),
    ZA(Country(710, "ZA", "ZAF", "710", "South Africa")),
    ZW(Country(716, "ZW", "ZWE", "716", "Zimbabwe")),
    ES(Country(724, "ES", "ESP", "724", "Spain")),
    SS(Country(728, "SS", "SSD", "728", "South Sudan")),
    SD(Country(729, "SD", "SDN", "729", "Sudan")),
    EH(Country(732, "EH", "ESH", "732", "Western Sahara")),
    SR(Country(740, "SR", "SUR", "740", "Suriname")),
    SJ(Country(744, "SJ", "SJM", "744", "Svalbard and Jan Mayen")),
    SZ(Country(748, "SZ", "SWZ", "748", "Swaziland")),
    SE(Country(752, "SE", "SWE", "752", "Sweden")),
    CH(Country(756, "CH", "CHE", "756", "Switzerland")),
    SY(Country(760, "SY", "SYR", "760", "Syria")),
    TJ(Country(762, "TJ", "TJK", "762", "Tajikistan")),
    TH(Country(764, "TH", "THA", "764", "Thailand")),
    TG(Country(768, "TG", "TGO", "768", "Togo")),
    TK(Country(772, "TK", "TKL", "772", "Tokelau")),
    TO(Country(776, "TO", "TON", "776", "Tonga")),
    TT(Country(780, "TT", "TTO", "780", "Trinidad and Tobago")),
    AE(Country(784, "AE", "ARE", "784", "United Arab Emirates")),
    TN(Country(788, "TN", "TUN", "788", "Tunisia")),
    TR(Country(792, "TR", "TUR", "792", "Turkey")),
    TM(Country(795, "TM", "TKM", "795", "Turkmenistan")),
    TC(Country(796, "TC", "TCA", "796", "Turks and Caicos Islands")),
    TV(Country(798, "TV", "TUV", "798", "Tuvalu")),
    UG(Country(800, "UG", "UGA", "800", "Uganda")),
    UA(Country(804, "UA", "UKR", "804", "Ukraine")),
    MK(Country(807, "MK", "MKD", "807", "Macedonia")),
    EG(Country(818, "EG", "EGY", "818", "Egypt")),
    GB(Country(826, "GB", "GBR", "826", "United Kingdom (UK)")),
    GG(Country(831, "GG", "GGY", "831", "Guernsey")),
    JE(Country(832, "JE", "JEY", "832", "Jersey")),
    IM(Country(833, "IM", "IMN", "833", "Isle of Man")),
    TZ(Country(834, "TZ", "TZA", "834", "Tanzania")),
    US(Country(840, "US", "USA", "840", "United States of America (USA)")),
    VI(Country(850, "VI", "VIR", "850", "United States Virgin Islands")),
    BF(Country(854, "BF", "BFA", "854", "Burkina Faso")),
    UY(Country(858, "UY", "URY", "858", "Uruguay")),
    UZ(Country(860, "UZ", "UZB", "860", "Uzbekistan")),
    VE(Country(862, "VE", "VEN", "862", "Venezuela")),
    WF(Country(876, "WF", "WLF", "876", "Wallis and Futuna")),
    WS(Country(882, "WS", "WSM", "882", "Samoa")),
    YE(Country(887, "YE", "YEM", "887", "Yemen")),
    ZM(Country(894, "ZM", "ZMB", "894", "Zambia")),
}
//@formatter:on
