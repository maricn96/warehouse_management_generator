package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import mbrs.tim9.model.Roba;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;

import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;
import mbrs.tim9.service.RobaService;

@Controller
@RequestMapping("/magacinskaKartica")
public class MagacinskaKarticaController{

	@Autowired
	private MagacinskaKarticaService magacinskaKarticaService;
	
	@Autowired
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	@Autowired
	private RobaService robaService;
	
	@GetMapping("")
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
        	MagacinskaKartica magacinskaKartica = magacinskaKarticaService.save(magacinskaKartica);
            return new ResponseEntity<MagacinskaKartica>(magacinskaKartica, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<MagacinskaKartica>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping("")
    public ResponseEntity<MagacinskaKartica> add(@RequestBody MagacinskaKartica magacinskaKartica){
    		MagacinskaKartica magacinskaKartica = magacinskaKarticaService.save(magacinskaKartica);
            return new ResponseEntity<MagacinskaKartica>(magacinskaKartica, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<> delete(@PathVariable("id") Long id) {
    	MagacinskaKartica magacinskaKartica = magacinskaKarticaService.getById(id);
    	
    	try {
    		magacinskaKarticaService.delete(magacinskaKartica);
    		return new ResponseEntity<>(magacinskaKartica, HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
