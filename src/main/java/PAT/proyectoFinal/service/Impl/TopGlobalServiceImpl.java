package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.TopGlobalModel;
import PAT.proyectoFinal.repository.TopGlobalRepository;
import PAT.proyectoFinal.service.TopGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopGlobalServiceImpl implements TopGlobalService
{
    @Autowired
    private TopGlobalRepository repository;

    @Override
    public Iterable<TopGlobalModel> getTopGlobalService(){
        return repository.findAll();
    }
}
