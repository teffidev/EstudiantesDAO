package main.dao.impl;

import main.dao.IDao;
import main.entidades.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOH2 implements IDao<Estudiante> {

    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/db-estudiantes";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

            //1. levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2. Crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO estudiantes VALUES (?,?,?)");
            preparedStatement.setLong(1, estudiante.getId());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setString(3, estudiante.getApellido());

            //3. Ejecutamos la sentencia
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiante;
    }

    @Override
    public void eliminar(Long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //1. levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2. Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM estudiantes WHERE ID=?");
            preparedStatement.setLong(1, id);


            //3. Ejecutamos la sentencia
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public Estudiante buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Estudiante estudiante = null;

        //1. levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2. Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes WHERE ID=?");
            preparedStatement.setLong(1, id);

            //3. Ejecutamos la sentencia
            ResultSet resultado = preparedStatement.executeQuery();

            //5. Evaluar los resultados (recorrer los registros)
            while (resultado.next()) {
                Long idEstudiante = resultado.getLong("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
            }
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiante;
    }


    @Override
    public List<Estudiante> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Estudiante> estudiantes = new ArrayList<>();

        //1. levantar el driver y conectarnos
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2. Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes");

            //3. Ejecutamos la sentencia
            ResultSet resultado = preparedStatement.executeQuery();

            //5. Evaluar los resultados (recorrer los registros)
            while (resultado.next()) {
                Long idEstudiante = resultado.getLong("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");

                Estudiante estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);

                estudiantes.add(estudiante);

            }
            preparedStatement.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estudiantes;
    }
}
