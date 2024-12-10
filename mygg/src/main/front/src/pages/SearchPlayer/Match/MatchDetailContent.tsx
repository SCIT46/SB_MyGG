import styled from "styled-components";
import { IMatchDetailProp } from "./MatchDetail";
import ChampionImage from "../../../components/ChampionImage";
import SummonerImage from "../../../components/SummonerImage";
import ItemImage from "../../../components/ItemImage";
import StyledRuneImage from "../../../components/StyledRuneImage";

const MatchDetailContentContainer = styled.div`
  border: 2px solid black;
  border-radius: 10px;
  width: 90%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

const TableHeader = styled.thead`
  th {
    text-align: center;
    font-weight: 600;
    height: 40px;
    vertical-align: middle;
  }
`;

const TableRow = styled.tr`
  td {
    padding: 6px;
  }
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
  font-size: 12px;
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
            <th>팀</th>
            <th>KDA</th>
            <th>피해량</th>
            <th>와드</th>
            <th>CS</th>
            <th>아이템</th>
          </tr>
        </TableHeader>
        <tbody>
          {matchDetail.info.participants.slice(0, 5).map((part, index) => (
            <TableRow key={index}>
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
                  <StyledRuneImage
                    runeId={part.perks.styles[0].style}
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
                  <ItemImage itemId={part.item6} width={20} height={20} />
                </ItemBox>
              </ItemTd>
            </TableRow>
          ))}
        </tbody>
      </Table>
      <Table>
        <TableHeader>
          <tr>
            <th>팀</th>
            <th>KDA</th>
            <th>피해량</th>
            <th>와드</th>
            <th>CS</th>
            <th>아이템</th>
          </tr>
        </TableHeader>
        <tbody>
          {matchDetail.info.participants.slice(5, 10).map((part, index) => (
            <TableRow key={index}>
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
                  <StyledRuneImage
                    runeId={part.perks.styles[0].style}
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
                  <ItemImage itemId={part.item6} width={20} height={20} />
                </ItemBox>
              </ItemTd>
            </TableRow>
          ))}
        </tbody>
      </Table>
    </MatchDetailContentContainer>
  );
}
