import styled from "styled-components";
import { IMatchDetail } from "./type";
import useCurrentVersionStore from "../../stores/useCurrentVersionStore";

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
  margin-left: -1px;

  &:hover {
    text-decoration: underline; /* 호버시 밑줄 추가 */
  }
`;

interface IParticipants {
  matchDetail: IMatchDetail;
}

export default function Participants({ matchDetail }: IParticipants) {
  const version = useCurrentVersionStore((state) => state.version);
  return (
    <ParticipantsContainer>
      <TeamContainer>
        {matchDetail.info.participants.slice(0, 5).map((part, index) => (
          <ParticipantsBox key={index}>
            <ParticipantImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${part.championName}.png`}
            ></ParticipantImg>
            <ParticipantImgSpan
              onClick={() => {
                window.open(
                  `/search/${part.riotIdGameName}-${part.riotIdTagline}`,
                  "_blank",
                  "noopener,noreferrer"
                );
              }}
              style={{ cursor: "pointer" }}
            >
              {part.riotIdGameName}
            </ParticipantImgSpan>
          </ParticipantsBox>
        ))}
      </TeamContainer>
      <TeamContainer>
        {matchDetail.info.participants.slice(5, 10).map((part, index) => (
          <ParticipantsBox key={index}>
            <ParticipantImg
              src={`https://ddragon.leagueoflegends.com/cdn/${version}/img/champion/${part.championName}.png`}
            ></ParticipantImg>
            <ParticipantImgSpan
              onClick={() => {
                window.open(
                  `/search/${part.riotIdGameName}-${part.riotIdTagline}`,
                  "_blank",
                  "noopener,noreferrer"
                );
              }}
              style={{ cursor: "pointer" }}
            >
              {part.riotIdGameName}
            </ParticipantImgSpan>{" "}
          </ParticipantsBox>
        ))}
      </TeamContainer>
    </ParticipantsContainer>
  );
}
