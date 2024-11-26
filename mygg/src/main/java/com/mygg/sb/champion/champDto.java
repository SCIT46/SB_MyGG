package com.mygg.sb.champion;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class champDto {
    //field
    private String id;
    private String key;
    private String name;
    private String title;
    private String blurb;
    private infoDto info;
    //private String[] tags;
    private HashMap<String, Number> stats;

    public champDto(){
        info = new infoDto();
    }
}
