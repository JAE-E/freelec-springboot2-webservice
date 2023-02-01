package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void 롬복_기능_테스트() {

        //given
        String name = "test";
        int amount  = 1000;

        //when // @RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다.
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); //assertThat 이라는 테스트 검증 라이브러리의 검증 메소드
        assertThat(dto.getAmount()).isEqualTo(amount); // 비교 동등 메소드 같을때만 성공.
    }
}
