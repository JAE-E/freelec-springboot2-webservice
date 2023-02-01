package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 슾부트테스트와 junit사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class) // web mvc 집중할수있는 어노테이션 선언할 경우 contorller등 사용가능
// 단 service, component, repository 등은 사용 불가능.
// 여기선 컨트롤러만 사용하니깐 사용 가능.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받음.
    private MockMvc mvc; // 웹 api 테스트시 사용 테스트의 시작점.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // http get 요청합니다,
        // 체이닝이 지원되어 아래와 같은 여러 검증 기능을 이어서 선언 가능
        // http header status를 검증합니다. 200, 404, 500 등의 상태를 검증합니다.
        // 즉 200인지 아닌지를 검증합니다.
        // 결과를 검증합니다, 응답 본문의 내용을 검증합니다 Controller에서 hello를 리턴하기 때문ㅇㅔ 이 값이 맞는지 검증합니다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 10000000;

        // params는 int x string만 가능
        mvc.perform(get("/hello/dto")
                .param("name",name)
                .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name",is(name)))
                    .andExpect(jsonPath("$.amount", is(amount)));
    }
}
