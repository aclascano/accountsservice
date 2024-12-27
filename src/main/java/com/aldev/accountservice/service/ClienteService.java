package com.aldev.accountservice.service;

import com.aldev.accountservice.entity.Cliente;
import com.aldev.accountservice.exception.EntityDuplicateException;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente guardarCliente(Cliente cliente) {
        if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
            throw new EntityDuplicateException("El cliente", cliente.getIdentificacion());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long clienteId, Cliente clienteActualizado) {

        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
        if (clienteExistente.isEmpty()) {
            throw new EntityNotFoundException("El cliente", clienteId.toString());
        }

        Cliente cliente = clienteExistente.get();
        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setGenero(clienteActualizado.getGenero());
        cliente.setEdad(clienteActualizado.getEdad());
        cliente.setIdentificacion(clienteActualizado.getIdentificacion());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setContraseña(clienteActualizado.getContraseña());
        cliente.setEstado(clienteActualizado.getEstado());

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new EntityNotFoundException("El cliente", clienteId.toString());
        }
        clienteRepository.deleteById(clienteId);
    }

    public Cliente obtenerClientePorId(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("El cliente", clienteId.toString()));
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }
}
