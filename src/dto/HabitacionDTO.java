package dto;

public class HabitacionDTO {

	private int idHabitacion;
	private int numeroHabitacion;
	private int tipoHabitacion;
	private Double precioHabitacion;

	public HabitacionDTO(int idHabitacion, int numeroHabitacion, int tipoHabitacion, Double precioHabitacion) {
		this.idHabitacion = idHabitacion;
		this.numeroHabitacion = numeroHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.precioHabitacion = precioHabitacion;
	}

	public int getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public int getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(int tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public Double getPrecioHabitacion() {
		return precioHabitacion;
	}

	public void setPrecioHabitacion(Double precioHabitacion) {
		this.precioHabitacion = precioHabitacion;
	}

}
