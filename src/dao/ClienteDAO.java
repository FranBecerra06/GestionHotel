package dao;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.ClienteDTO;
import util.ConexionDB;

public class ClienteDAO {

	// CRUD COMPLETO

	public void insertarCliente(ClienteDTO cliente) {

		String sql = "INSERT INTO cliente (id_cliente, nombre, email) VALUES (?, ?, ?)";

		try (Connection conexion = ConexionDB.obtenerConexion();

				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, cliente.getIdCliente());
			stmt.setString(2, cliente.getNombre());
			stmt.setString(3, cliente.getEmail());

			int filas = stmt.executeUpdate();

			if (filas > 0) {

				System.out.println("Cliente agregado con exito.");

			} else {
				System.out.println("Ha habido un error al agregar el cliente.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<ClienteDTO> obtenerClientes() {

		List<ClienteDTO> listaClientes = new ArrayList<>();

		String sql = "SELECT * FROM cliente";

		try (Connection conexion = ConexionDB.obtenerConexion();

				PreparedStatement stmt = conexion.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {

				int idCliente = rs.getInt("id_cliente");
				String nombreCliente = rs.getString("nombre");
				String emailCliente = rs.getString("email");

				listaClientes.add(new ClienteDTO(idCliente, nombreCliente, emailCliente));

			}
		} catch (SQLException e) {

			System.out.println("Error al obtener clientes.");
			e.printStackTrace();
		}
		return listaClientes;

	}

	public void modificarClientes(int idCliente, String nombre, String email) {

		String sql = "UPDATE cliente SET nombre = ?, email = ? WHERE id_cliente = ?";

		try (Connection conexion = ConexionDB.obtenerConexion();
				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setString(1, nombre);
			stmt.setString(2, email);

			int filas = stmt.executeUpdate();

			if (filas > 0) {

				System.out.println("Cliente modificado correctamente.");

			} else {
				System.out.println("Ha habido un error al modificar el cliente.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void eliminarCliente(int idCliente) {

		String sql = "DELETE FROM cliente WHERE id_cliente = ?";

		try (Connection conexion = ConexionDB.obtenerConexion();

				PreparedStatement stmt = conexion.prepareStatement(sql)) {

			stmt.setInt(1, idCliente);
			int filas = stmt.executeUpdate();

			if (filas > 0) {

				System.out.println("Cliente eliminado con exito.");

			} else {
				System.out.println("Error al eliminar el cliente.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
