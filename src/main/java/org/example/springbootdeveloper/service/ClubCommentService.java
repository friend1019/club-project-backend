package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.ClubComment;
import org.example.springbootdeveloper.repository.ClubCommentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubCommentService {

    private final ClubCommentRepository clubCommentRepository;

    // 댓글 작성
    public String addComment(Long clubId, String author, String content, String ipAddress, String password) {

        // 필수 입력값 검사
        if (author == null || author.trim().isEmpty()) {
            return "작성자 이름을 입력해주세요.";
        }
        if (content == null || content.trim().isEmpty()) {
            return "댓글 내용을 입력해주세요.";
        }
        if (password == null || password.trim().isEmpty()) {
            return "비밀번호를 입력해주세요.";
        }

        // 중복 댓글 체크
        Optional<ClubComment> existingComment = clubCommentRepository.findByClubIdAndIpAddress(clubId, ipAddress);
        if (existingComment.isPresent()) {
            return "이미 댓글을 작성한 사용자입니다.";
        }
        //댓글 추가
        ClubComment newComment = new ClubComment(null, clubId, ipAddress, author, content, password);
        newComment.setPassword(password); // 비밀번호 설정
        clubCommentRepository.save(newComment);
        return "댓글이 작성되었습니다.";
    }

    // 댓글 수정
    public String updateComment(Long commentId, String newContent, String password) {
        Optional<ClubComment> existingComment = clubCommentRepository.findById(commentId);
        if (existingComment.isEmpty()) {
            return "댓글이 존재하지 않습니다.";
        }
        //비번 일치 여부 확인
        ClubComment comment = existingComment.get();
        if (!comment.getPassword().equals(password)) {
            return "비밀번호가 일치하지 않습니다.";
        }
        //댓글 수정
        comment.setContent(newContent); // 댓글 내용 수정
        clubCommentRepository.save(comment);
        return "댓글이 수정되었습니다.";
    }

    // 댓글 삭제
    public String deleteComment(Long commentId, String password) {
        Optional<ClubComment> existingComment = clubCommentRepository.findById(commentId);
        if (existingComment.isEmpty()) {
            return "댓글이 존재하지 않습니다.";
        }
        //비번 일치 여부 확인
        ClubComment comment = existingComment.get();
        if (!comment.getPassword().equals(password)) {
            return "비밀번호가 일치하지 않습니다.";
        }
        //댓글 삭제
        clubCommentRepository.delete(comment);
        return "댓글이 삭제되었습니다.";
    }
    // 동아리별 모든 댓글 조회
    public List<ClubComment> getAllComments(Long clubId) {
        return clubCommentRepository.findByClubId(clubId);
    }
}
