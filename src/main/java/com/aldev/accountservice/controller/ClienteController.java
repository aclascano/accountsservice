package com.aldev.accountservice.controller;

import com.aldev.accountservice.entity.Cliente;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Validated
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente, BindingResult result) {
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            result.rejectValue("nombre", "field.required", "El nombre es obligatorio.");
        }
        if (cliente.getEdad() <= 0) {
            result.rejectValue("edad", "field.invalid", "La edad debe ser mayor que cero.");
        }
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente", String.valueOf(id));
        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado, BindingResult result) {

        if (clienteActualizado.getNombre() == null || clienteActualizado.getNombre().isEmpty()) {
            result.rejectValue("nombre", "field.required", "El nombre es obligatorio.");
        }
        if (clienteActualizado.getEdad() <= 0) {
            result.rejectValue("edad", "field.invalid", "La edad debe ser mayor que cero.");
        }
        if (result.hasErrors()) {

            return ResponseEntity.badRequest().body(null);
        }

        Cliente clienteActual = clienteService.actualizarCliente(id, clienteActualizado);
        return ResponseEntity.ok(clienteActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
