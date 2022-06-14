package ${class.typePackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import mbrs.tim9.model.${class.name};
<#list referencedProperties as property>
import mbrs.tim9.model.${property.type?cap_first};
</#list>
import java.util.Collection;

import mbrs.tim9.service.${class.name}Service;
<#list referencedProperties as property>
import mbrs.tim9.service.${property.type?cap_first}Service;
</#list>

@Controller
@RequestMapping("/${class.name?uncap_first}")
public class ${class.name}Controller{

	@Autowired
	private ${class.name}Service ${class.name?uncap_first}Service;
	
	<#list referencedProperties as property>
	@Autowired
	private ${property.type?cap_first}Service ${property.type?uncap_first}Service;
	</#list>
	
	@GetMapping
	public ResponseEntity<Collection<${class.name}>> getAll() {
		return new ResponseEntity<Collection<${class.name}>>(${class.name?uncap_first}Service.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/id")
	public ResponseEntity<${class.name}> getById(@PathVariable("/id") Long id) {
		return new ResponseEntity<${class.name}>(${class.name?uncap_first}Service.getById(id), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("/id") Long id, @RequestBody ${class.name} ${class.name?uncap_first}) {
        try {
        	${class.name} ${class.name?uncap_first}Updated = ${class.name?uncap_first}Service.save(${class.name?uncap_first});
            return new ResponseEntity<${class.name}>(${class.name?uncap_first}Updated, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getStackTrace(), HttpStatus.BAD_REQUEST);
        }
    }
      
    @PostMapping
    public ResponseEntity<${class.name}> save(@RequestBody ${class.name} ${class.name?uncap_first}){
    		${class.name} ${class.name?uncap_first}Saved = ${class.name?uncap_first}Service.save(${class.name?uncap_first});
            return new ResponseEntity<${class.name}>(${class.name?uncap_first}Saved, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    	
    	try {
    		${class.name?uncap_first}Service.delete(id);
    		return new ResponseEntity<>(HttpStatus.OK);
    	}
    	catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
 
}
