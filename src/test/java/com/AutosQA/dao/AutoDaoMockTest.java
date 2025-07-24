package com.AutosQA.dao;

import com.AutosQA.model.Auto;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AutoDaoMockTest {

    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockPreparedStatement;
    @Mock private ResultSet mockResultSet;
    @Mock private Statement mockStatement;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this); //  clave para evitar NullPointerException
    }
    private void imprimirEncabezado(String mensaje) {
        System.out.println("\n====================");
        System.out.println("  " + mensaje);
        System.out.println("====================");
    }

@Test
    @DisplayName("Test crear Auto con mock - versión segura")
    void testCrearAutoConMock() throws Exception {
        imprimirEncabezado("Test crear Auto con Mock");
        
        // Configurar el mock
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // 1 fila afectada
        
        // Crear el DAO con conexión mock
        AutoDAO daoMock = new AutoDAO(mockConnection);
        
        // Crear auto
        Auto auto = new Auto( "Toyota", "Corolla Mock", 2023);
        
        // Ejecutar el método
        assertDoesNotThrow(() -> daoMock.crear(auto));
        
        // Verificar que se llamaron los métodos esperados
        verify(mockConnection).prepareStatement(contains("INSERT"));
        verify(mockPreparedStatement).setString(1, "Toyota");
        verify(mockPreparedStatement).setString(2, "Corolla Mock");
        verify(mockPreparedStatement).setInt(3, 2023);
        verify(mockPreparedStatement).executeUpdate();
        
        System.out.println("Mock funcionando: Auto creado con mocks correctamente");
    }

    @Test
    @DisplayName("Test buscar Auto por ID con mock - versión segura")
    void testBuscarPorIdConMock() throws Exception {
        imprimirEncabezado("Test buscar por ID con Mock");
        
        // Configurar mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        
        // Simular que encuentra un registro
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(5);
        when(mockResultSet.getString("marca")).thenReturn("Honda Mock");
        when(mockResultSet.getString("modelo")).thenReturn("Civic Mock");
        when(mockResultSet.getInt("fabricacion")).thenReturn(2022);
        
        // Crear DAO con mock
        AutoDAO daoMock = new AutoDAO(mockConnection);
        
        // Buscar auto
        Auto resultado = daoMock.buscarPorId(5);
        
        // Verificar resultado
        assertNotNull(resultado);
        assertEquals(5, resultado.getId());
        assertEquals("Honda Mock", resultado.getMarca());
        assertEquals("Civic Mock", resultado.getModelo());
        assertEquals(2022, resultado.getFabricacion());
        
        // Verificar llamadas a los mocks
        verify(mockConnection).prepareStatement(contains("SELECT"));
        verify(mockPreparedStatement).setInt(1, 5);
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet).next();
        verify(mockResultSet).getInt("id");
        verify(mockResultSet).getString("marca");
        verify(mockResultSet).getString("modelo");
        verify(mockResultSet).getInt("fabricacion");
        
        System.out.println("Mock funcionando: Auto encontrado = " + resultado);
    }

    @Test
    @DisplayName("Test buscar Auto inexistente con mock")
    void testBuscarAutoInexistenteConMock() throws Exception {
        imprimirEncabezado("Test buscar Auto inexistente con Mock");
        
        // Configurar mocks
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No encuentra nada
        
        // Crear DAO con mock
        AutoDAO daoMock = new AutoDAO(mockConnection);
        
        // Buscar auto inexistente
        Auto resultado = daoMock.buscarPorId(999);
        
        // Verificar que retorna null
        assertNull(resultado);
        
        // Verificar llamadas
        verify(mockConnection).prepareStatement(contains("SELECT"));
        verify(mockPreparedStatement).setInt(1, 999);
        verify(mockPreparedStatement).executeQuery();
        verify(mockResultSet).next();
        
        System.out.println("Mock funcionando: Auto inexistente retornó null correctamente");
    }

   @Test
@DisplayName("Test listar con mock")
void testListarConMock() throws Exception {
    imprimirEncabezado("Test listar con Mock");
    
    // Configurar mocks para retornar lista vacía
    when(mockConnection.createStatement()).thenReturn(mockStatement);  // Esta línea era clave
    when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(false); // Lista vacía
    
    // Crear DAO con mock
    AutoDAO daoMock = new AutoDAO(mockConnection);
    
    // Listar autos
    List<Auto> resultado = daoMock.listar();
    
    // Verificar resultado
    assertNotNull(resultado);
    assertTrue(resultado.isEmpty());
    
    // Verificar llamadas
    verify(mockConnection).createStatement();
    verify(mockStatement).executeQuery(anyString());
    verify(mockResultSet).next();
    
    System.out.println("Mock funcionando: Lista vacía retornada correctamente");
}
}