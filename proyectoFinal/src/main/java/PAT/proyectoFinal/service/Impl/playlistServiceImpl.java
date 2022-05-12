package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.playlistModel;
import PAT.proyectoFinal.repository.playlistRepository;
import PAT.proyectoFinal.service.playlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import PAT.proyectoFinal.repository.playlistRepository;

import java.util.List;

@Service
public class playlistServiceImpl implements playlistService {

  @Autowired
  playlistRepository playlistRepository;


  @Override
  public Iterable<playlistModel> getPlaylistsService(){
    return playlistRepository.findAll();
  }

  @Override
  public Iterable<playlistModel> getPlaylistByIdService(String id){

    return playlistRepository.findPlaylistById(id);
  }

  @Override
  public Iterable<playlistModel> getPlaylistService(){
    return playlistRepository.findAll();

  }

  @Override
  public void deletePlaylistByIdService(String id){
    playlistRepository.deleteById(id);
  }

  @Override
  public void createPlaylistByIdService(String id){
    playlistRepository.createPlaylistById(id);
  }

  @Override
  public boolean checkIfPlaylistExistsService(String id){
    boolean exists = playlistRepository.existsById(id);
    return exists;
  }
}
