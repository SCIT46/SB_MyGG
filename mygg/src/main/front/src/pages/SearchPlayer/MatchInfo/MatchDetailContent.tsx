import styled from "styled-components";
import { IMatchDetailProp } from "./MatchDetail";
import ChampionImage from "../../../components/ImageUI/ChampionImage";
import SummonerImage from "../../../components/ImageUI/SummonerImage";
import ItemImage from "../../../components/ImageUI/ItemImage";
import StyledRuneImage from "../../../components/ImageUI/StyledRuneImage";
import RuneImage from "../../../components/ImageUI/RuneImage";

const MatchDetailContentContainer = styled.div`
  width: 90%;
  display: flex;
  flex-direction: column;
  gap: 7px;
`;

const Table = styled.table`
  border-radius: 10px;
  width: 100%;
  border-collapse: separate;
  overflow: hidden;
  border: 1px solid ${({ theme }) => theme.colors.border.light};
`;

const TableHeader = styled.thead`
  th {
    border-bottom: 2px solid ${({ theme }) => theme.colors.border.light};
    text-align: center;
    font-weight: 800;
    height: 30px;
    vertical-align: middle;
    background-color: ${({ theme }) => theme.colors.background.primary};
    font-size: 12px;
    color: ${({ theme }) => theme.colors.text.secondary};
  }
`;

const TableRow = styled.tr<{ isWin: boolean }>`
  td {
    padding: 6px;
  }
  background-color: ${({ isWin }) => (isWin ? "#e0ecff" : "#ffe5e5")};
`;

const ProfileTd = styled.td`
  display: flex;
  align-items: center;
  gap: 4px;
  width: 200px;
`;

const SpellBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

const RuneBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

const NameBox = styled.div`
  margin-left: 8px;
  font-weight: 500;
  font-size: 14px;
  color: ${({ theme }) => theme.colors.text.secondary};
  cursor: pointer;
  &:hover {
    text-decoration: underline;
  }
`;

const ItemTd = styled.td`
  vertical-align: middle;
  margin-right: auto;
`;

const ItemBox = styled.div`
  display: flex;
  gap: 1px;
  align-items: center;
  justify-content: center;
`;

const KdaTd = styled.td`
  font-size: 12px;
  vertical-align: middle;
  text-align: center;
`;

const TotalDamageTd = styled.td`
  vertical-align: middle;
  text-align: center;
  font-size: 12px;
`;

const VisionWardTd = styled.td`
  vertical-align: middle;
  text-align: center;
  font-size: 12px;
  width: 30px;
`;

const TotalMinionTd = styled.td`
  vertical-align: middle;
  text-align: center;
  font-size: 12px;
`;

