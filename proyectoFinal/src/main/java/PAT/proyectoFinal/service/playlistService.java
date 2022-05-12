package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.playlistModel;

import java.util.List;

public interface playlistService {

  Iterable<playlistModel> getPlaylistsService();

  Iterable<playlistModel> getPlaylistByIdService(String id);

  Iterable<playlistModel> getPlaylistService();

  void deletePlaylistByIdService(String id);

  void createPlaylistByIdService(String id);

  boolean checkIfPlaylistExistsService(String id);
}
