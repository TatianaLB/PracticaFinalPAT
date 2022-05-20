package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.CancionModel;
import PAT.proyectoFinal.model.CantantesModel;
import PAT.proyectoFinal.repository.CantantesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CantantesE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private CantantesRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCantantesFavByUserTest() {
        Iterable<CantantesModel> cantantesIt = repository.findCantantesByUser("dGF0aWFuYWxiOnRhdGk=");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/cantantes/dGF0aWFuYWxiOnRhdGk=";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<CantantesModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<CantantesModel>>() {
                }

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(cantantesIt);
    }

    @Test
    public void createCantanteFavCorrectTest(){
        CantantesModel cantante = new CantantesModel();
        cantante.setId(1);
        cantante.setNombre("Britney Spears");
        cantante.setAlbum("Circus");
        cantante.setUser("dGF0aWFuYWxiOnRhdGk=");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/cantantes/create";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CantantesModel> entity = new HttpEntity<>(cantante,headers);

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

}
