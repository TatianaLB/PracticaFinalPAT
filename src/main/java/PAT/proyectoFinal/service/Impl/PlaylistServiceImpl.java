package PAT.proyectoFinal.service.Impl;

import PAT.proyectoFinal.model.PlaylistModel;
import PAT.proyectoFinal.repository.PlaylistRepository;
import PAT.proyectoFinal.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlaylistServiceImpl implements PlaylistService {
  AtomicInteger id = new AtomicInteger();

  @Autowired
  PlaylistRepository playlistRepository;


  @Override
  public Iterable<PlaylistModel> getPlaylistsService(){
    return playlistRepository.findAll();
  }

  @Override
  public Iterable<PlaylistModel> getPlaylistByIdService(String id, String user){

    return playlistRepository.findPlaylistById(id, user);
  }

  @Override
  public Iterable<PlaylistModel> getPlaylistByUserService(String user){

    return playlistRepository.findPlaylistByUser(user);
  }

  @Override
  public Iterable<PlaylistModel> getPlaylistService(){
    return playlistRepository.findAll();

  }

  @Override
  public void deletePlaylistByNameService(String name, String user){
    playlistRepository.deletePlaylistByNameAndUser(name, user);
  }

  @Override
  public void createPlaylistByIdService(String name, String user){
    int id1 = id.getAndIncrement();
    playlistRepository.createPlaylistById(id1, name, user);
  }

  @Override
  public boolean checkIfPlaylistExistsService(String id, String user){
    String exists = playlistRepository.trueOrFalsePlaylistService(id,user);
    if(exists == "TRUE"){
      return true;
    }else{
      return false;
    }
  }




}
