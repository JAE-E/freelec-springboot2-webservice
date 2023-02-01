package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest // 사용시, H2 데이터베이스를 자동으로 실행
public class PostRepositoryTest {

    @Autowired // 의존성 주입
    PostsRepository postRepository;

    @After // Junit에서 단위 테스트가 끝날때마다 수행되는 메소드를 지정 / 보통 배포전에 전체 테스트 수행 할때 테스트간의 대이터침범막으려고~
    // 여러 테스트 동시에 수행시 테스트용 db가 h2에 남아있어 실행시 테스트가 실패할수가잇서요...
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        //given
        String title ="test게시글";
        String content = "테스트본문";

        // 테이블posts에 insert, update쿼리를 실행합니다. id값이있다면 update, 없다면 insert....오호,,,bb
        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드.... !

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);  // assertj의 동등 비교 메소드.... assetThat에 있는 값과 isEqualTo값이 같으면 성공.
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(Posts.builder().title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createData = "+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);  // assertj의 동등 비교 메소드.... assetThat에 있는 값과 isEqualTo값이 같으면 성공.
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
