package com.mygg.sb.match.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mygg.sb.match.entity.MMatchEntity;
import com.mygg.sb.match.analist.entity.RecenetMatchDataEntity;

import java.util.List;

@Repository
public class MMatchesRepositoryCustomImpl implements MMatchesRepositoryCustom
	{

		@Autowired
		private MongoTemplate mongoTemplate;

		@Override
		public List<RecenetMatchDataEntity> getRecentMatchStatsForUser(String puuid)
			{
				Aggregation aggregation = Aggregation.newAggregation(
					    Aggregation.match(Criteria.where("info.participants.puuid").is(puuid)),
					    Aggregation.sort(Sort.by(Sort.Order.desc("info.gameEndTimestamp"))),
//					    Aggregation.limit(20),
					    Aggregation.match(Criteria.where("info.queueId").in(420, 440)),	// 420: soloRank 440: FreeRank
					    Aggregation.unwind("info.participants"),
					    Aggregation.match(Criteria.where("info.participants.puuid").is(puuid)),
					    Aggregation.group("info.participants.championId")
					        .avg("info.participants.kills").as("kill")
					        .avg("info.participants.deaths").as("death")
					        .avg("info.participants.assists").as("assist")
					        .avg("info.participants.totalMinionsKilled").as("minionKilled")
					        .avg("info.participants.neutralMinionsKilled").as("neutralKilled")
					        .avg("info.participants.challenges.kda").as("kda")
					        .first("info.participants.championName").as("championName")
					        .count().as("gameCnt"),
					    Aggregation.sort(Sort.by(Sort.Order.desc("gameCnt"))), // gameCnt로 정렬
					    Aggregation.project()
					        .and("_id").as("championId")
					        .and("kill").as("kill")
					        .and("death").as("death")
					        .and("assist").as("assist")
					        .and("minionKilled").as("minionKilled")
					        .and("neutralKilled").as("neutralKilled")
					        .and("gameCnt").as("gameCnt")
					        .and("kda").as("kda")
					        .and("championName").as("championName")
					);

				AggregationResults<RecenetMatchDataEntity> results = mongoTemplate.aggregate(aggregation,
						MMatchEntity.class, RecenetMatchDataEntity.class);
				//		뽑을 데이터 | 매핑할 대상

				return results.getMappedResults();
			}
	}
