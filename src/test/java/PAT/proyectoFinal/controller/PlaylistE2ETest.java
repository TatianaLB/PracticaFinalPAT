package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.PlaylistModel;
import PAT.proyectoFinal.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaylistE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PlaylistRepository repository;

    @Test
    public void playlistsGetTest(){
        Iterable<PlaylistModel> playlists = repository.findAll();

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/playlists";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<PlaylistModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<PlaylistModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(playlists);

    }

}
