package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.exception.CantanteAlreadyExistsException;
import PAT.proyectoFinal.model.CantantesModel;
import PAT.proyectoFinal.service.CantantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class CantantesController {
    @Autowired
    private CantantesService cantantesService;

    @RequestMapping(value="/cantantes/create", method = RequestMethod.GET)
    public ResponseEntity<String> createCantanteFav(
            @RequestBody CantantesModel cantante) {
        boolean checkAlreadyExists = cantantesService.checkIfCantanteExistsService(cantante.getId(),cantante.getUser());
        if(checkAlreadyExists){
            throw new CantanteAlreadyExistsException();
        }else{
            cantantesService.createCantanteFavService(cantante);
            return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
        }
    }

    //Mostrar por usuario
    @GetMapping("/cantantes/{user}")
    public ResponseEntity<Iterable<CantantesModel>> getCantantesFavByUser(
            @PathVariable String user
    ){

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Result", "OK");
        System.out.println(responseHeaders);
        return new ResponseEntity<Iterable<CantantesModel>>(cantantesService.getCantantesByUserService(user),responseHeaders, HttpStatus.OK);

        //return ResponseEntity.ok().body(playlistService.getPlaylistsService());
    }

}

