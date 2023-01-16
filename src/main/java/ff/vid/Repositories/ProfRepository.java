package ff.vid.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import ff.vid.entities.Prof;

public interface ProfRepository extends JpaRepository<Prof,Long> {

	public Prof findByEmailAndPassword(String email, String password);
}
