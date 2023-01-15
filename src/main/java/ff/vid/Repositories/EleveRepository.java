package ff.vid.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ff.vid.entities.Classe;
import ff.vid.entities.Eleve;

public interface EleveRepository extends JpaRepository<Eleve,Long> {
	public List<Eleve> findByClasse(Classe c );
}
