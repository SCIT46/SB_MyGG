package com.mygg.sb.statics.api;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class RiotApiConstants {
    public static Dotenv dotenv = Dotenv.load();
    public static final String API_KEY = dotenv.get("RIOT_API_PERSONAL_KEY");
    // =============================================================================================
    //https://developer.riotgames.com/docs/lol
    public final static String RIOT_DATA_API_URL = "https://ddragon.leagueoflegends.com";
    public final static String RIOT_DATA_API_VERSION = "/api/versions.json";
    public final static String RIOT_DATA_API_LANGUAGE = "/cdn/languages.json";
    public final static String RIOT_DATA_API_ITEM = "/cdn/14.23.1/data/ko_KR/item.json";
    public final static String RIOT_DATA_API_ITEM_IMG = "/cdn/14.23.1/img/item/";
    public final static String RIOT_DATA_API_CHAMPION = "/cdn/14.23.1/data/ko_KR/champion.json";
    public final static String RIOT_DATA_API_CHAMPION_IMG = "/cdn/14.23.1/img/champion/";
    //public final static String RIOT_DATA_API_RUNE = "/cdn/{version}/data/{language}/runes.json";
    //public final static String RIOT_DATA_API_SPELL = "/cdn/{version}/data/{language}/summoner.json";
    // =============================================================================================
    public final static String RIOT_API_URL = "https://asia.api.riotgames.com";
    public final static String RIOT_API_URL_KR = "https://kr.api.riotgames.com";
    // =============================================================================================
    public final static String RIOT_API_ACCOUNT_RID = "/riot/account/v1/accounts/by-riot-id/"; // public match
    public final static String RIOT_API_ACCOUNT_PID = "/riot/account/v1/accounts/by-puuid/"; // public match
    public final static String RIOT_API_MATCH = "/lol/match/v5/matches/"; // public match
    public final static String RIOT_API_MATCH_P = "/lol/rso-match/v1/matches/"; // private match
    public final static String RIOT_API_SUMMONER_INFO = "/lol/summoner/v4/summoners/by-puuid/";
    public final static String RIOT_API_LEAGUE = "/lol/league/v4/"; // Iron ~ Diamond
    public final static String RIOT_API_LEAGUE_EXP = "/lol/league-exp/v4/"; // Master ~ Challenger
}
