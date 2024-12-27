package com.aldev.accountservice.service;

import com.aldev.accountservice.entity.Persona;
import com.aldev.accountservice.exception.EntityDuplicateException;
import com.aldev.accountservice.exception.EntityNotFoundException;
import com.aldev.accountservice.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La persona", id.toString()));
    }

    public Iterable<Persona> obtenerPersonas() {
        return personaRepository.findAll();
    }

    public Persona guardarPersona(Persona persona) {

        if (personaRepository.existsByIdentificacion(persona.getIdentificacion())) {
            throw new EntityDuplicateException("La Persona ", persona.getIdentificacion());
        }
        return personaRepository.save(persona);
    }

    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Persona persona = obtenerPersonaPorId(id);
        persona.setNombre(personaActualizada.getNombre());
        persona.setGenero(personaActualizada.getGenero());
        persona.setEdad(personaActualizada.getEdad());
        persona.setIdentificacion(personaActualizada.getIdentificacion());
        persona.setDireccion(personaActualizada.getDireccion());
        persona.setTelefono(personaActualizada.getTelefono());
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) {
        Persona persona = obtenerPersonaPorId(id);
        personaRepository.delete(persona);
    }
}
