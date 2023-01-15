package ff.vid.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Eleve {

	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	int ID;
	
	String nom;
	
	String prenom;
	
	@Temporal(TemporalType.DATE)
	Date date_inscription;
	
	@Temporal(TemporalType.DATE)
	Date dat_naissance;
	
	String email;
	
	String tel;
	
	@ManyToOne
	@JoinColumn(name = "id_class")
	private Classe classe;

	@OneToMany (mappedBy = "eleve")
	List<Examen> examens;
}
