package dao;

import dto.ReservaDTO;
import util.ConexionDB;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ReservaDAO {

	// Insertar una nueva reserva
	public void insertar(ReservaDTO reserva) {
		String sql = "INSERT INTO reserva (id_cliente, id_habitacion, fecha_entrada, fecha_salida) VALUES (?, ?, ?, ?)";
		try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, reserva.getIdCliente());
			ps.setInt(2, reserva.getIdHabitacion());
			ps.setDate(3, Date.valueOf(reserva.getFechaEntrada()));
			ps.setDate(4, Date.valueOf(reserva.getFechaSalida()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Buscar una reserva por su identificador
	public ReservaDTO buscarPorId(int idReserva) {
		String sql = "SELECT * FROM reserva WHERE id_reserva = ?";
		try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idReserva);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new ReservaDTO(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_habitacion"),
						rs.getDate("fecha_entrada").toLocalDate(), rs.getDate("fecha_salida").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Listar todas las reservas
	public List<ReservaDTO> listarTodas() {
		List<ReservaDTO> reservas = new ArrayList<>();
		String sql = "SELECT * FROM reserva";
		try (Connection con = ConexionDB.obtenerConexion(); Statement st = con.createStatement()) {

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				reservas.add(
						new ReservaDTO(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_habitacion"),
								rs.getDate("fecha_entrada").toLocalDate(), rs.getDate("fecha_salida").toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	// Actualizar una reserva existente
	public void actualizar(ReservaDTO reserva) {
		String sql = "UPDATE reserva SET id_cliente = ?, id_habitacion = ?, fecha_entrada = ?, fecha_salida = ? WHERE id_reserva = ?";
		try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, reserva.getIdCliente());
			ps.setInt(2, reserva.getIdHabitacion());
			ps.setDate(3, Date.valueOf(reserva.getFechaEntrada()));
			ps.setDate(4, Date.valueOf(reserva.getFechaSalida()));
			ps.setInt(5, reserva.getIdReserva());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Eliminar una reserva por su identificador
	public void eliminar(int idReserva) {
		String sql = "DELETE FROM reserva WHERE id_reserva = ?";
		try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idReserva);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Listar todas las reservas activas (la fecha actual se encuentra entre
	// fecha_entrada y fecha_salida)
	public List<ReservaDTO> listarReservasActivas() {
		List<ReservaDTO> activas = new ArrayList<>();
		String sql = "SELECT * FROM reserva WHERE CURRENT_DATE BETWEEN fecha_entrada AND fecha_salida";
		try (Connection con = ConexionDB.obtenerConexion(); Statement st = con.createStatement()) {

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				activas.add(new ReservaDTO(rs.getInt("id_reserva"), rs.getInt("id_cliente"), rs.getInt("id_habitacion"),
						rs.getDate("fecha_entrada").toLocalDate(), rs.getDate("fecha_salida").toLocalDate()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activas;
	}

	// Mostrar reservas utilizando un Iterator
	public void mostrarReservasConIterator() {
		List<ReservaDTO> lista = listarTodas();
		Iterator<ReservaDTO> it = lista.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}