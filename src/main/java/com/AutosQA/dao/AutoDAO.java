package com.AutosQA.dao;

import com.AutosQA.db.Conexion;
import com.AutosQA.model.Auto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {

    private Connection connection;

    // Constructor por defecto para uso en producción
    public AutoDAO() {
        try {
            this.connection = Conexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Constructor para inyectar conexión en pruebas
    public AutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void crear(Auto auto) {
        String sql = "INSERT INTO auto (marca, modelo, fabricacion) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setInt(3, auto.getFabricacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Auto> listar() {
        List<Auto> autos = new ArrayList<>();
        String sql = "SELECT * FROM auto";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Auto auto = new Auto(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("fabricacion")
                );
                autos.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autos;
    }

    public Auto buscarPorId(Integer id) {
        String sql = "SELECT * FROM auto WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Auto(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("fabricacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizar(Auto auto) {
        String sql = "UPDATE auto SET marca = ?, modelo = ?, fabricacion = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setInt(3, auto.getFabricacion());
            ps.setInt(4, auto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Integer id) {
        String sql = "DELETE FROM auto WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Auto> listarPorMarca(String marca) throws SQLException {
        List<Auto> autos = new ArrayList<>();
        String sql = "SELECT * FROM auto WHERE marca = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autos.add(mapResultSetToAuto(rs));
            }
        }
        return autos;
    }

    public List<Auto> listarPorAnioFabricacion(int anio) throws SQLException {
        List<Auto> autos = new ArrayList<>();
        String sql = "SELECT * FROM auto WHERE fabricacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, anio);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                autos.add(mapResultSetToAuto(rs));
            }
        }
        return autos;
    }
     
    private Auto mapResultSetToAuto(ResultSet rs) throws SQLException {
    return new Auto(
        rs.getInt("id"),
        rs.getString("marca"),
        rs.getString("modelo"),
        rs.getInt("fabricacion")
    );
}
  
}
