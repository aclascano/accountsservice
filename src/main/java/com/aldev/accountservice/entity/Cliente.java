package com.aldev.accountservice.entity;

import jakarta.persistence.*;

@Entity
public class Cliente extends Persona {

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "contraseña")
    private String contraseña;

    public Cliente(Long persona_id, String nombre, String genero, int edad, String identificacion, String direccion, String telefono, boolean estado, String contraseña) {
        super(persona_id, nombre, genero, edad, identificacion, direccion, telefono);
        this.estado = estado;
        this.contraseña = contraseña;
    }
    public Cliente() {
    }

    public Cliente(boolean estado, String contraseña) {
        this.estado = estado;
        this.contraseña = contraseña;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}