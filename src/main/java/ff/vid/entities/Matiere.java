package ff.vid.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Matiere {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	Long ID;
	
	String libelle;
	
	int volume_horaire;
	@OneToMany (mappedBy = "matiere")
	List<Prof> profs;
	

	@OneToMany (mappedBy = "matiere")
	List<Cour> cours;

	@OneToMany (mappedBy = "matiere")
	List<Examen> examens;
	
}
