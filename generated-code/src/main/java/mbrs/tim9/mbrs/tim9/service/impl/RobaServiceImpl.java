package mbrs.tim9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.RobaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.RobaService;
import mbrs.tim9.model.Roba;
import java.util.Collection;

@Service
public class RobaServiceImpl implements RobaService {

    @Autowired
    private RobaRepository robaRepository;
    
 	@Override
    public Roba getById(Long id){
        return robaRepository.findById(id).get();
    }
				
    @Override
    public Collection<Roba> getAll(){
        return robaRepository.findAll();
    }

 	@Override
    public Roba save(Roba roba){
        robaRepository.save(roba);
    }
				
				
 	@Override
    public void delete(long id){
        robaRepository.delete(roba);
    }
				
}


