package com.example.demo.repository;

import com.example.demo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long form, Long to);//정렬하는 sortAll와 동일 but 이건 DB에서 실제 값 가져옴

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);//위에서 정렬된 것을 가져와서 페이지 보여줌

    void deleteMemoryByMnoLessThan(Long num);//num보다 작은것 모두 삭제
}
