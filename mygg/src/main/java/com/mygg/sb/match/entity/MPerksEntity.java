package com.mygg.sb.match.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document
public class MPerksEntity
	{
		MPerkStatsEntity statPerks; // 적응형 공격력 3개 찍는 거, 사용자파편(["perks"]["statPerks"]["defense"] | ["flex"] | ["offense"]) = 5011 5008 5005
		List<MPerkStyleEntity> styles;	// 룬 선택 리스트 ex) [0]["selections"]["perk"] : 8345 메인룬 세팅 ex) 정복자, 감전, 비열한 한 방 등등..
									// 				  [1]["selections"]["perk"] : 8347
		
		public void insertIntoPerksDto(JSONObject obj)
		{
			// 적응형 능력치 넣기
			statPerks = new MPerkStatsEntity();
			styles = new ArrayList<MPerkStyleEntity>();
			
			JSONObject _statPerks = ((JSONObject)(obj.get("statPerks")));
			this.statPerks.setDefense(((Long)_statPerks.get("defense")).intValue());
			this.statPerks.setFlex(((Long)_statPerks.get("flex")).intValue());
			this.statPerks.setOffense(((Long)_statPerks.get("offense")).intValue());
			
			// styles(메인룬 서브룬 넣기)
			JSONArray jarr = (JSONArray) obj.get("styles");
			
			for(int i = 0; i < jarr.size(); i++)
				{
					JSONObject jar = (JSONObject)jarr.get(i);
					MPerkStyleEntity _perkStyleDto = new MPerkStyleEntity();
					_perkStyleDto.setDescription((String) jar.get("description"));
					
					JSONArray _selections = (JSONArray)jar.get("selections");
					// 메인룬 서브룬
					for(int j = 0; j < _selections.size(); j++)
						{
							MPerkStyleSelectionEntity _perkStyleSelctionDto = new MPerkStyleSelectionEntity();
							Long perk = (Long)((JSONObject)_selections.get(i)).get("perk");
							Long var1 = (Long)((JSONObject)_selections.get(i)).get("var1");
							Long var2 = (Long)((JSONObject)_selections.get(i)).get("var2");
							Long var3 = (Long)((JSONObject)_selections.get(i)).get("var3");
							
							_perkStyleSelctionDto.setPerk(perk.intValue());
							_perkStyleSelctionDto.setVar1(var1.intValue());
							_perkStyleSelctionDto.setVar2(var2.intValue());
							_perkStyleSelctionDto.setVar3(var3.intValue());
							
							_perkStyleDto.selections.add(_perkStyleSelctionDto);
						}
					//  style 넣기
					_perkStyleDto.setStyle(((Long)jar.get("style")).intValue());
					
					styles.add(_perkStyleDto);
				}
		}
	}