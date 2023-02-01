package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter            // 롬복의 어노테이션 , 필드의 게터 메소드 자동생성
@NoArgsConstructor // 롬복의 어노테이션, 기본 생성자 추가.
@Entity            // jpa의 어노테이션 ( 테이블과 링크될 클래스 ) , 카멜 케이스의 이름을 언더스코어 네이밍으로 매칭~
public class User extends BaseTimeEntity {
    // but, table의 컬럼을 굳이 선언하지않더라도 해당 클래스의 필드는 모두 컬럼이 됩니다.

    // Id는 table의 pk 필드를 나타냄.
    // pk 타입은 long으로 되도록이면, mysql기준으로는 bigint가됩니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament를 의미 !
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING) // enum ~ :: jpa로 db 저장할때 enum값을 어떤 형태로 저장할지 결정! 기본적으로는요 int로 된 숫자가 저장이되고요, 숫자로 저장할때 db에 그 값이 무슨 코드를 의미하는지 알수가 없으요, 그래서! 문자열로 저장될수있도록 선언 합니다. EnumType.STRING
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
