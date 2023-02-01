package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션을 클래스에 가까이 둔다.
@Getter            // 롬복의 어노테이션 , 필드의 게터 메소드 자동생성
@NoArgsConstructor // 롬복의 어노테이션, 기본 생성자 추가.
@Entity            // jpa의 어노테이션 ( 테이블과 링크될 클래스 ) , 카멜 케이스의 이름을 언더스코어 네이밍으로 매칭~
public class Posts extends BaseTimeEntity { // 시간관련 class가 상속받도록 변경되었숨다?

    @Id // 해당 table의 pk 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment 를 의미.
    private Long id; // pk의 타입은 long으로 잡는다 되도록이면 (mysql기준으로 bigint의 타입이 되기도 합니다),

    // Table의 컬럼을 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다. / 그럼 왜 굳이 사용하냐? 렝스가 255인데 500으로 늘리고 싶거나 할시 쓰면 됩니당..


    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
