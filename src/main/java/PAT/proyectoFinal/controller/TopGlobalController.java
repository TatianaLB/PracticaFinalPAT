package PAT.proyectoFinal.controller;

import PAT.proyectoFinal.model.TopGlobalModel;
import PAT.proyectoFinal.service.TopGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/api/v1")
public class TopGlobalController {
    @Autowired
    private TopGlobalService listaTopservice;


    @GetMapping("/top50global")
    public ResponseEntity<Iterable<TopGlobalModel>> getTopGlobal() {
        return ResponseEntity.ok().body(listaTopservice.getTopGlobalService());
    }
}
