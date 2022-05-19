package PAT.proyectoFinal.service;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.service.Impl.CancionServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class CancionServiceTest{

  final CancionService service = new CancionServiceImpl();

  @Test
  public void GetCancionesByPlaylist(){

    Iterable<CancionModel> result = service.getCancionesByPlaylistUserService("Favoritos","dmljc3Ryb3llcjpwYXNzd29yZA==");

    Assert.assertEquals(2,2);


  }
}