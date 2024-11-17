import styled from "styled-components";

const ProfileContainer = styled.div`
  margin-top: 70px;
  width: 1000px;
  height: 150px;
  background-color: ${({ theme }) => theme.colors.backgroundWhite};
  border: 1px ${({ theme }) => theme.colors.primaryGold} solid;
  border-radius: 10px;
  display: flex;
  align-items: center;
`;

const ProfileImg = styled.div`
  margin-left: 25px;
  margin-right: 25px;
  height: 100px;
  width: 100px;
  background-color: #a4a4a4;
  border-radius: 10px;
`;

const DetailContainer = styled.div`
  height: 90px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const NameSpan = styled.div`
  font-size: 20px;
  font-weight: 600;
  color: ${({ theme }) => theme.colors.textBlack};
`;

const LevelSpan = styled.div`
  color: ${({ theme }) => theme.colors.lightText};
  margin-bottom: 30px;
  font-size: 16px;
`;

export default function Profile() {
  return (
    <ProfileContainer>
      <ProfileImg />
      <DetailContainer>
        <NameSpan>소환사 이름#태그</NameSpan>
        <LevelSpan>레벨</LevelSpan>
        <div>전적갱신 버튼</div>
      </DetailContainer>
    </ProfileContainer>
  );
}
