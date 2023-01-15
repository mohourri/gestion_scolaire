package ff.vid.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ff.vid.Repositories.ClasseRepository;
import ff.vid.entities.Classe;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
public class ClasseController {

	@Autowired
	private ClasseRepository cr;
	
	
	@GetMapping("/getClasses")
	public List<Classe> getAllClasses(){
		return cr.findAll();
	}
	

	@PostMapping("/addClasse")
	public void AjouterClasse(@RequestBody JsonNode cls) {     
		ObjectMapper mapper = new ObjectMapper();
		try {
			Classe c = mapper.readValue(cls.toString(), Classe.class);
			cr.save(c);		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
}
