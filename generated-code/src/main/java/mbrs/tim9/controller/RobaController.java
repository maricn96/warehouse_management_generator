package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.Roba;
import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import java.util.Collection;

import mbrs.tim9.service.RobaService;
import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;

@Controller
@RequestMapping("/roba")
public class RobaController{

	@Autowired
	@Qualifier("robaServiceImpl")
	private RobaService robaService;
	
	@Autowired
	@Qualifier("magacinskaKarticaServiceImpl")
	private MagacinskaKarticaService magacinskaKarticaService;
	@Autowired
	@Qualifier("stavkaPrometnogDokumentaServiceImpl")
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	
	@GetMapping
	public ResponseEntity<Collection<Roba>> getAll() {
		return new ResponseEntity<Collection<Roba>>(robaService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Roba> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<Roba>(robaService.getById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Roba roba) {
        try {
        	Roba robaUpdated = robaService.save(roba);
            return new ResponseEntity<Roba>(robaUpdated, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping
    public ResponseEntity<Roba> save(@RequestBody Roba roba){
    		Roba robaSaved = robaService.save(roba);
            return new ResponseEntity<Roba>(robaSaved, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    	
    	try {
    		robaService.delete(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
