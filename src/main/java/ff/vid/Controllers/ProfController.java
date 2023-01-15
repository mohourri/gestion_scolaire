package ff.vid.Controllers;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ff.vid.Repositories.ClasseRepository;
import ff.vid.entities.Classe;

@Controller
public class ProfController {
	@Autowired
	private ClasseRepository classeRepository;
	

    @RequestMapping("/")
	public String home() {
		return "index";	
	}
	
    @GetMapping("/mes_classes")
    public ModelAndView mesClasses() {
		List<Classe> allClasses = classeRepository.findAll();
        return new ModelAndView("mesClasses.html", "classes", allClasses);
    }

    @GetMapping("/mes_classes/c/")
    public ModelAndView getClasse(@RequestParam("ID_classe") String id_classe) {
    	System.out.println(id_classe);
    	Optional<Classe> classe = Optional.of(new Classe());
    	
    	classe = classeRepository.findById(Long.parseLong(id_classe));
    	System.out.println(classe);

        return new ModelAndView("maClasse.html", "classe", classe);
    }

//	
//	
//	
//	@PostMapping("/enseignants/ajouter")
//	public RedirectView ajouterEnseignant(@RequestParam("nom") String nom,
//			@RequestParam("prenom") String prenom,
//			@RequestParam("email") String email,
//			@RequestParam("password") String password
//			) {
//		Prof ens = new Prof();
//		
//        return new RedirectView("/enseignants");
//	}
//	
//	@GetMapping("/enseignants/supprimer/{id}")
//    public RedirectView supprimerEnseignant(@PathVariable String id) {
//        enseignantRepository.deleteById(Long.parseLong(id));
//        return new RedirectView("/enseignants");
//    }
//	
//
//	@PostMapping("/enseignants/modifier")
//    public RedirectView modifierEnseignant(
//    		@RequestParam("id") String id,
//    		@RequestParam("nom") String nom,
//			@RequestParam("prenom") String prenom,
//			@RequestParam("email") String email,
//			@RequestParam("password") String password) {
//		
//        return new RedirectView("/mes_classes");    
//        
//	}
//	
//	@GetMapping("/enseignants/chercherParNom")
//    public ModelAndView chercherEnseignant(	@RequestParam("nom") String nom) {
//		List<Enseignant> ens = enseignantRepository.findByNom(nom);
//		System.out.println(ens);
//        return new ModelAndView("enseignants", "ens", ens);
//    }
//	

}
