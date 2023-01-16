package ff.vid.Controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import ff.vid.Repositories.ClasseRepository;
import ff.vid.Repositories.EleveRepository;
import ff.vid.Repositories.ExamenRepository;
import ff.vid.Repositories.MatiereRepository;
import ff.vid.entities.Classe;
import ff.vid.entities.Eleve;
import ff.vid.entities.Examen;
import ff.vid.entities.Matiere;
import ff.vid.entities.MatiereEleves;

@Controller
public class ProfController {
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private EleveRepository eleveRepository;
	@Autowired
	private MatiereRepository matiereRepository;
	@Autowired
	private ExamenRepository examenRepository;
	
	


    @GetMapping("/")
    public ModelAndView homePage() {
		List<Classe> allClasses = classeRepository.findAll();
        return new ModelAndView("mesClasses", "classes", allClasses);
    }

	
    @GetMapping("/mes_classes")
    public ModelAndView mesClasses() {
		List<Classe> allClasses = classeRepository.findAll();
        return new ModelAndView("mesClasses", "classes", allClasses);
    }

    @GetMapping("/mes_classes/c/")
    public ModelAndView getClasse(@RequestParam("ID_classe") String id_classe) {
    	System.out.println(id_classe);
    	Classe classe = new Classe();
    	List<Eleve> eleves = new ArrayList<Eleve>();
    	List<Matiere> mt = new ArrayList<>();
    	try {
    		Classe c = classeRepository.findById(Long.parseLong(id_classe)).orElse(classe);
    	    eleves = eleveRepository.findByClasse(c);
	    	mt = matiereRepository.findAll();
    	} catch (NumberFormatException e) {
    	    e.printStackTrace();
    	}

    	MatiereEleves me = new MatiereEleves(eleves,mt);
        return new ModelAndView("maClasse", "me", me);
    }



	@PostMapping("/examen/noter")
	public RedirectView ajouterEnseignant(@RequestParam("matiere") String id_mat,
			@RequestParam("eleve") String id_eleve,
			@RequestParam("note") String note,
			@RequestParam("numero_exam") String num
			) {

		Examen ex = new Examen();
		ex.setMatiere(matiereRepository.findById(Long.parseLong(id_mat)).orElse(null));
		ex.setEleve(eleveRepository.findById(Long.parseLong(id_eleve)).orElse(null));
		ex.setNote(Float.parseFloat(note));
		ex.setNumero(Integer.parseInt(num));
		examenRepository.save(ex);
        return new RedirectView("/");
	}
    
}
