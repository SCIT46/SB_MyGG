package com.mygg.sb.champion;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChampDTO {
    //field
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private InfoDTO info;
    //private String[] tags;
    private HashMap<String, Number> stats;
    private SpellDTO[] spells;

    public ChampDTO(){
        info = new InfoDTO();
        spells = new SpellDTO[4];
    }
}
