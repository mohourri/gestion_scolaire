package ff.vid.Controllers;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ff.vid.Repositories.ClasseRepository;
import ff.vid.Repositories.EleveRepository;
import ff.vid.entities.Classe;
import ff.vid.entities.Eleve;

@Controller
public class ProfController {
	@Autowired
	private ClasseRepository classeRepository;
	@Autowired
	private EleveRepository eleveRepository;
	

    @RequestMapping("/")
	public String home() {
		return "index";	
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
    	try {
    		Classe c = classeRepository.findById(Long.parseLong(id_classe)).orElse(classe);
    	     eleves = eleveRepository.findByClasse(c);
    	} catch (NumberFormatException e) {
    	    e.printStackTrace();
    	}

        return new ModelAndView("maClasse", "eleves", eleves);
    }


}
