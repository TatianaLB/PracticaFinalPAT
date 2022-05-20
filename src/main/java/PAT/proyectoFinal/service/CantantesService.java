package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CantantesModel;


public interface CantantesService {
    void createCantanteFavService(CantantesModel cantante);
    boolean checkIfCantanteExistsService(String nombre, String user);
    Iterable<CantantesModel> getCantantesByUserService(String user);
}
