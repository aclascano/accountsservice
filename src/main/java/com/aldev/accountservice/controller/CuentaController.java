package com.aldev.accountservice.controller;

import com.aldev.accountservice.entity.Cuenta;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@Validated
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta, BindingResult result) {
        if (cuenta.getNumeroCuenta() == null || cuenta.getNumeroCuenta().isEmpty()) {
            result.rejectValue("numeroCuenta", "field.required", "El número de cuenta es obligatorio.");
        }
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Cuenta nuevaCuenta = cuentaService.crearCuenta(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
    }

    @GetMapping
    public List<Cuenta> obtenerCuentas() {
        return cuentaService.obtenerTodasLasCuentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable String id) {
        Cuenta cuenta = cuentaService.obtenerCuentaPorNumero(id);
        if (cuenta == null) {
            throw new EntityNotFoundException("Cuenta", String.valueOf(id));
        }
        return ResponseEntity.ok(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable String id, @RequestBody Cuenta cuentaActualizada, BindingResult result) {
        if (cuentaActualizada.getNumeroCuenta() == null || cuentaActualizada.getNumeroCuenta().isEmpty()) {
            result.rejectValue("numeroCuenta", "field.required", "El número de cuenta es obligatorio.");
        }
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Cuenta cuentaActual = cuentaService.actualizarCuenta(id, cuentaActualizada);
        return ResponseEntity.ok(cuentaActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
