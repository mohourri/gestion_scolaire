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
		
	@ManyToOne
	@JoinColumn(name = "id_class")
	private Classe classe;
	
	@ManyToOne
	@JoinColumn(name = "id_prof")
	private Prof prof;
	
	@Temporal(TemporalType.DATE)
	Date date_cours;
}
