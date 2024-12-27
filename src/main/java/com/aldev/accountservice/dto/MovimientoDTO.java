package com.aldev.accountservice.dto;

public class MovimientoDTO {

    private String numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private boolean estado;
    private String descripcionMovimiento;
    private double valorMovimiento;
    private double saldoFinal;

    // Constructor
    public MovimientoDTO(String numeroCuenta, String tipoCuenta, double saldoInicial, boolean estado,
                         String descripcionMovimiento, double valorMovimiento, double saldoFinal) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.descripcionMovimiento = descripcionMovimiento;
        this.valorMovimiento = valorMovimiento;
        this.saldoFinal = saldoFinal;
    }

    // Getters and Setters
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

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public double getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(double valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}
