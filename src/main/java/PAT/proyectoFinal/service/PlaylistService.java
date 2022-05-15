package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.PlaylistModel;

public interface PlaylistService {

  Iterable<PlaylistModel> getPlaylistsService();

  Iterable<PlaylistModel> getPlaylistByIdService(String id, String user);

  Iterable<PlaylistModel> getPlaylistByUserService(String user);

  Iterable<PlaylistModel> getPlaylistService();

  void deletePlaylistByNameService(String name, String user);

  void createPlaylistByIdService(String name, String user);

  boolean checkIfPlaylistExistsService(String id, String user);


}
