package pe.com.gymconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.gymconnect.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    @SuppressWarnings("null")
    Page<Goal> findAll(Pageable pageable);

    @Query(value = "SELECT COALESCE(MAX(g.code), 0) + 1 FROM Goal g WHERE g.gym.id = :gymId")
    Integer generarCodigo(@Param("gymId") Long gymId);

    Boolean existsByName(String name);

}
