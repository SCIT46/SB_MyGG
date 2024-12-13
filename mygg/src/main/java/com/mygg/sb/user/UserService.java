package com.mygg.sb.user;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.mygg.sb.statics.api.RiotApiClient;
import com.mygg.sb.statics.util.DateTimeUtils;

import lombok.Getter;

@Getter
@Service
public class UserService {

    // 유저 정보
    UserDTO user;

    // 유저 정보 초기화/생성(lombok 자동 생성 불가)
    public UserService() throws Exception{
        user = new UserDTO();
    }

    // 이름과 태그를 통해 소환사 정보를 불러올 때 사용하는 생성자
    public UserDTO getUserInfo(String gameName, String tagLine) throws Exception{

        // 이름과 태그를 puuid로 변환
        user.setGameName(gameName);
        user.setTagLine(tagLine);
        user.setPuuid(RiotApiClient.getPuuidNameAndTag(gameName,tagLine));
        init();
        return this.user;
	  }
    
    // puuid를 통해 소환사 정보를 불러올 때 사용하는 생성자 (미사용)
    public UserDTO getUserInfo(String puuid) throws Exception{
        user.setPuuid(puuid);
        String[] nametag = RiotApiClient.getNametag(puuid);
        user.setGameName(nametag[0]);
        user.setTagLine(nametag[1]);
        init();

        return this.user;
    }

    // 인스턴스 초기화 구문
    private void init() throws Exception{
        // 소환사 정보 JSON
        JSONObject jsonObject = RiotApiClient.getSummonerInfo(user.getPuuid());   //(JSONObject) parser.parse(summoJSON);           
        user.setSummonerId((String) jsonObject.get("id"));
        user.setProfileIconId(((Long) jsonObject.get("profileIconId")).intValue());
        user.setRevisionDate(DateTimeUtils.epochToLocalDateTime((long)jsonObject.get("revisionDate")));
        user.setSummonerLevel(((Long) jsonObject.get("summonerLevel")).intValue());

        // summonerId를 통해 리그정보 JSON 받아옴
        JSONObject jsonObject2 = RiotApiClient.getLeagueBySummonerId(user.getSummonerId());
        user.setLeagueId((String) jsonObject2.get("leagueId"));
        user.setTier((String) jsonObject2.get("tier"));
        user.setRank((String) jsonObject2.get("rank"));
        user.setLeaguePoints(((Long) jsonObject2.get("leaguePoints")).intValue());
        user.setWins(((Long) jsonObject2.get("wins")).intValue());
        user.setLosses(((Long) jsonObject2.get("losses")).intValue());
        // 최근 매치 목록
        user.setMatchList(RiotApiClient.getMatchList(user.getPuuid()));
    }
}
