package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.UsuarioModel;
import PAT.proyectoFinal.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void userGetTest(){
        Iterable<UsuarioModel> usuarios = repository.findAll();

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuarios";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<UsuarioModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<UsuarioModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuarios);

    }

    @Test
    public void userGetByIdTest(){

        Iterable<UsuarioModel> usuarioIt = repository.getUsuarioById("tatianalb");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuario/tatianalb";
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization","Basic dGF0aWFuYWxiOnRhdGk=");
        HttpEntity<String> entity = new HttpEntity<>("tatianalb",headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<UsuarioModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<UsuarioModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuarioIt);

    }

    @Test
    public void userGetById2Test(){

        Iterable<UsuarioModel> usuarioIt = repository.getUsuarioById("GiraldaCB");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + port + "/api/v1/usuario/GiraldaCB";
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization","Basic R2lyYWxkYUNCOnBhc3N3b3Jk");
        HttpEntity<String> entity = new HttpEntity<>("GiraldaCB",headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Iterable<UsuarioModel>> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Iterable<UsuarioModel>>() {}

        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(usuarioIt);

    }

    @Test
    public void deleteUsuarioByIdTest(){

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/usuario/delete/tatianalb";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>("tatianalb",headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<Void> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Void>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


    //PostMapping /signup
    @Test
    public void userPostSignupTest(){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setUsername("GiraldaCB");
        usuario.setNombre("Gira");
        usuario.setApellido("CB");
        usuario.setEmail("giralda@gmail.com");
        usuario.setEdad(26);
        usuario.setPassword("password");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/signup";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic R2lyYWxkYUNCOnBhc3N3b3Jk");

        HttpEntity<UsuarioModel> entity = new HttpEntity<>(usuario,headers);

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

    //PostMapping /signup
    @Test
    public void userPostSignup2Test(){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setUsername("tatianalb");
        usuario.setNombre("Tatiana");
        usuario.setApellido("López");
        usuario.setEmail("tatiana@gmail.com");
        usuario.setEdad(21);
        usuario.setPassword("tati");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/signup";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic dGF0aWFuYWxiOnRhdGk=");

        HttpEntity<UsuarioModel> entity = new HttpEntity<>(usuario,headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<String>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        then(result.getBody()).isEqualTo("{\"result\" : \"User already exists.\"}");
    }

    //PostMapping /login
    @Test
    public void userPostLoginTest(){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setUsername("tatianalb");
        usuario.setNombre("Tatiana");
        usuario.setApellido("López");
        usuario.setEmail("tatiana@gmail.com");
        usuario.setEdad(21);
        usuario.setPassword("tati");

        //When: condiciones de nuestra prueba
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/login";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Basic dGF0aWFuYWxiOnRhdGk=");

        HttpEntity<UsuarioModel> entity = new HttpEntity<>(usuario,headers);

        //Given: Ejecutamos la prueba
        ResponseEntity<LoginResponse> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<LoginResponse>() {}
        );

        //Then: Evaluamos la prueba
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //then(result.getBody()).isEqualTo(loginResponse);
    }


}

