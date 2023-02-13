package com.example.demo.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// create Table( X ) , 시간 설정을 위한 Table( O )
@MappedSuperclass //entity임에도 불구하고 테이블 생성 안한다는 명령
@EntityListeners(value = {AuditingEntityListener.class}) //연결된 Table의 업데이트를 인지
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false) //updatable = false는 기본적으로 업데이트 시 같이 업데이트 하지 않는다는 뜻
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}