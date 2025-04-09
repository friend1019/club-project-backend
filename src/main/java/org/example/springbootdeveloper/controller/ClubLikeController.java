package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.service.ClubLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class ClubLikeController {

    private final ClubLikeService clubLikeService;

    // 좋아요 추가 또는 취소
    @PostMapping("/{clubId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long clubId) {
        String response = clubLikeService.toggleLike(clubId);  // 클라이언트의 IP 주소는 이제 필요 없음
        return ResponseEntity.ok(response);
    }

    // 동아리별 좋아요 개수 조회
    @GetMapping("/{clubId}")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long clubId) {
        return ResponseEntity.ok(clubLikeService.getLikeCount(clubId));
    }
}
