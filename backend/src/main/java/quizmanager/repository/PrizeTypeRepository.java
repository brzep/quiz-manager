package quizmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quizmanager.model.prize.PrizeType;

import java.util.List;

@Repository
public interface PrizeTypeRepository extends JpaRepository<PrizeType, Integer> {

    @Query("select prizeType.name from PrizeType prizeType")
    List<String> getPrizeTypeNames();

}