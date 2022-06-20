package mbrs.tim9.service;

import org.springframework.stereotype.Service;

import mbrs.tim9.model.MagacinskaKartica;

public interface MagacinskaKarticaExtendedService extends MagacinskaKarticaService {

	MagacinskaKartica izracunajUkupnuVrednost(MagacinskaKartica magacinskaKartica);

}
