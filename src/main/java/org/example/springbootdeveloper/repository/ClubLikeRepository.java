package org.example.springbootdeveloper.repository;
import org.example.springbootdeveloper.domain.ClubLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClubLikeRepository extends JpaRepository<ClubLike, Long> {

    // 동아리 ID를 기준으로 좋아요 여부를 확인
    Optional<ClubLike> findByClubId(Long clubId);

    // 동아리별 좋아요 개수 조회
    long countByClubId(Long clubId);
}
