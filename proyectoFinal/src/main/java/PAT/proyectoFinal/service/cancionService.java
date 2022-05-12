package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.cancionModel;
import PAT.proyectoFinal.model.usuarioModel;

import java.util.List;

public interface cancionService {

  Iterable<cancionModel> getCancionesService();

  Iterable<cancionModel> getCancionByIdService(String id);

  void deleteCancionByIdService(String id);

  void createCancionService(cancionModel cancion);

}
