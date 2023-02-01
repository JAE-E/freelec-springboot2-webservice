package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 각 사용자의 권한을 관리할 Enum 클래스 Role을 생성합니다.
@Getter                  // 롬복의 어노테이션 , 필드의 게터 메소드 자동생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다. (중요!!!!!!!!!!!!!!!!!!!!!!!!!)
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;

    private final String title;

    // 스프링 시큐리티에서는 권한코드에 항상 ROLE_ 이 앞에 잇서야만해요..
    // 코드별 키 값을 ROLE_GUEST, ROLE_USER 등으로 지정합니다.

}
