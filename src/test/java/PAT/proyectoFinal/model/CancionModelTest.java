package PAT.proyectoFinal.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class CancionModelTest{

  @Test
  public void SetLongitudCancion(){

    CancionModel cancion = new CancionModel();

    cancion.setLongitud(111111);
    Assert.assertEquals(99999,cancion.getLongitud());

    cancion.setLongitud(11111);
    Assert.assertEquals(11111, cancion.getLongitud());

  }
}