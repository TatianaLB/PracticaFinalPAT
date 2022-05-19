package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CantantesModel;


public interface CantantesService {
    void createCantanteFavService(String name, String album,String user);
    boolean checkIfCantanteExistsService(int id, String user);
    Iterable<CantantesModel> getCantantesByUserService(String user);
}
