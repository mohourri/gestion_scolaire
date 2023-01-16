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
import ff.vid.Repositories.MatiereRepository;
import ff.vid.Repositories.ProfRepository;
import ff.vid.entities.Classe;
import ff.vid.entities.Eleve;
import ff.vid.entities.Matiere;
import ff.vid.entities.Prof;

@Controller
public class GestionProfController {
	
	
	@Autowired
	private ProfRepository profRepository;
	
	@Autowired
	private MatiereRepository matiereRepository;
	
	
	@GetMapping("/Admin/Enseignants")
	public String Home(Model model){
		return getAllEnseignants(model);
	}
	
	@GetMapping("/Admin/findEnseignants")
	public String rechercherEnseignant(@RequestParam String nom,Model model){
		
		
		List<Prof> listProf =  new ArrayList<Prof>();
		for (Prof p : profRepository.findAll()) {

			if(p.getNom().toLowerCase().contains(nom.toLowerCase())) {
				p.setNomMatiere(matiereRepository.findById(p.getMatiere().getID()).get().getLibelle()); 
				listProf.add(p);
			}
		}
		model.addAttribute("listProf", listProf);
		return "Enseignant";
	}
	

	@GetMapping("/getEnseignants")
	public String getAllEnseignants(Model model) {	
		List<Prof> listProf = profRepository.findAll();
		List<Matiere> listMat = matiereRepository.findAll();
		
		for (Prof p : profRepository.findAll()) {

			p.setNomMatiere(matiereRepository.findById(p.getMatiere().getID()).get().getLibelle()); 
		}
		
		model.addAttribute("listProf", listProf);
		model.addAttribute("listMat", listMat);
	
		return "Enseignant";
	}
	
//---------------------------------------------------------------	

//------------------------------------------------------------------
	
	@GetMapping("/addEnseignant")
	public String ajouterEnseignant(@RequestParam String nomComplet,
		                         @RequestParam String date_embauche,
			                     @RequestParam String email,
			                     @RequestParam String id_matiere,
			                     //@RequestParam String id_class,
			                     Model model) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Prof p = new Prof();
		p.setNom(nomComplet);
		p.setDate_embauche(formatter.parse(date_embauche));
		p.setEmail(email);
		p.setMatiere(matiereRepository.findById(Long.valueOf(id_matiere)).get());
		
		profRepository.save(p);
		return  getAllEnseignants(model);
	}
	

	@GetMapping("/updateEnseignant")
	public String modifierEnseignant(@RequestParam String idEdit,
			@RequestParam String nomCompletEdit,
			@RequestParam String date_embaucheEdit,
            @RequestParam String emailEdit,
            @RequestParam String id_matiereEdit,
            Model model) throws ParseException {
         
            Prof p = profRepository.findById(Long.valueOf(idEdit)).get();
            
            if(p!=null)
            {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				p.setNom(nomCompletEdit);
				p.setDate_embauche(formatter.parse(date_embaucheEdit));
				p.setEmail(emailEdit);
				p.setMatiere(matiereRepository.findById(Long.valueOf(id_matiereEdit)).get());
				profRepository.save(p);
            }
			return getAllEnseignants(model);
	}
	
	@GetMapping("/deleteEnseignant/{id}")
	public String supprimerEnseignant(@PathVariable("id") String id, Model model) {
		System.out.println("id : "+id);
	    Prof p = profRepository.findById(Long.parseLong(id)).get();
	    if(p!=null) {
	    	profRepository.delete(p);
	    }
	    
	    return getAllEnseignants(model);
	}


}
