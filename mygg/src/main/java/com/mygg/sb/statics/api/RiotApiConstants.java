package com.mygg.sb.statics.api;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class RiotApiConstants {
    public static Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = dotenv.get("RIOT_API_PERSONAL_KEY");
    // =============================================================================================
    //https://developer.riotgames.com/docs/lol
    public static final String LANGUAGE = CountryType.Korea.getCountry();  //RiotApiClient.getLanguage();
    public static final String LATEST_VERSION;
    static {
        try {
            LATEST_VERSION = RiotApiClient.getLatestVersion();
        } catch (Exception e) {
            throw new RuntimeException("라이엇 API 버전을 가져오는 데 실패했습니다.", e);
        }
    }
    // =============================================================================================
    public static final String RIOT_DATA_API_URL = "https://ddragon.leagueoflegends.com";
    public static final String RIOT_DATA_API_VERSION = "/api/versions.json";
    public static final String RIOT_DATA_API_LANGUAGE = "/cdn/languages.json";
    public static final String RIOT_DATA_API_ITEM = "/cdn/"+LATEST_VERSION+"/data/"+LANGUAGE+"/item.json";
    public static final String RIOT_DATA_API_ITEM_IMG = "/cdn/"+LATEST_VERSION+"/img/item/";
    public static final String RIOT_DATA_API_CHAMPION = "/cdn/"+LATEST_VERSION+"/data/"+LANGUAGE+"/champion.json";
    public static final String RIOT_DATA_API_CHAMPION_IMG = "/cdn/"+LATEST_VERSION+"/img/champion/";
    public static final String RIOT_DATA_API_RUNE = "/cdn/"+LATEST_VERSION+"/data/"+LANGUAGE+"/runesReforged.json";
    //public final static String RIOT_DATA_API_RUNE = "/cdn/{version}/data/{language}/runes.json";
    //public final static String RIOT_DATA_API_SPELL = "/cdn/{version}/data/{language}/summoner.json";
    // =============================================================================================
    public static final String RIOT_API_URL = "https://"+RegionServer.ASIA+".api.riotgames.com";
    public static final String RIOT_API_URL_KR = "https://"+CountryServer.KR+".api.riotgames.com";
    // =================================== League of Legends API ===================================
    // =============================================================================================
    public static final String RIOT_API_ACCOUNT_RID = "/riot/account/v1/accounts/by-riot-id/"; // public match
    public static final String RIOT_API_ACCOUNT_PID = "/riot/account/v1/accounts/by-puuid/"; // public match
    public static final String RIOT_API_MATCH = "/lol/match/v5/matches/"; // public match
    public static final String RIOT_API_MATCH_P = "/lol/rso-match/v1/matches/"; // private match
    public static final String RIOT_API_SUMMONER_INFO = "/lol/summoner/v4/summoners/by-puuid/";
    public static final String RIOT_API_LEAGUE = "/lol/league/v4/"; // Iron ~ Diamond
    public static final String RIOT_API_LEAGUE_EXP = "/lol/league-exp/v4/"; // Master ~ Challenger
}
