import styled from "styled-components";
import { IMatchDetail } from "./type";

const ParticipantsContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 7px;
  margin-right: -100px;
`;

const TeamContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

const ParticipantsBox = styled.div`
  display: flex;
  gap: 5px;
  align-items: center;
`;

const ParticipantImg = styled.img`
  background-color: gray;
  border-radius: 3px;
  width: 16px;
  height: 16px;
`;

const ParticipantImgSpan = styled.div`
  font-size: 14px;
  white-space: nowrap; /* 줄바꿈 방지 */
  overflow: hidden; /* 초과 내용 숨기기 */
  text-overflow: ellipsis; /* 초과 부분 "..."으로 표시 */
  width: 90px;
  font-size: 13px;
`;

interface IParticipants {
  matchDetail: IMatchDetail;
}

export default function Participants({ matchDetail }: IParticipants) {
  return (
    <ParticipantsContainer>
      <TeamContainer>
        {matchDetail.info.participants.slice(0, 5).map((part, index) => (
          <ParticipantsBox key={index}>
            <ParticipantImg
              src={`https://ddragon.leagueoflegends.com/cdn/14.23.1/img/champion/${part.championName}.png`}
            ></ParticipantImg>
            <ParticipantImgSpan>{part.riotIdGameName}</ParticipantImgSpan>
          </ParticipantsBox>
        ))}
      </TeamContainer>
      <TeamContainer>
        {matchDetail.info.participants.slice(5, 10).map((part, index) => (
          <ParticipantsBox key={index}>
            <ParticipantImg
              src={`https://ddragon.leagueoflegends.com/cdn/14.23.1/img/champion/${part.championName}.png`}
            ></ParticipantImg>
            <ParticipantImgSpan>{part.riotIdGameName}</ParticipantImgSpan>
          </ParticipantsBox>
        ))}
      </TeamContainer>
    </ParticipantsContainer>
  );
}
