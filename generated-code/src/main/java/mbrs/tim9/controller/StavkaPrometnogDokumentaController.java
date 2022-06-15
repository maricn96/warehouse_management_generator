package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import java.util.Collection;

import mbrs.tim9.service.StavkaPrometnogDokumentaService;

@Controller
@RequestMapping("/stavkaPrometnogDokumenta")
public class StavkaPrometnogDokumentaController{

	@Autowired
	private StavkaPrometnogDokumentaService stavkaPrometnogDokumentaService;
	
	
	@GetMapping
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
        	StavkaPrometnogDokumenta stavkaPrometnogDokumentaUpdated = stavkaPrometnogDokumentaService.save(stavkaPrometnogDokumenta);
            return new ResponseEntity<StavkaPrometnogDokumenta>(stavkaPrometnogDokumentaUpdated, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping
    public ResponseEntity<StavkaPrometnogDokumenta> save(@RequestBody StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
    		StavkaPrometnogDokumenta stavkaPrometnogDokumentaSaved = stavkaPrometnogDokumentaService.save(stavkaPrometnogDokumenta);
            return new ResponseEntity<StavkaPrometnogDokumenta>(stavkaPrometnogDokumentaSaved, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    	
    	try {
    		stavkaPrometnogDokumentaService.delete(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
