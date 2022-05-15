package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.repository.CancionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.BDDAssertions.then;

import java.lang.reflect.Type;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CancionE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private CancionRepository repository;

    @Autowired
    private TestRestTemplate restTemplate; //Como si fuese Google Chrome, pero para hacer Testing

    @Test
    public void cancionGetTest(){
        Iterable<CancionModel> canciones = repository.findAll(); //canciones tiene todas las canciones de la base de datos

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/canciones";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<CancionModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<CancionModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(canciones);

    }

    @Test
    public void cancionGetByIdTest(){
        CancionModel cancion = new CancionModel();
        cancion.setId(-2);
        cancion.setNombre("Gasolina");
        cancion.setPlaylist("TemazosTuenti");
        cancion.setArtista("Daddy Yankee");
        cancion.setAlbum("Barrio Fino");
        cancion.setLongitud(192);

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/cancion/-2";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("-2",headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<CancionModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<CancionModel>>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(cancion);

    }

}
