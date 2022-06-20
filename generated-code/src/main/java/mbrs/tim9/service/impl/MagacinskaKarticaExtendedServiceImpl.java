package mbrs.tim9.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mbrs.tim9.model.MagacinskaKartica;
import mbrs.tim9.repository.MagacinskaKarticaRepository;
import mbrs.tim9.service.MagacinskaKarticaExtendedService;

@Service
@Qualifier("magacinskaKarticaExtendedServiceImpl")
public class MagacinskaKarticaExtendedServiceImpl extends MagacinskaKarticaServiceImpl implements MagacinskaKarticaExtendedService {
	
	@Autowired
	private MagacinskaKarticaRepository repository;

	public MagacinskaKartica izracunajUkupnuVrednost(MagacinskaKartica magacinskaKartica) {
		
		float ukupnaVrednost = magacinskaKartica.getPromet_ulaza_vrednost() - magacinskaKartica.getPromet_izlaza_vrednost();
		Integer ukupnaKolicina = magacinskaKartica.getPromet_ulaza_kolicina() - magacinskaKartica.getPromet_izlaza_kolicina();
		
		magacinskaKartica.setUkupna_vrednost(ukupnaVrednost);
		magacinskaKartica.setUkupna_kolicina(ukupnaKolicina);
		
		return repository.save(magacinskaKartica);
	}

}
