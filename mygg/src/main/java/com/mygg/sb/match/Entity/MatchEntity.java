package com.mygg.sb.match.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Matches")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId; // Primary Key

    @Column(nullable = false, unique = true)
    private String matchName;

    @Column
    private String jsonData;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<UserMatchEntity> userMatches;

    // Getters, Setters, 기본 생성자 생략
}
