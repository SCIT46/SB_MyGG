import styled from "styled-components";

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

const ParticipantImg = styled.div`
  background-color: gray;
  border-radius: 3px;
  width: 15px;
  height: 15px;
`;

const ParticipantImgSpan = styled.div`
  font-size: 14px;
  overflow: hidden;
  width: 90px;
`;

export default function Participants() {
  return (
    <ParticipantsContainer>
      <TeamContainer>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>
            fdsafdjskfadsjklfdjsalkfjdsklhafds
          </ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
      </TeamContainer>
      <TeamContainer>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
        <ParticipantsBox>
          <ParticipantImg></ParticipantImg>
          <ParticipantImgSpan>parti</ParticipantImgSpan>
        </ParticipantsBox>
      </TeamContainer>
    </ParticipantsContainer>
  );
}
