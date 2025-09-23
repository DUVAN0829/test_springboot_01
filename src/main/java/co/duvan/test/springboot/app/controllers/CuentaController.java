package co.duvan.test.springboot.app.controllers;

import co.duvan.test.springboot.app.models.Cuenta;
import co.duvan.test.springboot.app.models.TransaccionDTO;
import co.duvan.test.springboot.app.repositories.CuentaRepository;
import co.duvan.test.springboot.app.services.CuentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> transferir(@RequestBody TransaccionDTO dto) {

        service.transferir(dto.getBancoId(), dto.getCuentaOrigenId(), dto.getCuentaDestinoId(), dto.getMonto());

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("status", "OK");
        response.put("message", "Transferencia realizada con exito");
        response.put("transaccion", dto);

        return ResponseEntity.ok(response);

    }



}
