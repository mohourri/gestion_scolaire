package ff.vid.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class EtudiantController {
	
	
	public String id_class= "";
	@Autowired
	private EleveRepository etudiantRepository;
	
	@Autowired 
	private ClasseRepository classeRepository;
	
	
	@GetMapping("/Etudiants")
	@ResponseBody
	public List<Eleve> listEtd(){
		return etudiantRepository.findAll();
	}
	
	@GetMapping("/Admin/Etudiants")
	public String Home(Model model){
		return getAllEtudiantByClass(model,id_class);
	}
	
	@GetMapping("getEtudiantsByClass/findEtudiants")
	public String rechercherEtudiant(@RequestParam String nom,Model model){
		
		
		List<Eleve> listEtd =  new ArrayList<Eleve>();
		for (Eleve e : etudiantRepository.findAll()) {
			String nomComplet = e.getNom()+" "+e.getPrenom();
			Long id = Long.parseLong(id_class);
			if(nomComplet.toLowerCase().contains(nom.toLowerCase()) && e.getClasse().getID() == id) {
				listEtd.add(e);
			}
		}
		model.addAttribute("listEtd", listEtd);
		return "Etudiant";
	}
	

	/*@GetMapping("/getEtudiants")
	public String getAllEtudiant(Model model) {	
		List<Eleve> listEtd = etudiantRepository.findAll();
		List<Classe> listCls = classeRepository.findAll();
		
		model.addAttribute("listEtd", listEtd);
		model.addAttribute("listCls", listCls);
	
		return "Etudiant";
	}*/
	
//---------------------------------------------------------------	
	@GetMapping("/getEtudiantsByClass/{id}")
	public String getAllEtudiantByClass(Model model,@PathVariable("id") String id) {	
		List<Eleve> listEtd = new ArrayList<Eleve>();
		id_class= id;
		String classeNom =String.valueOf(classeRepository.findById(Long.parseLong(id)).get().getFiliere()+" "+classeRepository.findById(Long.parseLong(id)).get().getNiveau()); 
		
		for (Eleve e : etudiantRepository.findAll()) {	
			
			if(e.getClasse()!=null)
			{
				if(e.getClasse().getID().equals(Long.parseLong(id))) {
					listEtd.add(e);
				}
			}
		}
		List<Classe> listCls = classeRepository.findAll();
		
		model.addAttribute("listEtd", listEtd);
		model.addAttribute("listCls", listCls);
		model.addAttribute("classeNom", classeNom);
	
		return "Etudiant";
	}
//------------------------------------------------------------------
	
	@GetMapping("/addEtudiant")
	public String ajouterEtudiant(@RequestParam String nom,
		                         @RequestParam String prenom,
			                     @RequestParam String date_naissance,
			                     @RequestParam String email,
			                     @RequestParam String tele,
			                     @RequestParam String date_inscription,
			                     //@RequestParam String id_class,
			                     Model model) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Eleve e = new Eleve();
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setEmail(email);
		e.setTel(tele);
		e.setDat_naissance(formatter.parse(date_naissance));
		e.setDate_inscription(formatter.parse(date_inscription));
		e.setClasse(classeRepository.findById(Long.valueOf(id_class)).get());
		
		etudiantRepository.save(e);
		return  getAllEtudiantByClass(model,id_class);
	}
	

	@GetMapping("/updateEtudiant")
	public String modifierEtudiant(@RequestParam String idEdit,
			@RequestParam String nomEdit,
			@RequestParam String prenomEdit,
            @RequestParam String date_naissanceEdit,
            @RequestParam String emailEdit,
            @RequestParam String teleEdit,
            @RequestParam String date_inscriptionEdit,
            //@RequestParam String id_classEdit,
            Model model) throws ParseException {
         
            Eleve e = etudiantRepository.findById(Long.valueOf(idEdit)).get();
            
            if(e!=null)
            {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				e.setNom(nomEdit);
				e.setPrenom(prenomEdit);
				e.setEmail(emailEdit);
				e.setTel(teleEdit);
				e.setDat_naissance(formatter.parse(date_naissanceEdit));
				e.setDate_inscription(formatter.parse(date_inscriptionEdit));
				e.setClasse(classeRepository.findById(Long.valueOf(id_class)).get());
				etudiantRepository.save(e);
            }
			return  getAllEtudiantByClass(model,id_class);
	}
	
	@GetMapping("/deleteEtudiant/{id}")
	public String supprimerEtudiant(@PathVariable("id") String id, Model model) {
		System.out.println("id : "+id);
	    Eleve user = etudiantRepository.findById(Long.parseLong(id)).get();
	    etudiantRepository.delete(user);
	    return  getAllEtudiantByClass(model,id_class);
	}

}

