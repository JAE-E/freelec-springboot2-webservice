package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 보통  Entity가 CreatedDate, modifiedDate 을 포합합니다. (중요해서그런가봐요)
@Getter
@MappedSuperclass                               // JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 합니다 (CreatedDate, modifiedDate)
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity class에 auditing 기능을 포함시킵니다.
public abstract class BaseTimeEntity {

    @CreatedDate // entity가 생성되어 저장될때 시간이 자동 저장됩니당.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 entity의 값을 변경할때 시간이 자동 저장됩니다.
    private LocalDateTime modifiedDate;

}
