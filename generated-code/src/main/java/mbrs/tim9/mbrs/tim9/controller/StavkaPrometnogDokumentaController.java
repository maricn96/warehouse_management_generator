package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.model.Roba;
import org.springframework.ui.Model;
import java.util.Collection;
import java.util.List;

import mbrs.tim9.service.StavkaPrometnogDokumentaService;
import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.service.RobaService;

@Controller
@RequestMapping("/stavkaPrometnogDokumenta")
public class StavkaPrometnogDokumentaController{

	@Autowired
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	
	@Autowired
	private MagacinskaKarticaService magacinskaKarticaService;
	@Autowired
	private RobaService robaService;
	
	@GetMapping("")
	public ResponseEntity<Collection<StavkaPrometnogDokumenta>> getAll() {
		return new ResponseEntity<Collection<StavkaPrometnogDokumenta>>(stavkaPrometnogDokumentaService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/id")
	public ResponseEntity<StavkaPrometnogDokumenta> getById(@PathVariable("/id") Long id) {
		return new ResponseEntity<StavkaPrometnogDokumenta>(stavkaPrometnogDokumentaService.getById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("/id") Long id, @RequestBody StavkaPrometnogDokumenta stavkaPrometnogDokumenta) {
        try {
        	StavkaPrometnogDokumenta stavkaPrometnogDokumenta = stavkaPrometnogDokumentaService.save(stavkaPrometnogDokumenta);
            return new ResponseEntity<StavkaPrometnogDokumenta>(stavkaPrometnogDokumenta, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<StavkaPrometnogDokumenta>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping("")
    public ResponseEntity<StavkaPrometnogDokumenta> add(@RequestBody StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
    		StavkaPrometnogDokumenta stavkaPrometnogDokumenta = stavkaPrometnogDokumentaService.save(stavkaPrometnogDokumenta);
            return new ResponseEntity<StavkaPrometnogDokumenta>(stavkaPrometnogDokumenta, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<> delete(@PathVariable("id") Long id) {
    	StavkaPrometnogDokumenta stavkaPrometnogDokumenta = stavkaPrometnogDokumentaService.getById(id);
    	
    	try {
    		stavkaPrometnogDokumentaService.delete(stavkaPrometnogDokumenta);
    		return new ResponseEntity<>(stavkaPrometnogDokumenta, HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
