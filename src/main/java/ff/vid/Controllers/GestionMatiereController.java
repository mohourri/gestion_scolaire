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

import ff.vid.Repositories.MatiereRepository;
import ff.vid.Repositories.ProfRepository;
import ff.vid.entities.Matiere;
import ff.vid.entities.Prof;

@Controller
public class GestionMatiereController {
	
	
	@Autowired
	private MatiereRepository matiereRepository;
	
	
	@GetMapping("/Admin/Matieres")
	public String Home(Model model){
		return getAllMatieres(model);
	}
	
	@GetMapping("/findMatieres")
	public String rechercherMatiere(@RequestParam String nom,Model model){
		
		
		List<Matiere> listMat =  new ArrayList<Matiere>();
		for (Matiere m : matiereRepository.findAll()) {

			if(m.getLibelle().toLowerCase().contains(nom.toLowerCase())) {
				listMat.add(m);
			}
		}
		model.addAttribute("listMat", listMat);
		return "Matiere";
	}
	

	@GetMapping("/getMatieres")
	public String getAllMatieres(Model model) {	

		List<Matiere> listMat = matiereRepository.findAll();
		model.addAttribute("listMat", listMat);
	
		return "Matiere";
	}

	@GetMapping("/addMatiere")
	public String ajouterMatiere(@RequestParam String libelle,
		                         @RequestParam String volume_horaire,
			                     Model model) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Matiere m = new Matiere();
		m.setLibelle(libelle);
		m.setVolume_horaire(Integer.parseInt(volume_horaire));
		
		matiereRepository.save(m);
		return getAllMatieres(model);
	}
	

	@GetMapping("/updateMatiere")
	public String modifierMatiere(@RequestParam String idEdit,
			@RequestParam String LibelleEdit,
			@RequestParam String volume_horaireEdit,
            Model model)  {
         
            Matiere m = matiereRepository.findById(Long.valueOf(idEdit)).get();
            
            if(m!=null)
            {
				m.setLibelle(LibelleEdit);
				m.setVolume_horaire(Integer.parseInt(volume_horaireEdit));		
				matiereRepository.save(m);
            }
			return getAllMatieres(model);
	}
	
	@GetMapping("/deleteMatiere/{id}")
	public String supprimerMatiere(@PathVariable("id") String id, Model model) {
	    Matiere m= matiereRepository.findById(Long.parseLong(id)).get();
	    if(m!=null) {
	    	matiereRepository.delete(m);
	    }
	    
	    return getAllMatieres(model);
	}


}
