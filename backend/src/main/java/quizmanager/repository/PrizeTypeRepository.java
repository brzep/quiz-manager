package quizmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quizmanager.model.prize.PrizeType;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrizeTypeRepository extends JpaRepository<PrizeType, Integer> {

    @Query("select prizeType.name from PrizeType prizeType")
    List<String> getPrizeTypeNames();

    Optional<PrizeType> findByName(String name);

    List<PrizeType> findPrizeTypesByNameIsIn(Collection<String> name);

}
