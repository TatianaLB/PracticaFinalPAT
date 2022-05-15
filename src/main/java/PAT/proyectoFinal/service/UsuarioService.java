package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.UsuarioModel;

public interface UsuarioService {

  Iterable<UsuarioModel> getUsuariosService();
  Iterable<UsuarioModel> getUsuarioByIdService(String id);
  void deleteUsuarioByIdService(String id);
  String compararYCrearUsuarioService(UsuarioModel usuario);
}