export default function MatchDetailContent({
  matchDetail,
  userPuuid,
}: IMatchDetailProp) {
  return (
    <MatchDetailContentContainer>
      <Table>
        <TableHeader>
          <tr>
            <th>
              블루 팀 ({matchDetail.info.participants[0].win ? "승리" : "패배"})
            </th>
            <th>KDA</th>
            <th>피해량</th>
            <th>와드</th>
            <th>CS</th>
            <th>아이템</th>
          </tr>
        </TableHeader>
        <tbody>
          {matchDetail.info.participants.slice(0, 5).map((part, index) => (
            <TableRow key={index} isWin={part.win}>
              <ProfileTd>
                <ChampionImage
                  championId={part.championName}
                  width={40}
                  height={40}
                />
                <SpellBox>
                  <SummonerImage
                    summonerId={part.summoner1Id}
                    width={18}
                    height={18}
                  />
                  <SummonerImage
                    summonerId={part.summoner2Id}
                    width={18}
                    height={18}
                  />
                </SpellBox>
                <RuneBox>
                  <RuneImage
                    runeId={part.perks.styles[0].selections[0].perk}
                    styleRuneId={part.perks.styles[0].style}
                    width={18}
                    height={18}
                  />
                  <StyledRuneImage
                    runeId={part.perks.styles[0].style}
                    width={18}
                    height={18}
                  />
                </RuneBox>

                <NameBox
                  onClick={() => {
                    window.open(
                      `/search/${part.riotIdGameName}-${part.riotIdTagline}`,
                      "_blank",
                      "noopener,noreferrer"
                    );
                  }}
                >
                  {part.riotIdGameName}
                </NameBox>
              </ProfileTd>
              <KdaTd>
                <div>
                  {part.kills} / {part.deaths} / {part.assists}
                </div>
              </KdaTd>
              <TotalDamageTd>{part.totalDamageDealtToChampions}</TotalDamageTd>
              <VisionWardTd>{part.visionWardsBoughtInGame}</VisionWardTd>
              <TotalMinionTd>{part.totalMinionsKilled}</TotalMinionTd>
              <ItemTd>
                <ItemBox>
                  <ItemImage itemId={part.item0} width={20} height={20} />
                  <ItemImage itemId={part.item1} width={20} height={20} />
                  <ItemImage itemId={part.item2} width={20} height={20} />
                  <ItemImage itemId={part.item3} width={20} height={20} />
                  <ItemImage itemId={part.item4} width={20} height={20} />
                  <ItemImage itemId={part.item5} width={20} height={20} />
                  <ItemImage
                    itemId={part.item6}
                    width={20}
                    height={20}
                    isTrinket={true}
                  />
                </ItemBox>
              </ItemTd>
            </TableRow>
          ))}
        </tbody>
      </Table>
      <Table>
        <TableHeader>
          <tr>
            <th>
              레드 팀 ({matchDetail.info.participants[5].win ? "승리" : "패배"})
            </th>
            <th>KDA</th>
            <th>피해량</th>
            <th>와드</th>
            <th>CS</th>
            <th>아이템</th>
          </tr>
        </TableHeader>
        <tbody>
          {matchDetail.info.participants.slice(5, 10).map((part, index) => (
            <TableRow key={index} isWin={part.win}>
              <ProfileTd>
                <ChampionImage
                  championId={part.championName}
                  width={40}
                  height={40}
                />
                <SpellBox>
                  <SummonerImage
                    summonerId={part.summoner1Id}
                    width={18}
                    height={18}
                  />
                  <SummonerImage
                    summonerId={part.summoner2Id}
                    width={18}
                    height={18}
                  />
                </SpellBox>
                <RuneBox>
                  <RuneImage
                    runeId={part.perks.styles[0].selections[0].perk}
                    styleRuneId={part.perks.styles[0].style}
                    width={18}
                    height={18}
                  />
                  <StyledRuneImage
                    runeId={part.perks.styles[0].style}
                    width={18}
                    height={18}
                  />
                </RuneBox>
                <NameBox>{part.riotIdGameName}</NameBox>
              </ProfileTd>
              <KdaTd>
                <div>
                  {part.kills} / {part.deaths} / {part.assists}
                </div>
              </KdaTd>
              <TotalDamageTd>{part.totalDamageDealtToChampions}</TotalDamageTd>
              <VisionWardTd>{part.visionWardsBoughtInGame}</VisionWardTd>
              <TotalMinionTd>{part.totalMinionsKilled}</TotalMinionTd>
              <ItemTd>
                <ItemBox>
                  <ItemImage itemId={part.item0} width={20} height={20} />
                  <ItemImage itemId={part.item1} width={20} height={20} />
                  <ItemImage itemId={part.item2} width={20} height={20} />
                  <ItemImage itemId={part.item3} width={20} height={20} />
                  <ItemImage itemId={part.item4} width={20} height={20} />
                  <ItemImage itemId={part.item5} width={20} height={20} />
                  <ItemImage
                    itemId={part.item6}
                    width={20}
                    height={20}
                    isTrinket={true}
                  />
                </ItemBox>
              </ItemTd>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </MatchDetailContentContainer>
  );
}
