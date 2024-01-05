package quizmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    @Query("update Record r set r.prize.id = ?2 where r.id = ?1")
    void updatePrizeId(Integer RecordId, Integer prizeId);

}
