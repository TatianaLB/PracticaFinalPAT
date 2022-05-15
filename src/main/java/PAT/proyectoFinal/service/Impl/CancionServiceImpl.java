package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.repository.CancionRepository;
import PAT.proyectoFinal.service.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CancionServiceImpl implements CancionService {

  AtomicInteger id = new AtomicInteger();
  @Autowired
  private CancionRepository cancionRepository;

  @Override
  public Iterable<CancionModel> getCancionesService(){
    return cancionRepository.findAll();
  }

  @Override
  public Iterable<CancionModel> getCancionByIdService(String id){
    return cancionRepository.getCancionById(id);
  }

  @Override
  public void deleteCancionByNameAndPlaylistService(String name, String playlist){
    cancionRepository.deleteCancionByNameAndPlaylist(name, playlist);
  }

  @Override
  public void createCancionService(CancionModel cancion){
    int id1 = id.getAndIncrement();
    String nombre = cancion.getNombre();
    String playlist = cancion.getPlaylist();
    String artista = cancion.getArtista();
    String album = cancion.getAlbum();
    int longitud = cancion.getLongitud();
    cancionRepository.createCancion(id1,nombre,playlist,artista,album,longitud);
  }

  @Override
  public Iterable<CancionModel> getCancionesByPlaylistService(String playlist){

    return cancionRepository.getCancionByPlaylist(playlist);
  }
}
