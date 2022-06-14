package mbrs.tim9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.MagacinskaKarticaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.MagacinskaKarticaService;
import mbrs.tim9.model.MagacinskaKartica;
import java.util.Collection;

@Service
public class MagacinskaKarticaServiceImpl implements MagacinskaKarticaService {

    @Autowired
    private MagacinskaKarticaRepository magacinskaKarticaRepository;
    
 	@Override
    public MagacinskaKartica getById(Long id){
        return magacinskaKarticaRepository.findById(id).get();
    }
				
    @Override
    public Collection<MagacinskaKartica> getAll(){
        return magacinskaKarticaRepository.findAll();
    }

 	@Override
    public MagacinskaKartica save(MagacinskaKartica magacinskaKartica){
        magacinskaKarticaRepository.save(magacinskaKartica);
    }
				
				
 	@Override
    public void delete(long id){
        magacinskaKarticaRepository.delete(magacinskaKartica);
    }
				
}


