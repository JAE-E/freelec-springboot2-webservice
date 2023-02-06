package com.jojoldu.book.springboot.config.auth.dto;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;
import java.io.Serializable;
// 인증된! 사용자 정보만을 필요로 하여서 나머지 정보들은 필요가 없어서 선언조차 하지 않음.
@Getter // 선언된 모든 필드의 get 메소드를 생성.
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    // 생성자
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
