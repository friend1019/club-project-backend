package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.ClubLike;
import org.example.springbootdeveloper.repository.ClubLikeRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubLikeService {

    private final ClubLikeRepository clubLikeRepository;

    // 좋아요 추가 다시누르면 취소
    public String toggleLike(Long clubId) {
        // clubId에 해당하는 좋아요 여부 확인
        Optional<ClubLike> existingLike = clubLikeRepository.findByClubId(clubId);

        if (existingLike.isPresent()) {
            clubLikeRepository.delete(existingLike.get());  // 좋아요 취소
            return "좋아요가 취소되었습니다.";
        } else {
            ClubLike newLike = new ClubLike(null, clubId);  // IP 관련 필드 제거
            clubLikeRepository.save(newLike);  // 좋아요 추가
            return "좋아요가 추가되었습니다.";
        }
    }

    // 동아리별 좋아요 개수 조회
    public long getLikeCount(Long clubId) {
        return clubLikeRepository.countByClubId(clubId);
    }
}
