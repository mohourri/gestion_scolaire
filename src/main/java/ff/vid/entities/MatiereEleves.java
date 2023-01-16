package ff.vid.entities;

import java.util.ArrayList;
import java.util.List;

public class MatiereEleves {
	public List<Eleve> eleves = new ArrayList<Eleve>();
	public List<Matiere> matieres = new ArrayList<Matiere>();

	public MatiereEleves(List<Eleve> ele, List<Matiere> mt) {
		this.eleves=ele;
		this.matieres=mt;
	}
}
