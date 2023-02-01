package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing     // JPA의 AUDITING 활.성.화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을 모두 자동으로 설정.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); // 내장 WAS를 실행합니다. (스프링 부트로 만들어진 JAR 파일을 실행하면 됩니다
    }
}
