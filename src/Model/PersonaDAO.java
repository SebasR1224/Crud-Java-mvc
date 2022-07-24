package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class PersonaDAO {

    Connect connect = new Connect();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public List listar() {
        List<Persona> data = new ArrayList<>();
        String query = "select * from personas";
        try {
            connection = connect.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Persona p = new Persona();
                p.setId(resultSet.getInt(1));
                p.setName(resultSet.getString(2));
                p.setEmail(resultSet.getString(3));
                p.setPhone(resultSet.getString(4));
                data.add(p);
            }
        } catch (Exception e) {
        }
        return data;
    }

    public void store(Persona p) {
        String query = "insert into Personas (name, email, phone) values(?,?,?)";
        try {
            connection = connect.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getEmail());
            preparedStatement.setString(3, p.getPhone());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void update(Persona p) {
        String query = "update Personas set name=?, email=?, phone=? where id=?";
        try {
            connection = connect.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getEmail());
            preparedStatement.setString(3, p.getPhone());
            preparedStatement.setInt(4, p.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void delete(int id){
        String query = "delete from Personas where id="+id;
        try {
            connection = connect.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
        }
    }
}
