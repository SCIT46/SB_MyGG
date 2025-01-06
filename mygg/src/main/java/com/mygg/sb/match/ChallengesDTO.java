package com.mygg.sb.match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengesDTO
	{
		float controlWardTimeCoverageInRiverOrEnemyHalf; // 강가 또는 적 지역에서의 제어 와드 지속 시간.		
		float kda; // kda	
		int controlWardsPlaced; // 배치한 제어와드 수	
		float damagePerMinute; // 분당 DPS	
		
		float gameLength; // 게임시간
		int skillshotsDodged; // 회피한 스킬샷 수	
		int	skillshotsHit;	// 적중한 스킬샷
		int teamBaronKills; // 팀의 바론을 킬한 수
			
		int teamElderDragonKills; // 팀의 엘더용을 킬한 수	
		int teamRiftHeraldKills;// 전령을 킬한 수	
		float visionScorePerMinute; // 분당 시야점수	
		double goldPerMinute;		// 분당 골드수
//		teamDamagePercentage	float
//		fullTeamTakedown	int	
//		earliestBaron	int	
//		earliestDragonTakedown	int	
//		earliestElderDragon	int	
//		earlyLaningPhaseGoldExpAdvantage	int	
//		fasterSupportQuestCompletion	int	
//		fastestLegendary	int	
//		hadAfkTeammate	int	
//		highestChampionDamage	int	
//		highestCrowdControlScore	int	
//		highestWardKills	int	
//		junglerKillsEarlyJungle	int	
//		killsOnLanersEarlyJungleAsJungler	int	
//		laningPhaseGoldExpAdvantage	int	
//		legendaryCount	int	
//		maxCsAdvantageOnLaneOpponent	float	
//		maxLevelLeadLaneOpponent	int	
//		mostWardsDestroyedOneSweeper	int	
//		mythicItemUsed	int	
//		playedChampSelectPosition	int	
//		soloTurretsLategame	int	
//		takedownsFirst25Minutes	int	
//		teleportTakedowns	int	
//		thirdInhibitorDestroyedTime	int	
//		threeWardsOneSweeperCount	int	
//		visionScoreAdvantageLaneOpponent	float	
//		InfernalScalePickup	int	
//		fistBumpParticipation	int	
//		voidMonsterKill	int	
//		abilityUses	int	
//		acesBefore15Minutes	int	
//		alliedJungleMonsterKills	float	
//		baronTakedowns	int	
//		blastConeOppositeOpponentCount	int	
//		bountyGold	int	
//		buffsStolen	int	
//		completeSupportQuestInTime	int	
//
//		damageTakenOnTeamPercentage	float	
//		dancedWithRiftHerald	int	
//		deathsByEnemyChamps	int	
//		dodgeSkillShotsSmallWindow	int	
//		doubleAces	int	
//		dragonTakedowns	int	
//		legendaryItemUsed	List[int]	
//		effectiveHealAndShielding	float	
//		elderDragonKillsWithOpposingSoul	int	
//		elderDragonMultikills	int	
//		enemyChampionImmobilizations	int	
//		enemyJungleMonsterKills	float	
//		epicMonsterKillsNearEnemyJungler	int	
//		epicMonsterKillsWithin30SecondsOfSpawn	int	
//		epicMonsterSteals	int	
//		epicMonsterStolenWithoutSmite	int	
//		firstTurretKilled	int	
//		firstTurretKilledTime	float	
//		flawlessAces	int	
//
//		getTakedownsInAllLanesEarlyJungleAsLaner	int	
//		goldPerMinute	float	
//		hadOpenNexus	int	
//		immobilizeAndKillWithAlly	int	
//		initialBuffCount	int	
//		initialCrabCount	int	
//		jungleCsBefore10Minutes	float	
//		junglerTakedownsNearDamagedEpicMonster	int	
//		
//		killAfterHiddenWithAlly	int	
//		killedChampTookFullTeamDamageSurvived	int	
//		killingSprees	int	
//		killParticipation	float	
//		killsNearEnemyTurret	int	
//		killsOnOtherLanesEarlyJungleAsLaner	int	
//		killsOnRecentlyHealedByAramPack	int	
//		killsUnderOwnTurret	int	
//		killsWithHelpFromEpicMonster	int	
//		knockEnemyIntoTeamAndKill	int	
//		kTurretsDestroyedBeforePlatesFall	int	
//		landSkillShotsEarlyGame	int	
//		laneMinionsFirst10Minutes	int	
//		lostAnInhibitor	int	
//		maxKillDeficit	int	
//		mejaisFullStackInTime	int	
//		moreEnemyJungleThanOpponent	float	
//		multiKillOneSpell	int	This is an offshoot of the OneStone challenge. The code checks if a spell with the same instance ID does the final point of damage to at least 2 Champions. It doesn't matter if they're enemies, but you cannot hurt your friends.
//		multikills	int	
//		multikillsAfterAggressiveFlash	int	
//		multiTurretRiftHeraldCount	int	
//		outerTurretExecutesBefore10Minutes	int	
//		outnumberedKills	int	
//		outnumberedNexusKill	int	
//		perfectDragonSoulsTaken	int	
//		perfectGame	int	
//		pickKillWithAlly	int	
//		poroExplosions	int	
//		quickCleanse	int	
//		quickFirstTurret	int	
//		quickSoloKills	int	
//		riftHeraldTakedowns	int	
//		saveAllyFromDeath	int	
//		scuttleCrabKills	int	
//		shortestTimeToAceFromFirstTakedown	float	
//	
//		snowballsHit	int	
//		soloBaronKills	int	
//		SWARM_DefeatAatrox	int	
//		SWARM_DefeatBriar	int	
//		SWARM_DefeatMiniBosses	int	
//		SWARM_EvolveWeapon	int	
//		SWARM_Have3Passives	int	
//		SWARM_KillEnemy	int	
//		SWARM_PickupGold	float	
//		SWARM_ReachLevel50	int	
//		SWARM_Survive15Min	int	
//		SWARM_WinWith5EvolvedWeapons	int	
//		soloKills	int	
//		stealthWardsPlaced	int	
//		survivedSingleDigitHpCount	int	
//		survivedThreeImmobilizesInFight	int	
//		takedownOnFirstTurret	int	
//		takedowns	int	
//		takedownsAfterGainingLevelAdvantage	int	
//		takedownsBeforeJungleMinionSpawn	int	
//		takedownsFirstXMinutes	int	
//		takedownsInAlcove	int	
//		takedownsInEnemyFountain	int	
//
//		tookLargeDamageSurvived	int	
//		turretPlatesTaken	int	
//		turretsTakenWithRiftHerald	int	Any player who damages a tower that is destroyed within 30 seconds of a Rift Herald charge will receive credit. A player who does not damage the tower will not receive credit.
//		turretTakedowns	int	
//		twentyMinionsIn3SecondsCount	int	
//		twoWardsOneSweeperCount	int	
//		unseenRecalls	int	
//
//		wardsGuarded	int	
//		wardTakedowns	int	
//		wardTakedownsBefore20M	int	
//		public static ChallengesDTO toDTO(MChallengesEntity challengesEntity) {
//		    return ChallengesDTO.builder()
//		            .controlWardTimeCoverageInRiverOrEnemyHalf(challengesEntity.getControlWardTimeCoverageInRiverOrEnemyHalf()) // 강가 또는 적 지역에서의 제어 와드 지속 시간
//		            .kda(challengesEntity.getKda())                                                                           // KDA
//		            .controlWardsPlaced(challengesEntity.getControlWardsPlaced())                                             // 배치한 제어 와드 수
//		            .damagePerMinute(challengesEntity.getDamagePerMinute())                                                  // 분당 DPS
//		            .gameLength(challengesEntity.getGameLength())                                                            // 게임 시간
//		            .skillshotsDodged(challengesEntity.getSkillshotsDodged())                                                // 회피한 스킬샷 수
//		            .skillshotsHit(challengesEntity.getSkillshotsHit())                                                      // 적중한 스킬샷 수
//		            .teamBaronKills(challengesEntity.getTeamBaronKills())                                                    // 팀 바론 킬 수
//		            .teamElderDragonKills(challengesEntity.getTeamElderDragonKills())                                        // 팀 엘더 드래곤 킬 수
//		            .teamRiftHeraldKills(challengesEntity.getTeamRiftHeraldKills())                                          // 팀 전령 킬 수
//		            .visionScorePerMinute(challengesEntity.getVisionScorePerMinute())                                        // 분당 시야 점수
//		            .goldPerMinute(challengesEntity.getGoldPerMinute())                                                      // 분당 골드
//		            .build();
//		}

		
	}
