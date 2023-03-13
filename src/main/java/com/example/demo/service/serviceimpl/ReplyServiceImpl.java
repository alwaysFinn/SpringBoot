package com.example.demo.service.serviceimpl;

import com.example.demo.dto.ReplyDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Reply;
import com.example.demo.repository.ReplyRepository;
import com.example.demo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;//required~~import 시 에러 삭제


    //댓글 등록
    @Override
    public Long register(ReplyDTO replyDTO) {

        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    //댓글 읽기
    @Override
    public List<ReplyDTO> getList(Long bno) { //bno를 기준으로 bno를 기준으로 생성

        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    //댓글 수정
    @Override
    public void modify(ReplyDTO replyDTO) { //dto를 entity로 변환

        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    //댓글 삭제
    @Override
    public void remove(Long rno) { replyRepository.deleteById(rno);}
}
