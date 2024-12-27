package com.aldev.accountservice.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private double saldoInicial;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimiento> movimientos;

    public Cuenta(String numeroCuenta, String tipoCuenta, double saldoInicial, boolean estado, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
    }


    public Cuenta() {
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
