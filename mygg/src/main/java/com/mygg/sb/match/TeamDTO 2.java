package com.mygg.sb.match;

import java.util.List;

import com.mygg.sb.match.dto.BanDTO;
import com.mygg.sb.match.dto.ObjectDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO
	{
		List<BanDTO> bans; // 밴한 것들
		ObjectDTO objectives; // 먹은 오브젝트들
		int teamId; // 팀ID
		boolean win; // 승리여부

//			public static TeamDTO toDTO(MTeamEntity entity)
//			{
//				List<BanDTO> list = new ArrayList<>();
//			    entity.getBans().forEach(item -> {
//			        BanDTO banDTO = new BanDTO(item.getChampionId(), item.getPickTurn());  // BanDTO 객체 생성
//			        list.add(banDTO);  // 생성된 BanDTO를 리스트에 추가
//			    });
//				
//				return TeamDTO.builder()
//			            .bans(list)  // 변환된 BanDTO 리스트
//			            .objectives(ObjectDTO.toDTO(entity.getObjectives()))  // MObjectEntity -> ObjectDTO 변환
//			            .teamId(entity.getTeamId())  // teamId 설정
//			            .win(entity.isWin())  // 승리 여부 설정
//			            .build();  // 최종 객체 반환
//			}
	}