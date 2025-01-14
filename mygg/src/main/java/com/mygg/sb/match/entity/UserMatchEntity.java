package com.mygg.sb.match.entity;

import jakarta.persistence.*;
import lombok.Getter;

import com.mygg.sb.user.UserEntity;

@Entity
@Table(name = "UserMatch")
@Getter
public class UserMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 중간 테이블의 Primary Key

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // UserEntity와 연결

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private MatchEntity match; // MatchEntity와 연결

    // Getters, Setters, 기본 생성자 생략
    // name: DB에서 저장될 컬럼의 이름
    // 외래키는 기본적으로 지정해주면 그것의 기본키를 참조한다
}
