package ff.vid.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ff.vid.entities.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {

}
