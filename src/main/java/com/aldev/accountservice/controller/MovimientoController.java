package com.aldev.accountservice.controller;

import com.aldev.accountservice.dto.MovimientoDTO;
import com.aldev.accountservice.entity.Movimiento;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.exception.InsufficientBalanceException;
import com.aldev.accountservice.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> listarMovimientos() {
        List<MovimientoDTO> movimientos = movimientoService.listarMovimientos();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable Long id) {
        try {
            Movimiento movimiento = movimientoService.obtenerMovimientoPorId(id);
            return ResponseEntity.ok(movimiento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Map<String, Object> request) {
        try {
            String cuentaId = (String) request.get("cuentaId");
            Long tipoMovimientoId = Long.valueOf(request.get("tipoMovimientoId").toString());
            double valor = Double.valueOf(request.get("valor").toString());

            Movimiento movimiento = movimientoService.registrarMovimiento(cuentaId, tipoMovimientoId, valor);
            return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        try {
            movimientoService.eliminarMovimiento(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
