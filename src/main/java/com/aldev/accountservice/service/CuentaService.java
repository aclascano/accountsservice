package com.aldev.accountservice.service;

import com.aldev.accountservice.entity.Cliente;
import com.aldev.accountservice.entity.Cuenta;
import com.aldev.accountservice.exception.EntityDuplicateException;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.repository.ClienteRepository;
import com.aldev.accountservice.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Cuenta crearCuenta(Cuenta cuenta) {

        Optional<Cliente> clienteExistente = clienteRepository.findById(cuenta.getCliente().getPersona_id());
        if (clienteExistente.isEmpty()) {
            throw new EntityNotFoundException("El cliente con ID", cuenta.getCliente().getPersona_id().toString());
        }

        Optional<Cuenta> cuentaExistente = cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (cuentaExistente.isPresent()) {
            throw new EntityDuplicateException("La cuenta", cuenta.getNumeroCuenta());
        }

        cuenta.setCliente(clienteExistente.get());

        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
        return cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new EntityNotFoundException("La Cuenta", numeroCuenta));
    }

    public List<Cuenta> obtenerTodasLasCuentas() {
        return cuentaRepository.findAll();
    }


    public Cuenta actualizarCuenta(String numeroCuenta, Cuenta cuentaActualizada) {

        Optional<Cuenta> cuentaExistente = cuentaRepository.findById(numeroCuenta);
        if (cuentaExistente.isEmpty()) {
            throw new EntityNotFoundException("La Cuenta ", numeroCuenta);
        }


        Cuenta cuenta = cuentaExistente.get();
        cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
        cuenta.setEstado(cuentaActualizada.isEstado());

        return cuentaRepository.save(cuenta);
    }


    public void eliminarCuenta(String numeroCuenta) {
        if (!cuentaRepository.existsById(numeroCuenta)) {
            throw new EntityNotFoundException("La Cuenta ", numeroCuenta);
        }
        cuentaRepository.deleteById(numeroCuenta);
    }
}
