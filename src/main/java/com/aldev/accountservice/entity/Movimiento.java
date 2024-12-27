package com.aldev.accountservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "tipo_movimiento_id", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "cuenta_numero", nullable = false) // Este es el nombre de la columna que es la clave foránea en la tabla Movimiento
    private Cuenta cuenta;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "saldo", nullable = false)
    private double saldo;

    public Movimiento() {
    }

    public Movimiento(LocalDate fecha, TipoMovimiento tipoMovimiento, Cuenta cuenta, double valor, double saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.cuenta = cuenta;
        this.valor = valor;
        this.saldo = saldo;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
