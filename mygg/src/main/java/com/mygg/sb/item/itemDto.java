package com.mygg.sb.item;

import lombok.Setter;

import java.util.HashMap;

import lombok.Getter;

@Getter
@Setter
public class itemDto {
    //field
    //아이템 아이디
    private String id;
    //아이템 이름
    private String name;
    //아이템 설명
    private String description;
    //아이템 설명(평문)
    //private String plaintext;
    //아이템 제작소비 아이템 아이디
    private String[] from;
    //아이템 제작 아이템 아이디
    private String[] into;
    //아이템 골드 정보
    private goldDto gold;
    //아이템 태그
    //private ArrayList<String> tags;
    //아이템 사용되는 맵
    //private String[] maps;
    //아이템 특수 레시피
    //private String[] specialRecipe;
    //아이템 성능(효과) 정보
    //private statsDto stats;
    private HashMap<String, Number> stats;

    public itemDto(){
        gold = new goldDto();
    }
}


// stats
//아이템 통계 정보

    // //아이템 체력 총합
    // private int flatHPPoolMod;
    // //아이템 체력 증가량
    // private int rFlatHPModPerLevel;
    // //아이템 체력 퍼센트
    // private int percentHPPoolMod;

    // //아이템 마나 총합
    // private int flatMPPoolMod;
    // //아이템 마나 증가량
    // private int rFlatMPModPerLevel;
    // //아이템 마나 퍼센트
    // private int percentMPPoolMod;

    // //아이템 체력 재생량
    // private int flatHPRegenMod;
    // //아이템 체력 재생량 증가량
    // private int rFlatHPRegenModPerLevel;
    // //아이템 체력 재생량 퍼센트
    // private int percentHPRegenMod;

    // //아이템 마나 재생량
    // private int flatMPRegenMod;
    // //아이템 마나 재생량 증가량
    // private int rFlatMPRegenModPerLevel;
    // //아이템 마나 재생량 퍼센트
    // private int percentMPRegenMod;

    // //아이템 방어력
    // private int flatArmorMod;
    // //아이템 방어력 증가량
    // private int rFlatArmorModPerLevel;
    // //아이템 방어력 퍼센트
    // private int percentArmorMod;

    // //아이템 방어력 관통
    // private int rFlatArmorPenetrationMod;
    // //아이템 방어력 관통 증가량
    // private int rFlatArmorPenetrationModPerLevel;
    // //아이템 방어력 관통 퍼센트
    // private int rPercentArmorPenetrationMod;
    // //아이템 방어력 관통 퍼센트 증가량
    // private int rPercentArmorPenetrationModPerLevel;

    // //아이템 물리 데미지
    // private int flatPhysicalDamageMod;
    // //아이템 물리 데미지 증가량
    // private int rFlatPhysicalDamageModPerLevel;
    // //아이템 물리 데미지 퍼센트
    // private int percentPhysicalDamageMod;

    // //아이템 마법 데미지
    // private int flatMagicDamageMod;
    // //아이템 마법 데미지 증가량
    // private int rFlatMagicDamageModPerLevel;
    // //아이템 마법 데미지 퍼센트
    // private int percentMagicDamageMod;

    // //아이템 이동속도
    // private int flatMovementSpeedMod;
    // //아이템 이동속도 증가량
    // private int rFlatMovementSpeedModPerLevel;
    // //아이템 이동속도 퍼센트
    // private int percentMovementSpeedMod;
    // //아이템 이동속도 퍼센트 증가량
    // private int rPercentMovementSpeedModPerLevel;

    // //아이템 공격속도
    // private int flatAttackSpeedMod;
    // //아이템 공격속도 퍼센트
    // private int percentAttackSpeedMod;
    // //아이템 공격속도 퍼센트 증가량
    // private int rPercentAttackSpeedModPerLevel;

    // //아이템 회피
    // private int rFlatDodgeMod;
    // //아이템 회피 증가량
    // private int rFlatDodgeModPerLevel;
    // //아이템 회피 퍼센트
    // private int percentDodgeMod;

    // //아이템 치명타 확률
    // private int flatCritChanceMod;
    // //아이템 치명타 확률 증가량
    // private int rFlatCritChanceModPerLevel;
    // //아이템 치명타 확률 퍼센트
    // private int percentCritChanceMod;   

    // //아이템 치명타 데미지
    // private int flatCritDamageMod;
    // //아이템 치명타 데미지 증가량
    // private int rFlatCritDamageModPerLevel;
    // //아이템 치명타 데미지 퍼센트
    // private int percentCritDamageMod;

    // //아이템 블럭
    // private int flatBlockMod;
    // //아이템 블럭 퍼센트
    // private int percentBlockMod;

    // //아이템 마법 블럭
    // private int flatSpellBlockMod;
    // //아이템 마법 블럭 증가량
    // private int rFlatSpellBlockModPerLevel;
    // //아이템 마법 블럭 퍼센트
    // private int percentSpellBlockMod;

    // //아이템 경험치
    // private int flatEXPBonus;
    // //아이템 경험치 퍼센트
    // private int percentEXPBonus;
    
    // //아이템 쿨타임 퍼센트
    // private int rPercentCooldownMod;
    // //아이템 쿨타임 퍼센트 증가량
    // private int rPercentCooldownModPerLevel;

    // //아이템 시체 소멸 시간
    // private int rFlatTimeDeadMod;
    // //아이템 시체 소멸 시간 증가량
    // private int rFlatTimeDeadModPerLevel;
    // //아이템 시체 소멸 시간 퍼센트
    // private int rPercentTimeDeadMod;
    // //아이템 시체 소멸 시간 퍼센트 증가량
    // private int rPercentTimeDeadModPerLevel;

    // //아이템 골드 퍼센트
    // private int rFlatGoldPer10Mod;

    // //아이템 마법 관통
    // private int rFlatMagicPenetrationMod;
    // //아이템 마법 관통 증가량
    // private int rFlatMagicPenetrationModPerLevel;
    // //아이템 마법 관통 퍼센트
    // private int rPercentMagicPenetrationMod;
    // //아이템 마법 관통 퍼센트 증가량
    // private int rPercentMagicPenetrationModPerLevel;

    // //아이템 에너지 재생량
    // private int flatEnergyRegenMod;
    // //아이템 에너지 재생량 증가량
    // private int rFlatEnergyRegenModPerLevel;

    // //아이템 에너지 퍼센트
    // private int percentLifeStealMod;
    // //아이템 에너지 퍼센트 증가량
    // private int percentSpellVampMod;

    // //아이템 에너지 총합
    // private int flatEnergyPoolMod;
    // //아이템 에너지 총합 증가량
    // private int rFlatEnergyModPerLevel;