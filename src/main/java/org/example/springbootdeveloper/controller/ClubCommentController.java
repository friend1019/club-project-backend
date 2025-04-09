package org.example.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.ClubComment;
import org.example.springbootdeveloper.dto.CommentRequest;
import org.example.springbootdeveloper.service.ClubCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class ClubCommentController {

    private final ClubCommentService clubCommentService;

    // 댓글 작성
    @PostMapping("/{clubId}")
    public ResponseEntity<String> addComment(@PathVariable Long clubId, @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();  // 클라이언트의 IP 주소
        String response = clubCommentService.addComment(clubId, commentRequest.getAuthor(), commentRequest.getContent(), ipAddress, commentRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    // 동아리별 모든 댓글 조회
    @GetMapping("/{clubId}")
    public ResponseEntity<List<ClubComment>> getAllComments(@PathVariable Long clubId) {
        List<ClubComment> comments = clubCommentService.getAllComments(clubId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        String response = clubCommentService.updateComment(commentId, commentRequest.getContent(), commentRequest.getPassword());
        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        String response = clubCommentService.deleteComment(commentId, commentRequest.getPassword());
        return ResponseEntity.ok(response);
    }
}
