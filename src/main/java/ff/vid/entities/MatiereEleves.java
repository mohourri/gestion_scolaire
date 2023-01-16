package ff.vid.entities;

import java.util.ArrayList;
import java.util.List;

public class MatiereEleves {
	public List<Eleve> eleves = new ArrayList<Eleve>();
	public List<Matiere> matieres = new ArrayList<Matiere>();
	public String classe;

	public MatiereEleves(List<Eleve> ele, List<Matiere> mt, String c) {
		this.eleves=ele;
		this.matieres=mt;
		this.classe =c;
	}
}
