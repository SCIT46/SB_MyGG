package com.mygg.sb.match;

class Participants
{
	// 플레이어 정보
	String champion;	// 사용한 챔피언
	String lane;		// 어느 라인인가														// teamPosition
	String userName;	// 유저 이름
	Long userLevel;		// 유저 레벨
	Double goldPerMin;	// 분당 골드 값 추출
	
	public Participants(String champion, String lane, String userName, Long userLevel, Double goldPerMin)
		{
			this.champion = champion;
			this.lane = lane;
			this.userName = userName;
			this.userLevel = userLevel;
			this.goldPerMin = goldPerMin;
		}

	@Override
	public String toString()
		{
			return "Participants [champion=" + champion + ", lane=" + lane + ", userName=" + userName + ", userLevel="
					+ userLevel + ", goldPerMin=" + goldPerMin + "]";
		}
}