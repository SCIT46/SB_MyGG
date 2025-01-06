package com.mygg.sb.statics.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
	{
		@Bean
		public ModelMapper modelMapper()
			{
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setFieldMatchingEnabled(true) // 필드 이름으로 매핑 활성화
						.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE); // private 필드 접근
																										// 허용
				return modelMapper;
			}
	}