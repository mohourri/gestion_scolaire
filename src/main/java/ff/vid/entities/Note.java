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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Note {
	
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	int id_note;
	
	@ManyToOne
	@JoinColumn(name = "id_eleve")
    Eleve eleve;
	
	@ManyToOne
	@JoinColumn(name = "id_matiere")
    Matiere matiere;
	
	@Temporal(TemporalType.DATE)
	Date date;
	
	float note;

}
