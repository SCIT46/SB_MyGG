package com.mygg.sb.match.dto;


import com.mygg.sb.match.MatchDTO;
import com.mygg.sb.match.entity.MatchEntity;
import com.mygg.sb.match.entity.UserMatchEntity;
import com.mygg.sb.user.UserDTO;
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
	    private Long id; 		// 중간 테이블의 Primary Key
	    private UserDTO user; 	// UserEntity와 연결
	    private MatchDTO match; // MatchEntity와 연결
	    
//		public static UserMatchDTO toDTO(UserMatchEntity userEntity)
//			{
//				return UserMatchDTO.builder()
//						.id(userEntity.getId())
//						.user(UserDTO.toDTO(userEntity.getUser()))
//						.match(MatchDTO.toDTO(userEntity.getMatch())).build();
//			}
	}
