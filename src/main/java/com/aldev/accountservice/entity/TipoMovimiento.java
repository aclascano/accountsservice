package com.aldev.accountservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_movimiento")
public class TipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detalle", nullable = false, unique = true)
    private String detalle;


    public TipoMovimiento() {
    }

    public TipoMovimiento(String detalle) {
        this.detalle = detalle;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
