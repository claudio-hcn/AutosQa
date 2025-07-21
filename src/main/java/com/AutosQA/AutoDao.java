package com.AutosQA;

import com.AutosQA.db.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AutoDAO {

    public void crear(Auto auto) {
        String sql = "INSERT INTO auto (marca, modelo, fabricacion) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setLong(3, auto.getFabricacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Auto> listar() {
        List<Auto> autos = new ArrayList<>();
        String sql = "SELECT * FROM auto";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Auto auto = new Auto(
                    rs.getLong("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getLong("fabricacion")
                );
                autos.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autos;
    }

    public Auto buscarPorId(Long id) {
        String sql = "SELECT * FROM auto WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Auto(
                    rs.getLong("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getLong("fabricacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizar(Auto auto) {
        String sql = "UPDATE auto SET marca = ?, modelo = ?, fabricacion = ? WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setLong(3, auto.getFabricacion());
            ps.setLong(4, auto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM auto WHERE id = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

