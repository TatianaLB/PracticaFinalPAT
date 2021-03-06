package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.model.CantantesModel;
import PAT.proyectoFinal.model.PlaylistModel;
import PAT.proyectoFinal.repository.CantantesRepository;
import PAT.proyectoFinal.service.CantantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CantantesServiceImpl implements CantantesService {

    AtomicInteger id = new AtomicInteger();

    @Autowired
    CantantesRepository repository;

    @Override
    public void createCantanteFavService(CantantesModel cantante){
        int id1 = id.getAndIncrement();
        String nombre = cantante.getNombre();
        String album = cantante.getAlbum();
        String user = cantante.getUser();

        repository.createCantanteFav(id1, nombre, album,user);
    }

    @Override
    public boolean checkIfCantanteExistsService(String nombre, String user){
        String exists = repository.trueOrFalsePlaylistService(nombre,user);
        if(exists == "TRUE"){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Iterable<CantantesModel> getCantantesByUserService(String user){

        return repository.findCantantesByUser(user);
    }
}
