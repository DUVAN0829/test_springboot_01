package co.duvan.test.springboot.app.controllers;

import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.services.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {

    //* Vars
    private final CuentaService service;

    //* Constructor
    public CuentaController(CuentaService service) {
        this.service = service;
    }

    //* Methods handler
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cuenta detalle(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir() {

    }

}
