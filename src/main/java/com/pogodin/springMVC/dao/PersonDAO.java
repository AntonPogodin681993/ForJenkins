package com.pogodin.springMVC.dao;

import com.pogodin.springMVC.MyEcxeption.MyEmailValidException;
import com.pogodin.springMVC.models.Person;
import com.pogodin.springMVC.util.RegExValidator;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    RegExValidator regExValidator;

    private static final String URL = "jdbc:postgresql://192.168.0.102:5432/first_db";
    private static final String USERNAME = "postgres";
    private  static final String PASSWORD = "123456";
    private static Connection connection;

    public static Connection openConnection()  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }


    public   List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            openConnection();
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person  = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName((resultSet.getString("name")));
                person.setAge((resultSet.getInt("age")));
                person.setEmail((resultSet.getString("email")));
                people.add(person);
                closeConnection();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }



    public Person show(int id){
        Person person = null;
        try {
            openConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;

    }

    public void save(Person person){
        try {
            openConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person(name, age, email) VALUES(?,?,?)");
            //preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void update (int id, Person updatedPerson){
        try {
            openConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id){
        PreparedStatement preparedStatement = null;
        try {
            openConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
