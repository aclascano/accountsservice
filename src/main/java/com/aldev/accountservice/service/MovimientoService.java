package com.aldev.accountservice.service;

import com.aldev.accountservice.dto.MovimientoDTO;
import com.aldev.accountservice.entity.Cuenta;
import com.aldev.accountservice.entity.Movimiento;
import com.aldev.accountservice.entity.TipoMovimiento;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.exception.InsufficientBalanceException;
import com.aldev.accountservice.repository.CuentaRepository;
import com.aldev.accountservice.repository.MovimientoRepository;
import com.aldev.accountservice.repository.TipoMovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final TipoMovimientoRepository tipoMovimientoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository,
                             CuentaRepository cuentaRepository,
                             TipoMovimientoRepository tipoMovimientoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.tipoMovimientoRepository = tipoMovimientoRepository;
    }

    public Movimiento guardarMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento", id.toString()));
    }

    public List<MovimientoDTO> listarMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        List<MovimientoDTO> respuestas = new ArrayList<>();

        for (Movimiento mov : movimientos) {
            Cuenta cuenta = mov.getCuenta();
            TipoMovimiento tipoMovimiento = mov.getTipoMovimiento();

            String descripcionMovimiento = tipoMovimiento.getDetalle() + " de " + mov.getValor();
            double saldoFinal = mov.getSaldo();

            MovimientoDTO respuesta = new MovimientoDTO(
                    cuenta.getNumeroCuenta(),
                    cuenta.getTipoCuenta(),
                    cuenta.getSaldoInicial(),
                    cuenta.isEstado(),
                    descripcionMovimiento,
                    mov.getValor(),
                    saldoFinal
            );

            respuestas.add(respuesta);
        }

        return respuestas;
    }

    public void eliminarMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new EntityNotFoundException("Movimiento", id.toString());
        }
        movimientoRepository.deleteById(id);
    }

    @Transactional
    public Movimiento registrarMovimiento(String cuentaId, Long tipoMovimientoId, double valor) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta", cuentaId.toString()));


        TipoMovimiento tipoMovimiento = tipoMovimientoRepository.findById(tipoMovimientoId)
                .orElseThrow(() -> new EntityNotFoundException("TipoMovimiento", tipoMovimientoId.toString()));

        if (tipoMovimiento.getDetalle().equalsIgnoreCase("Retiro") && cuenta.getSaldoInicial() < valor) {
            throw new InsufficientBalanceException("Saldo no disponible para realizar esta transacción.");
        }

        double nuevoSaldo = tipoMovimiento.getDetalle().equalsIgnoreCase("Retiro") ?
                cuenta.getSaldoInicial() - valor :
                cuenta.getSaldoInicial() + valor;


        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDate.from(LocalDateTime.now()));
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(valor);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }
}

