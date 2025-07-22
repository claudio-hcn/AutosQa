package com.AutosQA;
import com.AutosQA.dao.AutoDAO;
import com.AutosQA.model.Auto;
import com.AutosQA.db.Conexion;
import com.AutosQA.db.CrearTabla;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;  


public class Main {
    public static void main(String[] args) {
        // Crear tabla
        CrearTabla.crearTabla();

        // DAO
        AutoDAO autoDAO = new AutoDAO();

        System.out.println("Conexion establecida");

        // Crear auto
        Auto auto = new Auto(null, "Toyota", "Corolla", 2020L);
        autoDAO.crear(auto);

        // Listar autos
        List<Auto> autos = autoDAO.listar();
        System.out.println("Listado de autos:");
        for (Auto a : autos) {
            System.out.println(a);
        }

        // Buscar por ID
        Auto encontrado = autoDAO.buscarPorId(1L);
        if (encontrado != null) {
            System.out.println("Auto encontrado: " + encontrado);
        }

        // Actualizar auto
        encontrado.setModelo("Corolla GLi");
        autoDAO.actualizar(encontrado);
        System.out.println("Auto actualizado: " + autoDAO.buscarPorId(1L));

        // Eliminar auto
        autoDAO.eliminar(1L);
        System.out.println("Listado después de eliminar:");
        autoDAO.listar().forEach(System.out::println);
    }
}