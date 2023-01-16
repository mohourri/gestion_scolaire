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
import ff.vid.Repositories.CourRepository;
import ff.vid.Repositories.EleveRepository;
import ff.vid.Repositories.MatiereRepository;
import ff.vid.Repositories.ProfRepository;
import ff.vid.entities.Classe;
import ff.vid.entities.Cour;
import ff.vid.entities.Eleve;
import ff.vid.entities.Matiere;
import ff.vid.entities.Prof;

@Controller
public class GestionCourController {

	

	@Autowired 
	private CourRepository courRepository;
	
	@Autowired 
	private ClasseRepository classeRepository;
	
	@Autowired 
	private ProfRepository profRepository;
	
	@Autowired 
	private MatiereRepository matiereRepository;
	
	
	@GetMapping("/Admin/Cours")
	public String Home(Model model){
		return getAllCourses(model);
	}
	
	/*@GetMapping("/findEtudiants")
	public String rechercherCours(@RequestParam String nom,Model model){
		
		
		List<Cour> listCours =  new ArrayList<Cour>();
		for (Cour c : courRepository.findAll()) {
			String nomComplet = e.getNom()+" "+e.getPrenom();
			Long id = Long.parseLong(id_class);
			if(nomComplet.toLowerCase().contains(nom.toLowerCase()) && e.getClasse().getID() == id) {
				listEtd.add(e);
			}
		}
		model.addAttribute("listEtd", listEtd);
		return "Etudiant";
	}*/
	

	@GetMapping("/getCourses")
	public String getAllCourses(Model model) {	
		
		List<Prof> listProf = profRepository.findAll();
		List<Classe> listCls = classeRepository.findAll();
		List<Matiere> listMat = matiereRepository.findAll();
		List<Cour> listCour = courRepository.findAll();
		
		for (Cour c : listCour) {					
            
			c.setProfNom(profRepository.findById(c.getProf().getId_prof()).get().getNom());
			c.setClasseNom(classeRepository.findById(c.getClasse().getID()).get().getFiliere()+" "+classeRepository.findById(c.getClasse().getID()).get().getNiveau());
			c.setMatiereNom(matiereRepository.findById(c.getMatiere().getID()).get().getLibelle());
		}

		model.addAttribute("listCour", listCour);
		model.addAttribute("listCls", listCls);
		model.addAttribute("listProf", listProf);
		model.addAttribute("listMat", listMat);
	
		return "Cour";
	}
	

	
	@GetMapping("/addCour")
	public String ajouterCour(@RequestParam String date_cours,
		                         @RequestParam String id_class,
			                     @RequestParam String id_prof,
			                     @RequestParam String id_matiere,
			                     Model model) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Cour c = new Cour();
		c.setDate_cours(formatter.parse(date_cours));
		c.setClasse(classeRepository.findById(Long.valueOf(id_class)).get());
		c.setProf(profRepository.findById(Long.valueOf(id_prof)).get());
		c.setMatiere(matiereRepository.findById(Long.valueOf(id_matiere)).get());
		
		courRepository.save(c);
		return getAllCourses(model);
	}
	

	@GetMapping("/updateCour")
	public String modifierCour(@RequestParam String idEdit,
			@RequestParam String date_coursEdit,
			@RequestParam String id_classEdit,
            @RequestParam String id_profEdit,
            @RequestParam String id_matiereEdit,
            Model model) throws ParseException {
         
            Cour c = courRepository.findById(Long.valueOf(idEdit)).get();
            
            if(c!=null)
            {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				c.setDate_cours(formatter.parse(date_coursEdit));
				c.setClasse(classeRepository.findById(Long.valueOf(id_classEdit)).get());
				c.setProf(profRepository.findById(Long.valueOf(id_profEdit)).get());
				c.setMatiere(matiereRepository.findById(Long.valueOf(id_matiereEdit)).get());
				
				courRepository.save(c);
            }
            return getAllCourses(model);
	}
	
	@GetMapping("/deleteCour/{id}")
	public String supprimerCour(@PathVariable("id") String id, Model model) {
	   Cour c = courRepository.findById(Long.parseLong(id)).get();
	    courRepository.delete(c);
	    return getAllCourses(model);
	}
}
