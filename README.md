# 🚗 AutosQa

**AutosQa** es un proyecto en Java que implementa pruebas unitarias y de integración para un sistema de gestión de autos. Utiliza el patrón DAO, conexión a base de datos con JDBC, y pruebas con JUnit 5 y Mockito, aplicando buenas prácticas de desarrollo orientado a pruebas (TDD).

---

## 📁 Estructura del Proyecto

```
AutosQa/
├── src/
│   ├── main/
│   │   └── java/com/AutosQA/
│   │       ├── dao/           # Lógica de acceso a datos (AutoDAO)
│   │       ├── db/            # Conexión y creación de tablas (Conexion, CrearTabla)
│   │       └── model/         # Clase modelo: Auto
│   └── test/
│       └── java/com/AutosQA/
│           ├── dao/          # Tests unitarios para AutoDAO
│           ├── db/           # Test de conexión
│           └── model/        # Test de entidad Auto
├── pom.xml                   # Archivo Maven con dependencias
└── README.md
```

---

## 🧪 Tecnologías utilizadas

- **Lenguaje:** Java 17
- **Build Tool:** Maven
- **Framework de pruebas:** JUnit 5
- **Mocks:** Mockito
- **Base de Datos:** JDBC con SQLite o embebida
- **IDE recomendado:** IntelliJ IDEA / Eclipse

---

## 🚀 Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/LilianaCedeno/AutosQa.git
   cd AutosQa
   ```

2. Abre el proyecto en tu IDE como un proyecto Maven.

3. Asegúrate de tener configurado Java 17+ y Maven en tu entorno.

---

## ▶️ Ejecución del Proyecto

### Ejecutar la app principal

Puedes correr la clase `Main.java` que crea la tabla y realiza inserciones o consultas desde consola:

```bash
mvn compile exec:java -Dexec.mainClass="com.AutosQA.Main"
```

(O desde tu IDE: clic derecho sobre `Main.java` > Run)

---

### Ejecutar pruebas

Para correr todas las pruebas:

```bash
mvn test
```

Para correr una clase específica:

```bash
mvn -Dtest=AutoDaoTest test
```

---

## 🧠 Funcionalidades implementadas

- CRUD básico para la entidad `Auto`
- Conexión a base de datos y creación de tabla automática
- Pruebas unitarias del DAO usando JUnit y Mockito
- Validaciones de atributos del modelo

---

## 🤝 Contribuciones

¿Quieres contribuir? Sigue estos pasos:

1. Haz fork del proyecto
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -m 'Agrega nueva funcionalidad'`)
4. Haz push a tu rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Puedes modificarlo y reutilizarlo libremente con atribución.

---

## 👩‍💻 DevOps

**Liliana Cedeño** 
**Samuel Millelche**
**Claudio Carrasco**
[GitHub](https://github.com/LilianaCedeno)
[GitHub](https://github.com/claudio-hcn)
[GitHub](https://github.com/Millelche)

Desarrolladores con enfoque en QA y automatización.