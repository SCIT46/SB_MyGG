package com.mygg.sb.statics.api;

public enum CountryType {
    Czech("cs_CZ"),
    Greece("el_GR"),
    Poland("pl_PL"),
    Romania("ro_RO"),
    Hungary("hu_HU"),
    UnitedKingdom("en_GB"),
    Germany("de_DE"),
    Spain("es_ES"),
    Italy("it_IT"),
    France("fr_FR"),
    Japan("ja_JP"),
    Korea("ko_KR"),
    Mexico("es_MX"),
    Argentina("es_AR"),
    Brazil("pt_BR"),
    UnitedStates("en_US"),
    Australia("en_AU"),
    Russia("ru_RU"),
    Turkey("tr_TR"),
    Malaysia("ms_MY"),
    Philippines("en_PH"),
    Singapore("en_SG"),
    Thailand("th_TH"),
    Vietnam("vi_VN"),
    Indonesia("id_ID"),
    MalaysiaChinese("zh_MY"),
    China("zh_CN"),
    Taiwan("zh_TW");

    private String country;

    CountryType(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
