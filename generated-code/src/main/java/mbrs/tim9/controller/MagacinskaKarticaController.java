package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.model.Roba;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import java.util.Collection;

import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.service.RobaService;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;

@Controller
@RequestMapping("/magacinskaKartica")
public class MagacinskaKarticaController{

	@Autowired
	private MagacinskaKarticaService magacinskaKarticaService;
	
	@Autowired
	private RobaService robaService;
	@Autowired
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	
	@GetMapping
	public ResponseEntity<Collection<MagacinskaKartica>> getAll() {
		return new ResponseEntity<Collection<MagacinskaKartica>>(magacinskaKarticaService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/id")
	public ResponseEntity<MagacinskaKartica> getById(@PathVariable("/id") Long id) {
		return new ResponseEntity<MagacinskaKartica>(magacinskaKarticaService.getById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("/id") Long id, @RequestBody MagacinskaKartica magacinskaKartica) {
        try {
        	MagacinskaKartica magacinskaKarticaUpdated = magacinskaKarticaService.save(magacinskaKartica);
            return new ResponseEntity<MagacinskaKartica>(magacinskaKarticaUpdated, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping
    public ResponseEntity<MagacinskaKartica> save(@RequestBody MagacinskaKartica magacinskaKartica){
    		MagacinskaKartica magacinskaKarticaSaved = magacinskaKarticaService.save(magacinskaKartica);
            return new ResponseEntity<MagacinskaKartica>(magacinskaKarticaSaved, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    	
    	try {
    		magacinskaKarticaService.delete(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
