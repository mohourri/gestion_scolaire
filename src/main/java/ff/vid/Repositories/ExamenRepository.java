package ff.vid.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ff.vid.entities.Examen;

public interface ExamenRepository extends JpaRepository<Examen,Long> {
}