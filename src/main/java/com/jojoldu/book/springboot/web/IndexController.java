package com.jojoldu.book.springboot.web;
import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import org.springframework.ui.Model;

import com.jojoldu.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

// post 생성(create), get 읽기(read), put 수정(update), delete 삭제(delete)
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다. (중요!!!!!!!!!!!!!!!!!!!!!!!!!)
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // model :: 서버 템플릿 엔진에서 사용할수있는 객체를 저장할수가 있서요.. 가져온 결과를 front에 붙여요 어트리뷰트로용.... !
        model.addAttribute("posts",postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user"); // CustomOAuth2UserService에서 로그인 성공시, 세션에 SessionUser를 저장하도록 구성. 즉 로그인 성공시 세션에서 user값을 가져올수가잇서요

        if(user != null) { // 세션에 저장된 값이 있을때만, model에 userName으로 등록합니다. 세션에 저장된 값이 없으면 model엔 아무값이없는상태이므로 로그인버튼이보이겠ㅈ?
            model.addAttribute("userName", user.getName());
        }
        return "index"; // 문자열을 반환시 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됩니다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";  // 문자열을 반환시 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됩니다.
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto); // 수정하기 전 default data를 지정해주는거지~

        return "posts-update";  // 문자열을 반환시 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됩니다.
    }

}