// UTF-8
package com.AutosQA.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AutoTest {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    private void imprimirEncabezado(String nombreTest) {
        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("");
        System.out.println(ANSI_BLUE + nombreTest + ANSI_RESET);
    }

    @Test
    @DisplayName("Test constructor Auto con diferentes parámetros")
    void testConstructorAutoDiferentes() {
        imprimirEncabezado("Test constructor Auto diferentes");
        
        // Test constructor con ID null
        Auto auto1 = new Auto(null, "Mazda", "CX5", 2022);
        assertNull(auto1.getId());
        assertEquals("Mazda", auto1.getMarca());
        assertEquals("CX5", auto1.getModelo());
        assertEquals(2022, auto1.getFabricacion());
        
        // Test constructor con ID específico
        Auto auto2 = new Auto(10, "Subaru", "Forester", 2023);
        assertEquals(10, auto2.getId());
        assertEquals("Subaru", auto2.getMarca());
        assertEquals("Forester", auto2.getModelo());
        assertEquals(2023, auto2.getFabricacion());
        
        System.out.println("Constructores funcionando: " + auto1 + " | " + auto2);
    }

    @Test
    @DisplayName("Test crear Auto con atributos válidos e inválidos")
    void testCrearAutoValidaciones() {
        imprimirEncabezado("Test crear Auto con validaciones");

        // ✅ Auto válido
        Auto autoValido = new Auto(null, "Toyota", "Corolla", 2021);
        assertNotNull(autoValido);
        assertEquals("Toyota", autoValido.getMarca());
        assertEquals("Corolla", autoValido.getModelo());
        assertEquals(2021, autoValido.getFabricacion());

        // Marca vacía
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "", "Corolla", 2021);
        });

        // Modelo nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "Toyota", null, 2021);
        });

        // Año nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "Toyota", "Corolla", null);
        });
    }

    @Test
    @DisplayName("Test validación - marca vacía lanza excepción")
    void testValidacionMarcaVacia() {
        imprimirEncabezado("Test validación marca vacía");
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "", "ModeloTest", 2022);
        });
    }

    @Test
    @DisplayName("Test constructor vacío")
    void testConstructorVacio() {
        imprimirEncabezado("Test constructor vacío");
        Auto auto = new Auto();
        assertNotNull(auto);
        System.out.println("Constructor vacío funciona: " + auto);
    }

    @Test
    @DisplayName("Test setters y getters")
    void testSettersYGetters() {
        imprimirEncabezado("Test setters y getters");
        Auto auto = new Auto();
        
        auto.setId(1);
        auto.setMarca("Honda");
        auto.setModelo("Civic");
        auto.setFabricacion(2021);
        
        assertEquals(1, auto.getId());
        assertEquals("Honda", auto.getMarca());
        assertEquals("Civic", auto.getModelo());
        assertEquals(2021, auto.getFabricacion());
        
        System.out.println("Setters/Getters funcionando: " + auto);
    }

    @Test
    @DisplayName("Test toString contiene información relevante")
    void testToString() {
        imprimirEncabezado("Test toString");
        Auto auto = new Auto(1, "Toyota", "Corolla", 2020);
        
        String toString = auto.toString();
        assertNotNull(toString);
        assertTrue(toString.contains("Toyota"));
        assertTrue(toString.contains("Corolla"));
        assertTrue(toString.contains("2020"));
        
        System.out.println("toString funciona: " + toString);
    }

    // @Test
    // @DisplayName("Test equals y hashCode")
    // void testEqualsYHashCode() {
    //     imprimirEncabezado("Test equals y hashCode");
    //     Auto auto1 = new Auto(1, "Toyota", "Corolla", 2020);
    //     Auto auto2 = new Auto(1, "Toyota", "Corolla", 2020);
    //     Auto auto3 = new Auto(2, "Honda", "Civic", 2021);
        
    //     // Si implementaste equals
    //     assertEquals(auto1, auto2);
    //     assertNotEquals(auto1, auto3);
        
    //     // Si implementaste hashCode
    //     assertEquals(auto1.hashCode(), auto2.hashCode());
        
    //     System.out.println("Equals/HashCode funcionando correctamente");
    // }

    @Test
    @DisplayName("Test validaciones de campos nulos")
    void testValidacionesCamposNulos() {
        imprimirEncabezado("Test validaciones campos nulos");
        
        // Probar diferentes combinaciones de nulos
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, null, "Modelo", 2020);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Auto(null, "Marca", "", 2020);
        });
    }

    @Test
    @DisplayName("Test años válidos e inválidos")
    void testAniosValidosEInvalidos() {
        imprimirEncabezado("Test años válidos e inválidos");
        
        // Año válido
        Auto autoValido = new Auto(null, "Toyota", "Corolla", 2020);
        assertEquals(2020, autoValido.getFabricacion());
        
        // Si tienes validaciones de año, probar casos extremos
        assertDoesNotThrow(() -> {
            new Auto(null, "Ford", "Model T", 1908);
        });
    }

    @Test
    @DisplayName("Métodos equals y hashCode funcionan correctamente")
    void testEqualsYHashCode() {
        imprimirEncabezado("Equals y hashCode");

        Auto auto1 = new Auto("Toyota", "Yaris", 2021);
        auto1.setId(10);

        Auto auto2 = new Auto("Toyota", "Yaris", 2021);
        auto2.setId(10);

        Auto auto3 = new Auto("Honda", "Civic", 2021);
        auto3.setId(11);

        assertEquals(auto1, auto2);
        assertEquals(auto1.hashCode(), auto2.hashCode());
        assertNotEquals(auto1, auto3);
    }
}
