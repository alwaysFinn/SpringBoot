package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor //여기까지는 lombok의 기능 getter, setter, ...
@Table(name = "tbl_memo") //DB에 생성할 테이블 이름 -> tbl_memo라는 이름의 테이블이 생성
@Entity //JPA가 관리하며, 테이블과의 매핑
public class Memo {

    @Id //PK로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique임을 지정
    private Long mno;

    @Column(length = 200, nullable = false) // 길이는 200, notnull 지정
    private String memoText;

}
