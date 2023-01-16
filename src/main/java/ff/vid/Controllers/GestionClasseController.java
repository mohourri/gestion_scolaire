package ff.vid.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ff.vid.Repositories.ClasseRepository;
import ff.vid.Repositories.EleveRepository;
import ff.vid.entities.Classe;
import ff.vid.entities.Eleve;

@Controller
public class GestionClasseController {

	@Autowired
	private EleveRepository etudiantRepository;
	
	@Autowired 
	private ClasseRepository classeRepository;
	

	
	@GetMapping("/Admin/Classes")
	public String ListClasses(Model model){
		return getClasse(model);
	}
	
	@GetMapping("/Admin/findClasses")
	public String rechercherClasse(@RequestParam String nom,Model model){
		
		
		List<Classe> listCls =  new ArrayList<Classe>();
		for (Classe c : classeRepository.findAll()) {
			String libelle = c.getFiliere()+" "+c.getNiveau();
			if(libelle.toLowerCase().contains(nom.toLowerCase())) {
				listCls.add(c);
			}
		}
		model.addAttribute("listCls", listCls);
		return "Classe";
	}
	

	@GetMapping("/getClasse")
	public String getClasse(Model model) {	
		List<Classe> listCls = classeRepository.findAll();
		model.addAttribute("listCls", listCls);
	
		return "Classe";
	}
	
	@GetMapping("/addClasse")
	public String ajouterClasse(@RequestParam String filiere,
		                         @RequestParam String niveau,
			                     Model model)  {
		
		
		Classe c = new Classe();
		c.setFiliere(filiere);
		c.setNiveau(Integer.parseInt(niveau));
		classeRepository.save(c);
		return getClasse(model);
	}
	

	@GetMapping("/updateClasse")
	public String modifierEtudiant(@RequestParam String idEdit,
			@RequestParam String filiereEdit,
			@RequestParam String niveauEdit,
            Model model) {
         
            Classe c = classeRepository.findById(Long.valueOf(idEdit)).get();
            
            if(c!=null)
            {
				c.setFiliere(filiereEdit);
				c.setNiveau(Integer.parseInt(niveauEdit));	
				classeRepository.save(c);
            }
			return getClasse(model);
	}
	
	@GetMapping("/deleteClasse/{id}")
	public String supprimerClasse(@PathVariable("id") String id, Model model) {
	   Classe c = classeRepository.findById(Long.parseLong(id)).get();
	    classeRepository.delete(c);
	    return getClasse(model);
	}

}
