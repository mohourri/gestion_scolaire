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
public class Prof {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
     Long id_prof;
	
	 String nom;
	 
	 @Temporal(TemporalType.DATE)
	 Date date_embauche;
	 
	 String email;
	 
	 @ManyToOne
   	 @JoinColumn(name = "id_matiere")
	 private Matiere matiere;
	 
	 @OneToMany(mappedBy = "prof")
	 List<Cour> cours;

}
