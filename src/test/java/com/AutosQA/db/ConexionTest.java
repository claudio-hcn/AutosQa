package com.AutosQA.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class ConexionTest {

    @Test
    @DisplayName("Debería establecer conexión exitosamente")
    void getConnection_deberiaEstablecerConexionExitosamente() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        assertNotNull(connection, "La conexión no debería ser null");
        assertFalse(connection.isClosed(), "La conexión debería estar abierta");
        
        connection.close();
    }
    
    @Test
    @DisplayName("Debería conectar a base de datos H2 en memoria")
    void getConnection_deberiaConectarAH2EnMemoria() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        DatabaseMetaData metaData = connection.getMetaData();
        String databaseProductName = metaData.getDatabaseProductName();
        String url = metaData.getURL();
        
        assertEquals("H2", databaseProductName, "Debería usar base de datos H2");
        assertTrue(url.contains("jdbc:h2:mem:autosdb"), "URL debería contener jdbc:h2:mem:autosdb");
        
        // assertTrue(url.contains("DB_CLOSE_DELAY=-1"), "URL debería contener DB_CLOSE_DELAY=-1");
        
        connection.close();
    }
    
    @Test
    @DisplayName("Debería usar usuario 'sa' sin contraseña")
    void getConnection_deberiaUsarCredencialesCorrectas() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        DatabaseMetaData metaData = connection.getMetaData();
        String userName = metaData.getUserName();
        
        assertEquals("SA", userName.toUpperCase(), "El usuario debería ser 'SA'");
        
        connection.close();
    }
    
    @Test
    @DisplayName("Debería permitir múltiples conexiones")
    void getConnection_deberiaPermitirMultiplesConexiones() throws SQLException {
        Connection connection1 = Conexion.getConnection();
        Connection connection2 = Conexion.getConnection();
        
        assertNotNull(connection1, "Primera conexión no debería ser null");
        assertNotNull(connection2, "Segunda conexión no debería ser null");
        assertNotSame(connection1, connection2, "Deberían ser objetos de conexión diferentes");
        
        assertFalse(connection1.isClosed(), "Primera conexión debería estar abierta");
        assertFalse(connection2.isClosed(), "Segunda conexión debería estar abierta");
        
        connection1.close();
        connection2.close();
    }
    
    @Test
    @DisplayName("Debería mantener datos entre conexiones (DB_CLOSE_DELAY=-1)")
    void getConnection_deberiaMantenerDatosEntreConexiones() throws SQLException {
        // Primera conexión: crear tabla y datos
        Connection connection1 = Conexion.getConnection();
        var stmt1 = connection1.createStatement();
        stmt1.execute("CREATE TABLE test_persistencia (id INT, nombre VARCHAR(50))");
        stmt1.execute("INSERT INTO test_persistencia VALUES (1, 'Test')");
        stmt1.close();
        connection1.close();
        
        // Segunda conexión: verificar que los datos persisten
        Connection connection2 = Conexion.getConnection();
        var stmt2 = connection2.createStatement();
        var rs = stmt2.executeQuery("SELECT COUNT(*) as count FROM test_persistencia");
        
        assertTrue(rs.next(), "Debería haber resultados");
        assertEquals(1, rs.getInt("count"), "Debería haber 1 registro");
        
        rs.close();
        stmt2.close();
        connection2.close();
    }
    
    @Test
    @DisplayName("Debería ser compatible con autocommit por defecto")
    void getConnection_deberiaSerCompatibleConAutocommit() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        assertTrue(connection.getAutoCommit(), "AutoCommit debería estar habilitado por defecto");
        
        connection.close();
    }
    
    @Test
    @DisplayName("Debería permitir operaciones básicas de SQL")
    void getConnection_deberiaPermitirOperacionesBasicasSQL() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        // Crear tabla
        var createStmt = connection.createStatement();
        assertDoesNotThrow(() -> {
            createStmt.execute("CREATE TABLE test_operaciones (id INT PRIMARY KEY, descripcion VARCHAR(100))");
        });
        createStmt.close();
        
        // Insertar datos
        var insertStmt = connection.prepareStatement("INSERT INTO test_operaciones (id, descripcion) VALUES (?, ?)");
        insertStmt.setInt(1, 1);
        insertStmt.setString(2, "Test descripción");
        int rowsInserted = insertStmt.executeUpdate();
        assertEquals(1, rowsInserted, "Debería insertar 1 fila");
        insertStmt.close();
        
        // Consultar datos
        var selectStmt = connection.createStatement();
        var rs = selectStmt.executeQuery("SELECT * FROM test_operaciones WHERE id = 1");
        assertTrue(rs.next(), "Debería encontrar el registro insertado");
        assertEquals("Test descripción", rs.getString("descripcion"));
        rs.close();
        selectStmt.close();
        
        connection.close();
    }
    
    @Test
    @DisplayName("Debería manejar correctamente el cierre de conexión")
    void getConnection_deberiaManejarCierreCorrectamente() throws SQLException {
        Connection connection = Conexion.getConnection();
        
        assertFalse(connection.isClosed(), "Conexión debería estar abierta inicialmente");
        
        connection.close();
        
        assertTrue(connection.isClosed(), "Conexión debería estar cerrada después de close()");
    }
    
    @Test
    @DisplayName("Debería permitir verificar metadatos de la conexión")
    void getConnection_deberiaPermitirVerificarMetadatos() throws SQLException {
        Connection connection = Conexion.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        
        assertNotNull(metaData, "Metadatos no deberían ser null");
        assertNotNull(metaData.getDatabaseProductName(), "Nombre del producto no debería ser null");
        assertNotNull(metaData.getDatabaseProductVersion(), "Versión no debería ser null");
        assertNotNull(metaData.getDriverName(), "Nombre del driver no debería ser null");
        
        connection.close();
    }

    // Tests básicos adicionales
    @Test
    void getConnection_noDeberiaSerNull() throws SQLException {
        Connection conn = Conexion.getConnection();
        assertNotNull(conn);
        conn.close();
    }

    @Test
    void getConnection_deberiaEstarAbierta() throws SQLException {
        Connection conn = Conexion.getConnection();
        assertFalse(conn.isClosed());
        conn.close();
    }

    @Test
    void getConnection_noDeberiaLanzarExcepcion() {
        assertDoesNotThrow(() -> {
            Connection conn = Conexion.getConnection();
            conn.close();
        });
    }
}