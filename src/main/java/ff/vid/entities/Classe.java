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
public class Classe {

	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	Long ID;
	String filiere;
	int niveau;

	@OneToMany (mappedBy = "classe")
	List<Eleve> eleves;

	@OneToMany (mappedBy = "classe")
	List<Cour> Cours;
	
}
