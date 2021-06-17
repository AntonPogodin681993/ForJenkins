import com.pogodin.springMVC.dao.PersonDAO;
import com.pogodin.springMVC.models.Person;
import org.junit.*;
import org.junit.runner.RunWith;

import java.sql.*;

import static com.pogodin.springMVC.dao.PersonDAO.closeConnection;
import static com.pogodin.springMVC.dao.PersonDAO.openConnection;
import static org.junit.Assert.*;


public class PersonDaoTest {


    @Test
    public  void testConnection(){
        System.out.println("openConnection");
        Connection result = openConnection();
        assertEquals(result != null, true);
    }



    @Test
    public void testSave() {
        System.out.println("Insert test");

        try {
            //openConnection();
            Statement st = openConnection().createStatement();
            st.executeUpdate("INSERT INTO TestPerson (id,name, age, email) VALUES(888,'Test1',1,'test1@mail.ru')");
            ResultSet rs = st.executeQuery("SELECT * FROM TestPerson WHERE id=888");
            rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            assertEquals(888, id);
            assertEquals("Test1", name);
            assertEquals(1, age);
            assertEquals("test1@mail.ru", email);
            closeConnection();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    @Test
    public void testUpdate(){
        System.out.println("Update test");
        try {
            Statement st = openConnection().createStatement();
            st.executeUpdate("INSERT INTO TestPerson (id,name, age, email) VALUES(111,'Test1',1,'test1@mail.ru')");
            st.executeUpdate("UPDATE TestPerson SET name='Test2', age=22, email='test2@mail.ru' WHERE id=111");
            ResultSet rs = st.executeQuery("SELECT * FROM TestPerson WHERE id=111");
            rs.next();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String email = rs.getString("email");
            assertEquals(111, id);
            assertEquals("Test2", name);
            assertEquals(22, age);
            assertEquals("test2@mail.ru", email);
            closeConnection();

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    @Test
    public void testDelete() {
        System.out.println("Delete test");
        try{
            Statement st = openConnection().createStatement();
            st.executeUpdate("DELETE FROM TestPerson ");
            ResultSet rs = st.executeQuery("SELECT * FROM TestPerson ");
            assertEquals(rs.next(), false);
            closeConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }


    }


}
