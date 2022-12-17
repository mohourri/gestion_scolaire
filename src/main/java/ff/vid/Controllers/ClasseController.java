package ff.vid.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ff.vid.Repositories.ClasseRepository;
import ff.vid.entities.Classe;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class ClasseController {

	@Autowired
	private ClasseRepository cr;
	
	
	@GetMapping("/classes")
	public List<Classe> getAllClasses(){
		return cr.findAll();
	}
	
	
}
