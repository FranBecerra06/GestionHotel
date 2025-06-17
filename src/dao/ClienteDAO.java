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

			stmt.setInt(1, cliente.getId_cliente());
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

	
	
	
	
	
	
}
