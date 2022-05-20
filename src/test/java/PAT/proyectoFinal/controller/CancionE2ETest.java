package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.model.UsuarioModel;
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

        Iterable<CancionModel> cancionIt = repository.getCancionById("-1");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/cancion/-1";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(Integer.toString(-1),headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<CancionModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<CancionModel>>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(cancionIt);

    }


    @Test
    public void createCancionByIdTest(){
        CancionModel cancion = new CancionModel();
        cancion.setId(1);
        cancion.setNombre("Purpurina");
        cancion.setPlaylist("TemazosTuenti");
        cancion.setArtista("Gambino");
        cancion.setAlbum("Purpurina");
        cancion.setLongitud(171);
        cancion.setUser("dGF0aWFuYWxiOnRhdGk=");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/cancion/create";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CancionModel> entity = new HttpEntity<>(cancion,headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo("{\"result\" : \"OK\"}");
    }

    @Test
    public void cancionGetByPlaylistTest(){

        Iterable<CancionModel> cancionIt = repository.getCancionByPlaylistUser("Favoritos","vicstroyer");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/canciones/playlist/-1?accessToken=dmljc3Ryb3llcjpwYXNzd29yZA==";
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
        then(result.getBody()).isEqualTo(cancionIt);

    }

}
