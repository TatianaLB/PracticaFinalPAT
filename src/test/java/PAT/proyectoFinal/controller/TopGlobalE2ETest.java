package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.TopGlobalModel;
import PAT.proyectoFinal.repository.TopGlobalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopGlobalE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TopGlobalRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void topGlobalGetTest(){
        Iterable<TopGlobalModel> canciones = repository.findAll();

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/top50global";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<TopGlobalModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<TopGlobalModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(canciones);

    }
}
