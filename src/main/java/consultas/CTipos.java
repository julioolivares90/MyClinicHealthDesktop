package consultas;

import conexion.Conexion;
import models.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CTipos {

    public static int CrearTipo(Tipo tipo){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int result=0;
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConnection();
        String query ="";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,tipo.getTipoProducto());
            preparedStatement.setString(2,tipo.getDescripcion());

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
