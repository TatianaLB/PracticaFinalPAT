package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.exception.PlaylistAlreadyExistsSignUpException;
import PAT.proyectoFinal.model.PlaylistModel;
import PAT.proyectoFinal.service.PlaylistService;
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
  PlaylistService playlistService;

  @GetMapping("/playlists")
  public ResponseEntity<Iterable<PlaylistModel>> getPlaylists(){

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Result", "OK");
    System.out.println(responseHeaders);
    return new ResponseEntity<Iterable<PlaylistModel>>(playlistService.getPlaylistsService(),responseHeaders, HttpStatus.OK);

    //return ResponseEntity.ok().body(playlistService.getPlaylistsService());
  }


  @GetMapping("/playlists/{user}")
  public ResponseEntity<Iterable<PlaylistModel>> getPlaylistsByUser(
          @PathVariable String user
  ){

    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Result", "OK");
    System.out.println(responseHeaders);
    return new ResponseEntity<Iterable<PlaylistModel>>(playlistService.getPlaylistByUserService(user),responseHeaders, HttpStatus.OK);

    //return ResponseEntity.ok().body(playlistService.getPlaylistsService());
  }


  @RequestMapping(value="/playlist/{id}", method = RequestMethod.GET)
  public ResponseEntity<Iterable<PlaylistModel>> getPlaylistByName(
          @PathVariable String id,
          @RequestParam(value="user", required=true) String user){


    //return ResponseEntity.ok().body(id);

    return ResponseEntity.ok().body(playlistService.getPlaylistByIdService(id, user));
  }

  @GetMapping("/playlist/delete/{id}")
  public ResponseEntity<Void> deletePlaylist(
          @PathVariable String id,
          @RequestParam(value="user", required=true) String user
  ){
    playlistService.deletePlaylistByNameService(id,user);

    return ResponseEntity.ok().build();
  }


  @RequestMapping(value="/playlist/create/{id}", method = RequestMethod.GET)
  public ResponseEntity<String> createPlaylist(
          @PathVariable String id,
          @RequestParam(value="user", required=true) String user) {
    boolean checkAlreadyExists = playlistService.checkIfPlaylistExistsService(id,user);
    if(checkAlreadyExists){
      throw new PlaylistAlreadyExistsSignUpException();
    }else{
      playlistService.createPlaylistByIdService(id,user);
      return new ResponseEntity<>("{\"result\" : \"OK\"}", HttpStatus.OK);
    }


  }




}
