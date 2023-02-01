package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository <Entity 클래스, pk타입> 을 상속하면,!!!!!!!!!!!! 기!본!적!인 crud 메소드!!!!!!!!!!!!!가 자동으로 생성!!!!!!!!!!!!!!!!!
// @Repository 추가할 필요 x, 주의 할 점은 entity 클래스와 기본 entity repository는 함께 존재해야합니당.
// entity 클래스는 기본 repository없이는 제대로 역할을 할수가 x,
public interface UserRepository extends JpaRepository<User, Long> {

    // 와...이런게잇서..?????....음...뭔지 하면서알아야할듯.
    // findByEmail : 소셜로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기위한 메소드
    Optional<User> findByEmail(String email);
}
