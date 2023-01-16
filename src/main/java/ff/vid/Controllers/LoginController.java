package ff.vid.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ff.vid.Repositories.MatiereRepository;
import ff.vid.Repositories.ProfRepository;
import ff.vid.entities.Matiere;
import ff.vid.entities.Prof;

@Controller
public class LoginController {

	@Autowired
	public ProfRepository profRepository;
	
	@Autowired
	public MatiereRepository matiereRepository;
	
	
	
	@GetMapping("/")
	public String loginpage() {
		return "login";
	}
	
	@GetMapping("/Connexion")
	public String Connexion(@RequestParam String username,@RequestParam String password,Model model)
	{
		
		if(username.equals("admin") && password.equals("123"))
		{
			List<Prof> listProf = profRepository.findAll();
			List<Matiere> listMat = matiereRepository.findAll();
			
			for (Prof p : profRepository.findAll()) {

				p.setNomMatiere(matiereRepository.findById(p.getMatiere().getID()).get().getLibelle()); 
			}
			
			model.addAttribute("listProf", listProf);
			model.addAttribute("listMat", listMat);
		
			return "Enseignant";
		}
		else 
		{
			
			Prof p = profRepository.findByEmailAndPassword(username, password);
			if(p!=null)
			{
				model.addAttribute("nom",p.getNom());
				return "test";			
			}
			
			
		}
		
		return "login";
		
		
	}
}
