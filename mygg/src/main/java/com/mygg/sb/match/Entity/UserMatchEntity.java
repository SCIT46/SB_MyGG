package com.mygg.sb.match.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "UserMatch")
public class UserMatchEntity
	{
		// 중간다리 역할의 Entity이다
		// userId로 matchID(index)를 조회하는 등의 중간다리.
		@Column(nullable = false, unique = true)
		private int user_id;
		
		@Column(nullable = false, unique = true)
		private int match_id;
	}
