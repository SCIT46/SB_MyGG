package com.mygg.sb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//react 의 csr 을 처리하기 위한 configuration
// WebMvcConfigurer는 Spring MVC의 동작을 커스터마이징하기 위해 제공되는 인터페이스 이며 메서드구현을 통해 요청과 뷰 메핑을 정의 가능
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	//Spring MVC에서 요청 경로와 뷰 이름을 연결
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	//URL 경로가 /example, /search와 같이 단일 경로인 경우를 처리
        registry.addViewController("/{spring:\\w+}").setViewName("forward:/index.html");
        //경로가 여러 계층으로 구성된 경우를 처리 예: /user/profile, /category/item/details 등
        registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/index.html");
        //경로가 여러 계층으로 구성되었으며, 마지막 경로가 특정 확장자(.js, .css, .ico, .png)가 아닌 경우에 매칭
        registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css|\\.ico|\\.png)$}")
        		// index.html로 전달
                .setViewName("forward:/index.html");
    }
}