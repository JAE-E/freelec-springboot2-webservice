package com.jojoldu.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter            // 롬복의 어노테이션 , 필드의 게터 메소드 자동생성
@NoArgsConstructor // 롬복의 어노테이션, 기본 생성자 추가.
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
