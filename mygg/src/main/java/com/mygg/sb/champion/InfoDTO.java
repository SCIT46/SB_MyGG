package com.mygg.sb.champion;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class InfoDTO {
    //field
    private int attack;
    private int defense;
    private int magic;
    private int difficulty;

}
