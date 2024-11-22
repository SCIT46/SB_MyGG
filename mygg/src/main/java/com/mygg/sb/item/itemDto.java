package com.mygg.sb.item;

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
    private String[] tags;
    //아이템 사용되는 맵
    //private String[] maps;
    //아이템 특수 레시피
    private String[] specialRecipe;ㅑ
    //아이템 통계 정보
    private statsDto stats;
}
