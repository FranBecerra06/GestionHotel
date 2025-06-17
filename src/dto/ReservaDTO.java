package dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReservaDTO {

	private int idReserva;
	private int idCliente;
	private int idHabitacion;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;

	public ReservaDTO(int idReserva, int idCliente, int idHabitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
		this.idReserva = idReserva;
		this.idCliente = idCliente;
		this.idHabitacion = idHabitacion;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

}
