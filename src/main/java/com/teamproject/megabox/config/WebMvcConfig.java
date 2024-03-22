package com.teamproject.megabox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${uploadPath}")//file///c:/shop  <-롬복아님
    String uploadPath;

    //로컬 컴퓨터에 업로드한 파일을 찾을 위치를 설정
    //images라는 요청이 로면 uploadPath에서 찾겠다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }



}
