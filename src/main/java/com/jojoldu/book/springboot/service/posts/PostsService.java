package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다. (중요!!!!!!!!!!!!!!!!!!!!!!!!!)
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // why, update에는 쿼리 날리는 (builder하는 부분이) 없을까요? ==> 이것에 대한 대답은 : 엔티티를 영구 저장하는 환경이기때문에
    // 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈립니다 (무슨소리?) ==> jpa의 엔티티매니저가 활성화된 상태로 트랜잭션 안에서 데이터 베이스에서 데이터를 가져오면
    // 이 데이터는 영속성 컨텍스트가 유지된 상태 -----> 즉 트랜잭션이 끝나는 시점에 해당 테이블에 변경한 부분을!!!!!!!!!!!!반영하게 된다는 말알알ㅇ랑랑라알 씀!
    // 즉, Entity 객체의 값만 변경하면 별도로 update쿼리를 날릴 필요가 ㅇ벗고, 이 개념을 더티체킹이라고 합니다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. ID="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readonly를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨서 조회 속도가 개선되기때문에, CUD, 전혀 없는 서비스 메소드에서 사용하는것을추천.
    public List<PostsListResponseDto> findAllDesc() {
        //람다식 ::::: .map(PostsListResponseDto::new) ==> .map(posts -> new PostsListResponseDto(posts))
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //JpaRepository에서 이미 delete 메소드를 지원!
        postsRepository.delete(posts);
    }
}
