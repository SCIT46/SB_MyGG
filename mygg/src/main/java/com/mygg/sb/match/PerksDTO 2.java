package com.mygg.sb.match;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mygg.sb.match.dto.PerkStatsDto;
import com.mygg.sb.match.dto.PerkStyleDto;
import com.mygg.sb.match.dto.PerkStyleSelectionDto;
import com.mygg.sb.match.entity.MPerkStyleEntity;
import com.mygg.sb.match.entity.MPerkStyleSelectionEntity;
import com.mygg.sb.match.entity.MPerksEntity;

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
public class PerksDTO
	{
		PerkStatsDto statPerks; // 적응형 공격력 3개 찍는 거, 사용자파편(["perks"]["statPerks"]["defense"] | ["flex"] | ["offense"]) = 5011 5008 5005
		List<PerkStyleDto> styles;	// 룬 선택 리스트 ex) [0]["selections"]["perk"] : 8345 메인룬 세팅 ex) 정복자, 감전, 비열한 한 방 등등..
									// 				  [1]["selections"]["perk"] : 834

		public void insertIntoPerksDto(JSONObject obj)
		{
			// 적응형 능력치 넣기
			statPerks = new PerkStatsDto();
			styles = new ArrayList<PerkStyleDto>();
			
			JSONObject _statPerks = ((JSONObject)(obj.get("statPerks")));
			this.statPerks.setDefense(((Long)_statPerks.get("defense")).intValue());
			this.statPerks.setFlex(((Long)_statPerks.get("flex")).intValue());
			this.statPerks.setOffense(((Long)_statPerks.get("offense")).intValue());
			
			// styles(메인룬 서브룬 넣기)
			JSONArray jarr = (JSONArray) obj.get("styles");
			
			for(int i = 0; i < jarr.size(); i++)
				{
					JSONObject jar = (JSONObject)jarr.get(i);
					PerkStyleDto _perkStyleDto = new PerkStyleDto();
					_perkStyleDto.setDescription((String) jar.get("description"));
					
					JSONArray _selections = (JSONArray)jar.get("selections");
					// 메인룬 서브룬
					for(int j = 0; j < _selections.size(); j++)
						{
							PerkStyleSelectionDto _perkStyleSelctionDto = new PerkStyleSelectionDto();
							Long perk = (Long)((JSONObject)_selections.get(i)).get("perk");
							Long var1 = (Long)((JSONObject)_selections.get(i)).get("var1");
							Long var2 = (Long)((JSONObject)_selections.get(i)).get("var2");
							Long var3 = (Long)((JSONObject)_selections.get(i)).get("var3");
							
							_perkStyleSelctionDto.setPerk(perk.intValue());
							_perkStyleSelctionDto.setVar1(var1.intValue());
							_perkStyleSelctionDto.setVar2(var2.intValue());
							_perkStyleSelctionDto.setVar3(var3.intValue());
							
							_perkStyleDto.getSelections().add(_perkStyleSelctionDto);
						}
					//  style 넣기
					_perkStyleDto.setStyle(((Long)jar.get("style")).intValue());
					
					styles.add(_perkStyleDto);
				}
		}
		
//		public static PerksDTO toDTO(MPerksEntity entity) {
//		    PerksDTO.PerksDTOBuilder perksDTOBuilder = PerksDTO.builder();
//		    
//		    // statPerks 데이터 변환
//		    perksDTOBuilder.statPerks(PerkStatsDto.builder()
//		            .defense(entity.getStatPerks().getDefense())
//		            .flex(entity.getStatPerks().getFlex())  // statPerks에서 flex 데이터 올바르게 참조
//		            .offense(entity.getStatPerks().getOffense())
//		            .build());
//		    
//		    // styles 데이터 변환
//		    List<PerkStyleDto> perkStyleDtos = new ArrayList<>();
//		    for (MPerkStyleEntity perkStyleEntity : entity.getStyles()) {  // entity.getStyles()로 수정
//		        PerkStyleDto perkStyleDto = PerkStyleDto.builder()
//		                .description(perkStyleEntity.getDescription())
//		                .style(perkStyleEntity.getStyle())
//		                .selections(new ArrayList<>())  // selections 초기화
//		                .build();
//
//		        for (MPerkStyleSelectionEntity selection : perkStyleEntity.getSelections()) {
//		            PerkStyleSelectionDto selectionDto = PerkStyleSelectionDto.builder()
//		                    .perk(selection.getPerk())
//		                    .var1(selection.getVar1())
//		                    .var2(selection.getVar2())
//		                    .var3(selection.getVar3())
//		                    .build();
//		            perkStyleDto.getSelections().add(selectionDto);
//		        }
//
//		        perkStyleDtos.add(perkStyleDto);
//		    }
//		    perksDTOBuilder.styles(perkStyleDtos);
//		    
//		    return perksDTOBuilder.build();
//		}
	}