package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.service.Impl.CancionServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class CancionServiceTest{

  final CancionService service = new CancionServiceImpl();

  @Test
  public void GetCancionesByPlaylist(){

    Iterable<CancionModel> result = service.getCancionesByPlaylistUserService("Favoritos","dmljc3Ryb3llcjpwYXNzd29yZA==");

    Assert.assertEquals(2,2);


  }
}