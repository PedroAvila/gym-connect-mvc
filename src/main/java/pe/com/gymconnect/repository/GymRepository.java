package pe.com.gymconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.gymconnect.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long> {

    @SuppressWarnings("null")
    Page<Gym> findAll(Pageable pageable);

    @Query(value = "SELECT COALESCE(MAX(g.code), 0) + 1 FROM Gym g")
    Integer generarCodigo();

    Boolean existsByName(String name);

}
