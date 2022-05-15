package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CancionModel;

public interface CancionService {

  Iterable<CancionModel> getCancionesService();

  Iterable<CancionModel> getCancionByIdService(String id);

  void deleteCancionByNameAndPlaylistService(String name, String playlist);

  void createCancionService(CancionModel cancion);

  Iterable<CancionModel> getCancionesByPlaylistService(String playlist);

}
