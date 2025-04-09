package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.ClubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClubCommentRepository extends JpaRepository<ClubComment, Long> {
    //동아리 id로 댓글들 조회
    List<ClubComment> findByClubId(Long clubId);
    //1 동아리, 1 ip, 1 댓글인지 확인
    Optional<ClubComment> findByClubIdAndIpAddress(Long clubId, String ipAddress);
}
