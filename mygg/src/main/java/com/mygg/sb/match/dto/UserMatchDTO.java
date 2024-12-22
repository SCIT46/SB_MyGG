package com.mygg.sb.match.dto;


import com.mygg.sb.match.Entity.MatchEntity;
import com.mygg.sb.match.Entity.UserMatchEntity;
import com.mygg.sb.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserMatchDTO
	{
	    private Long id; 			// 중간 테이블의 Primary Key
	    private UserEntity user; 	// UserEntity와 연결
	    private MatchEntity match; // MatchEntity와 연결
	    
		public static UserMatchDTO toDTO(UserMatchEntity userEntity)
			{
				return UserMatchDTO.builder()
						.id(userEntity.getId())
						.user(userEntity.getUser())
						.match(userEntity.getMatch()).build();
			}
	}
