package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_memo") //DB에 생성할 테이블 이름
@Entity
public class Memo {

    @Id //PK로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique임을 지정
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

}
