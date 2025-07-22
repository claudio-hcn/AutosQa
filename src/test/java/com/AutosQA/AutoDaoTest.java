package com.AutosQA;

import com.AutosQA.dao.AutoDAO;
import com.AutosQA.model.Auto;
import com.AutosQA.db.Conexion;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutoDaoTest {

    private AutoDAO autoDAO;
    private Connection connection;

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void imprimirEncabezado(String nombreTest) {
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("");
        System.out.println(ANSI_BLUE + nombreTest + ANSI_RESET);
    }

    @BeforeAll
    void setup() throws Exception {
        connection = Conexion.getConnection();
        autoDAO = new AutoDAO(connection);
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS auto (" +
                         "id IDENTITY PRIMARY KEY, " +
                         "marca VARCHAR(255), " +
                         "modelo VARCHAR(255), " +
                         "fabricacion BIGINT" +
                         ")";
            stmt.execute(sql);
        }
        imprimirEncabezado("Inicio de pruebas - Tabla creada");
    }

    @AfterAll
    void teardown() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE auto");
        }
        connection.close();
        imprimirEncabezado("Fin de pruebas - Tabla eliminada");
    }

    @BeforeEach
    public void limpiarTabla() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM auto");
        }
        System.out.println("Tabla 'auto' limpiada antes del test.");
    }

    @Test
    @DisplayName("Test crear Auto")
    void testCrear() {
        imprimirEncabezado("Test crear Auto");
        autoDAO.crear(new Auto("Toyota", "Corolla", 2020));
        autoDAO.crear(new Auto("Toyota", "4runner", 2020));
        autoDAO.crear(new Auto("Toyota", "Yaris", 2021));
        autoDAO.crear(new Auto("Ford", "Fiesta", 2019));
    

        List<Auto> autos = autoDAO.listar();
        assertFalse(autos.isEmpty(), "La lista de autos no debe estar vacía");
        System.out.println("Resultado del listar por marca: ");
        autos.forEach(System.out::println);
        assertEquals(4, autos.size());
    }

    /** Este test representa la refactorización de crear Auto 
     * las mejoras que aplicamos incluyen validar que los campos no esten vacios
     * **/
    @Test
    @DisplayName("Test crear Auto con atributos válidos e inválidos")
    void testCrearAutoValidaciones() {
        imprimirEncabezado("Test crear Auto con validaciones");

        // ✅ Auto válido
        Auto autoValido = new Auto(null, "Toyota", "Corolla", 2021);
        assertDoesNotThrow(() -> autoDAO.crear(autoValido));

        // ❌ Marca vacía
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "", "Corolla", 2021);
        });

        // ❌ Modelo nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "Toyota", null, 2021);
        });

        // ❌ Año nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "Toyota", "Corolla", null);
        });
    }


    /** Este test podemos ver como detecta el error de validación 
     * al enviar la marca vacia
     * .. **/
    @Test
    @DisplayName("Test validación - marca vacía lanza excepción")
    void testValidacionMarcaVacia() {
        imprimirEncabezado("Test validación marca vacía");
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "", "ModeloTest", 2022);

        List<Auto> autos = autoDAO.listar();
        System.out.println("Listado de autos: " + autos.size());
        autos.forEach(System.out::println);    
        });
   }


    @Test
    @DisplayName("Test listar Autos")
    void testListar() {

        imprimirEncabezado("Test listar Autos");
        Auto auto = new Auto(null, "Chevrolet", "Spark", 2017);
        Auto auto1 = new Auto(null, "Chevrolet", "Corsa", 2016);
        autoDAO.crear(auto);
        autoDAO.crear(auto1);
        List<Auto> autos = autoDAO.listar();
        System.out.println("Listado de autos: " + autos.size());
        autos.forEach(System.out::println);
        assertNotNull(autos, "La lista de autos no debe ser nula");
    }

    @Test
    @DisplayName("Test buscar Auto por ID")
    void testBuscarPorId() {
        imprimirEncabezado("Test buscar Auto por ID");
        Auto auto = new Auto(null, "Ford", "Focus", 2019);
        autoDAO.crear(auto);

        Integer id = autoDAO.listar().get(0).getId();
        Auto encontrado = autoDAO.buscarPorId(id);

        assertNotNull(encontrado, "El auto debe existir");
        assertEquals("Ford", encontrado.getMarca(), "Marca debe coincidir");
        System.out.println("Auto encontrado por ID " + id + ": " + encontrado);
    }

    @Test
    @DisplayName("Test actualizar Auto")
    void testActualizar() {
        imprimirEncabezado("Test actualizar Auto");
        Auto auto = new Auto(null, "Honda", "Civic", 2018);
        autoDAO.crear(auto);

        Auto a = autoDAO.listar().get(0);
        a.setModelo("Accord");
        a.setFabricacion(2020);

        autoDAO.actualizar(a);
        Auto actualizado = autoDAO.buscarPorId(a.getId());

        assertEquals("Accord", actualizado.getModelo(), "Modelo debe haberse actualizado");
        assertEquals(2020, actualizado.getFabricacion(), "Año de fabricación actualizado");
        System.out.println("Auto actualizado: " + actualizado);
    }

    @Test
    @DisplayName("Test eliminar Auto")
    void testEliminar() {
        imprimirEncabezado("Test eliminar Auto");
        Auto auto = new Auto(null, "Chevrolet", "Spark", 2017);
        Auto auto1 = new Auto(null, "Chevrolet", "Corsa", 2016);
        autoDAO.crear(auto);
        autoDAO.crear(auto1);

        Auto a = autoDAO.listar().get(0);
        autoDAO.eliminar(a.getId());

        Auto eliminado = autoDAO.buscarPorId(a.getId());
        assertNull(eliminado, "El auto debe haber sido eliminado");
        System.out.println("Auto con ID " + a.getId() + " eliminado correctamente.");
    }

    @Test
    void testListarPorMarca() throws Exception {
        imprimirEncabezado("Test listar por Marca");
        autoDAO.crear(new Auto("Toyota", "Corolla", 2020));
        autoDAO.crear(new Auto("Toyota", "Yaris", 2021));
        autoDAO.crear(new Auto("Ford", "Fiesta", 2019));

        List<Auto> resultado = autoDAO.listarPorMarca("Toyota");
        System.out.println("Resultado del listar por marca: ");
        resultado.forEach(System.out::println);
        assertEquals(2, resultado.size());
    }

    @Test
    void testListarPorAnioFabricacion() throws Exception {
        imprimirEncabezado("Test listar por Año Fabricación");
        autoDAO.crear(new Auto("Kia", "Rio", 2020));
        autoDAO.crear(new Auto("Nissan", "Versa", 2021));

        List<Auto> resultado = autoDAO.listarPorAnioFabricacion(2020);
        
        System.out.println("Resultado por año de fabrocación: ");
        resultado.forEach(System.out::println);
        assertEquals(1, resultado.size());
        assertEquals("Kia", resultado.get(0).getMarca());
    }

    @Test
    @DisplayName("Test buscar auto por ID inexistente")
    void testBuscarPorIdInexistente() {
        imprimirEncabezado("Test buscar por ID inexistente");
        Auto resultado = autoDAO.buscarPorId(999);
        assertNull(resultado, "No debe encontrarse ningún auto con ID 999");
        System.out.println("Buscar por ID inexistente retornó: " + resultado);
    }

    @Test
    @DisplayName("Test eliminar auto inexistente")
    void testEliminarAutoInexistente() {
        imprimirEncabezado("Test eliminar auto inexistente");
        assertDoesNotThrow(() -> autoDAO.eliminar(999));
        System.out.println("Intento de eliminar auto inexistente no arrojó error.");
    }
}