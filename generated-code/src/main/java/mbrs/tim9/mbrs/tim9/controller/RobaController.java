package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.Roba;
import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;

import mbrs.tim9.service.RobaService;
import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;

@Controller
@RequestMapping("/roba")
public class RobaController{

	@Autowired
	private RobaService robaService;
	
	@Autowired
	private MagacinskaKarticaService magacinskaKarticaService;
	@Autowired
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	
	@GetMapping("")
	public ResponseEntity<Collection<Roba>> getAll() {
		return new ResponseEntity<Collection<Roba>>(robaService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/id")
	public ResponseEntity<Roba> getById(@PathVariable("/id") Long id) {
		return new ResponseEntity<Roba>(robaService.getById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("/id") Long id, @RequestBody Roba roba) {
        try {
        	Roba roba = robaService.save(roba);
            return new ResponseEntity<Roba>(roba, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Roba>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping("")
    public ResponseEntity<Roba> add(@RequestBody Roba roba){
    		Roba roba = robaService.save(roba);
            return new ResponseEntity<Roba>(roba, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<> delete(@PathVariable("id") Long id) {
    	Roba roba = robaService.getById(id);
    	
    	try {
    		robaService.delete(roba);
    		return new ResponseEntity<>(roba, HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
