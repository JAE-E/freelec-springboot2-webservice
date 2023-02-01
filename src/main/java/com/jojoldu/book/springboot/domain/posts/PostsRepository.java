package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository <Entity 클래스, pk타입> 을 상속하면,!!!!!!!!!!!! 기!본!적!인 crud 메소드!!!!!!!!!!!!!가 자동으로 생성!!!!!!!!!!!!!!!!!
// @Repository 추가할 필요 x, 주의 할 점은 entity 클래스와 기본 entity repository는 함께 존재해야합니당.
// entity 클래스는 기본 repository없이는 제대로 역할을 할수가 x,
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
