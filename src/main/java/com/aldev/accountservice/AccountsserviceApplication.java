package com.aldev.accountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsserviceApplication.class, args);
	}
	/*
	@Bean
	public CommandLineRunner initTipoMovimientos(TipoMovimientoRepository tipoMovimientoRepository) {
		return args -> {
			// Inicialización de los tipos de movimiento en la base de datos
			if (!tipoMovimientoRepository.existsByDetalle("Depósito")) {
				tipoMovimientoRepository.save(new TipoMovimiento("Depósito"));
			}
			if (!tipoMovimientoRepository.existsByDetalle("Retiro")) {
				tipoMovimientoRepository.save(new TipoMovimiento("Retiro"));
			}
			if (!tipoMovimientoRepository.existsByDetalle("Transferencia")) {
				tipoMovimientoRepository.save(new TipoMovimiento("Transferencia"));
			}
			if (!tipoMovimientoRepository.existsByDetalle("Pago")) {
				tipoMovimientoRepository.save(new TipoMovimiento("Pago"));
			}

			System.out.println("Tipos de movimiento inicializados correctamente.");
		};
	}
	*/

}
