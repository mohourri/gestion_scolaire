package ff.vid.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cour {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	int id_cour;
	
	@Temporal(TemporalType.DATE)
	Date date_cours;
		
	@ManyToOne
	@JoinColumn(name = "id_class")
	private Classe classe;
	
	@Transient
	String classeNom;
	
	@ManyToOne
	@JoinColumn(name = "id_prof")
	private Prof prof;
	
	@Transient
	String ProfNom;

	@ManyToOne
	@JoinColumn(name = "id_matiere")
	private Matiere matiere;
	
	@Transient
	String MatiereNom;
	
}
