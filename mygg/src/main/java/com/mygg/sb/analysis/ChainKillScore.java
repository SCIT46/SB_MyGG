package com.mygg.sb.analysis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Setter
@Data
public class ChainKillScore {
    private int doubleKill;
    private int tripleKill;
    private int quadraKill;
    private int pentaKill;
}
