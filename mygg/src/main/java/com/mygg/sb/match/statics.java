package com.mygg.sb.match;

import io.github.cdimascio.dotenv.Dotenv;

public class statics {
    static Dotenv dotenv = Dotenv.load();
	static final String API_KEY = dotenv.get("RIOT_API_PERSONAL_KEY");
	final static String RIOT_API_URL = "https://asia.api.riotgames.com";
	final static String RIOT_API_MATCH = "/lol/match/v5/matches/";
}
