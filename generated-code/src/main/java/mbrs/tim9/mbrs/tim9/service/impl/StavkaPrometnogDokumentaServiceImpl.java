package mbrs.tim9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import mbrs.tim9.repository.StavkaPrometnogDokumentaRepository;
import org.springframework.stereotype.Service;
import mbrs.tim9.service.StavkaPrometnogDokumentaService;
import mbrs.tim9.model.StavkaPrometnogDokumenta;
import java.util.Collection;

@Service
public class StavkaPrometnogDokumentaServiceImpl implements StavkaPrometnogDokumentaService {

    @Autowired
    private StavkaPrometnogDokumentaRepository stavkaPrometnogDokumentaRepository;
    
 	@Override
    public StavkaPrometnogDokumenta getById(Long Id){
        return stavkaPrometnogDokumentaRepository.findById(id).get();
    }
				
    @Override
    public Collection<StavkaPrometnogDokumenta> getAll(){
        return stavkaPrometnogDokumentaRepository.findAll();
    }

 	@Override
    public StavkaPrometnogDokumenta save(StavkaPrometnogDokumenta stavka){
        stavkaPrometnogDokumentaRepository.save(stavkaPrometnogDokumenta);
    }
				
				
 	@Override
    public void delete(long id){
        stavkaPrometnogDokumentaRepository.delete(stavkaPrometnogDokumenta);
    }
				
}


