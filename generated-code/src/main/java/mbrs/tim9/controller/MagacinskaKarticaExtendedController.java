package mbrs.tim9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.service.MagacinskaKarticaExtendedService;

@Controller
@RequestMapping("/magacinskaKarticaExtended")
public class MagacinskaKarticaExtendedController extends MagacinskaKarticaController {
	
	@Autowired
	@Qualifier("magacinskaKarticaExtendedService")
	private MagacinskaKarticaExtendedService magacinskaKarticaExtendedService;
	
	@PostMapping("/calculateTotal")
    public ResponseEntity<MagacinskaKartica> izracunajUkupnuVrednost(@RequestBody MagacinskaKartica magacinskaKartica){
    		MagacinskaKartica magacinskaKarticaCalculated = magacinskaKarticaExtendedService.izracunajUkupnuVrednost(magacinskaKartica);
            return new ResponseEntity<MagacinskaKartica>(magacinskaKarticaCalculated, HttpStatus.OK);
    }

}
