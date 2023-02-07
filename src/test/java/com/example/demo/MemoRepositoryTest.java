package com.example.demo;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void InsertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample..." + i)
                    .build();
            //Create!
            memoRepository.save(memo); //save = commit
        });
    }

    @Test
    public void ReadDummies() {

        Long mno = 10L;
        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("===================================");

        if(result.isEmpty()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    public void UpdateDummies() {
        Memo memo = Memo.builder()
                .mno(10L)
                .memoText("Update Text")
                .build();

        memoRepository.save(memo);
    }

    @Test
    public void DeleteDummies() {
        Long mno = 10L;

        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault(){

        // 1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);  //0 : start page, 10 : size
        Page<Memo> result = memoRepository.findAll(pageable); //Repository = Dao같은 느낌 -> DB와 JAVA를 연결해주는 (=JPA)
        System.out.println(result);
        System.out.println("-----------------------------------------");
        System.out.println("Total Pages: " + result.getTotalPages());       // 총 몇 페이지
        System.out.println("Total Count: " + result.getTotalElements());    // 전체 개수
        System.out.println("Page Number: " + result.getNumber());           // 현재 페이지 번호 0부터 시작
        System.out.println("Page Size: " + result.getSize());               // 페이지당 데이터 개수
        System.out.println("has next page?: " + result.hasNext());          // 다음 페이지 존재 여부
        System.out.println("first page?: " + result.isFirst());             // 시작 페이지(0) 여부
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);    // and를 이용해서 sort1, sort2를 연결
            Pageable pageable = PageRequest.of(0, 10, sortAll); // 0붙터 10개 페이지로 이루어져있고, sortAll을 이용해서 가져욤
            Page<Memo> result = memoRepository.findAll(pageable); //실제 DB에서 가져옴

            result.get().forEach(memo -> { //출력용
                System.out.println(memo);
            });
    }
    @Test
    public void testQueryMethods() { //진짜 DB랑 connect해서 DB값 가져옴
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(7L, 8L);

        for (Memo memo : list){
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending()); //시작, 보여줄 숫자, mno를 기준으로 정렬
        Page<Memo> result = memoRepository.findByMnoBetween(1L, 5L, pageable); // 1~5 pageable을 기준으로 정렬해서 보여줘라
        result.get().forEach(memo -> System.out.println(memo)); // 위에서 정렬된거를 result에 담고, 반복문으로 하나씩 출력
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoryByMnoLessThan(9L);
    }

}
