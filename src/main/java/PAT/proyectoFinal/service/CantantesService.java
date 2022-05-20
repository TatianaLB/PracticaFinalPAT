package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CantantesModel;


public interface CantantesService {
    void createCantanteFavService(CantantesModel cantante);
    boolean checkIfCantanteExistsService(int id, String user);
    Iterable<CantantesModel> getCantantesByUserService(String user);
}
