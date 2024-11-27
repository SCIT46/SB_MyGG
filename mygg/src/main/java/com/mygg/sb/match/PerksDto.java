package com.mygg.sb.match;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerksDto
	{
		PerkStatsDto statPerks = new PerkStatsDto(); // 적응형 공격력 3개 찍는 거, 사용자파편(["perks"]["statPerks"]["defense"] | ["flex"] | ["offense"]) = 5011 5008 5005
		List<PerkStyleDto> styles;	// 룬 선택 리스트 ex) [0]["selections"]["perk"] : 8345 메인룬 세팅 ex) 정복자, 감전, 비열한 한 방 등등..
									// 				  [1]["selections"]["perk"] : 8347
		@Override
		public String toString()
			{
				return "PerksDto [statPerks=" + statPerks + ", styles=" + styles + "]";
			}

		public void insertIntoPerksDto(JSONObject obj)
		{
			// 적응형 능력치 넣기
			
			JSONObject _statPerks = ((JSONObject)(obj.get("statPerks")));
			this.statPerks.setDefense(((Long)_statPerks.get("defense")).intValue());
			this.statPerks.setFlex(((Long)_statPerks.get("flex")).intValue());
			this.statPerks.setOffense(((Long)_statPerks.get("offense")).intValue());
			
			// styles(메인룬 서브룬 넣기)
			JSONArray jarr = (JSONArray) obj.get("styles");
			styles = new ArrayList<PerkStyleDto>();
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
							
							_perkStyleDto.selections.add(_perkStyleSelctionDto);
						}
					//  style 넣기
					_perkStyleDto.setStyle(((Long)jar.get("style")).intValue());
					
					styles.add(_perkStyleDto);
				}
		}
	}

@Getter
@Setter
class PerkStyleDto
{
	@Override
	public String toString()
		{
			return "PerkStyleDto [description=" + description + ", selections=" + selections + ", style=" + style + "]";
		}
	String description;	// 설명
	List<PerkStyleSelectionDto> selections = new ArrayList<>();
	int style;
}

@Setter
@Getter
class PerkStatsDto 
{
	// Name	Data Type	Description
	int defense;		
	int	flex;	
	int offense;	
}

@Getter
@Setter
class PerkStyleSelectionDto 
{
	int perk;		
	int var1;		
	int var2;		
	int var3;	
	
	@Override
	public String toString()
		{
			return "PerkStyleSelectionDto [perk=" + perk + ", var1=" + var1 + ", var2=" + var2 + ", var3=" + var3 + "]";
		}
}