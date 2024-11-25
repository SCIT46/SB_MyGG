package com.mygg.sb.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class goldDto {
    //아이템 골드 정보
    //아이템 골드 기본값 
    private int base;
    //아이템 골드 총합
    private int total;
    //아이템 골드 판매값
    private int sell;
    //아이템 골드 구매 가능 여부
    private boolean purchasable;
}
