package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.exception.PlaylistAlreadyExistsSignUpException;
import PAT.proyectoFinal.model.playlistModel;
import PAT.proyectoFinal.service.playlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class PlaylistController {

  @Autowired
  playlistService playlistService;


  @GetMapping("/playlists")
  public ResponseEntity<Iterable<playlistModel>> getPlaylists(){

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Result", "OK");
    System.out.println(responseHeaders);
    return new ResponseEntity<Iterable<playlistModel>>(playlistService.getPlaylistsService(),responseHeaders, HttpStatus.OK);

    //return ResponseEntity.ok().body(playlistService.getPlaylistsService());
  }

  //LOS TRES DE ARRIBA FUNCIONAN BIEN.

  //NO ESTA TERMINADO
  @GetMapping("/playlist/{id}")
  public ResponseEntity<Iterable<playlistModel>> getPlaylistByName(@PathVariable String id){


    //return ResponseEntity.ok().body(id);

    return ResponseEntity.ok().body(playlistService.getPlaylistByIdService(id));
  }

  @GetMapping("/playlist/delete/{id}")
  public ResponseEntity<Void> deletePlaylist(@PathVariable String id){
    playlistService.deletePlaylistByIdService(id);

    return ResponseEntity.ok().build();
  }


  @GetMapping("/playlist/create/{id}")
  public ResponseEntity<String> createPlaylist(
          @PathVariable String id) {
    boolean checkAlreadyExists = playlistService.checkIfPlaylistExistsService(id);
    if(checkAlreadyExists){
      throw new PlaylistAlreadyExistsSignUpException();
    }else{
      playlistService.createPlaylistByIdService(id);
      return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
    }


  }
}
