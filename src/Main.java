import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {

    static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    static final String USER = "system";
    static final String PASSWORD = "Tapiero123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conexion =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("=================================");
            System.out.println(" REGISTRO DE PRODUCTOS");
            System.out.println(" ORACLE DATABASE 19c");
            System.out.println("=================================");

            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            System.out.print("Precio: ");
            double precio = sc.nextDouble();

            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();

            String estado;

            // CONDICIONALES ANIDADOS .

            if (cantidad > 0) {
                if (precio >= 100000) {
                    if (categoria.equalsIgnoreCase("TECNOLOGIA")) {
                        estado = "PRODUCTO PREMIUM";
                    } else {
                        estado = "PRODUCTO COSTOSO";
                    }
                } else {
                    estado = "PRODUCTO ECONOMICO";
                }
            } else {
                estado = "AGOTADO";
            }

            System.out.println("---------------------------------");
            System.out.println("ESTADO DEL PRODUCTO: " + estado);

            String sql =
                    "INSERT INTO PRODUCTOS_E " +
                    "(NOMBRE, CATEGORIA, PRECIO, CANTIDAD, ESTADO) " +
                    "(NOMBRE, CATEGORIA, PRECIO, CANTIDAD, ESTADO) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, categoria);
            ps.setDouble(3, precio);
            ps.setInt(4, cantidad);
            ps.setString(5, estado);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {

                System.out.println("---------------------------------");
                System.out.println("PRODUCTO REGISTRADO CORRECTAMENTE");

            } else {
                System.out.println("ERROR AL REGISTRAR!");
            }

            ps.close();
            conexion.close();

        } catch (Exception e) {

            System.out.println("---------------------------------");
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }
}   