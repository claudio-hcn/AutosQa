import com.AutosQA.dao.AutoDAO;
import com.AutosQA.db.CrearTabla;
import com.AutosQA.model.Auto;
import com.AutosQA.db.Conexion; 
import java.sql.Connection;
import java.sql.SQLException;
    

public class Main {
    public static void main(String[] args) {

        CrearTabla.crearTabla(); // Crear tabla

        AutoDAO dao = new AutoDAO();

        dao.crear(new Auto("Toyota", "Yaris", 2020L));
        dao.crear(new Auto("Ford", "Fiesta", 2019L));

        dao.listar().forEach(a -> System.out.println(
                a.getId() + " - " + a.getMarca() + " - " + a.getModelo() + " - " + a.getFabricacion()
        ));
    }
}