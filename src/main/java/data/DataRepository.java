package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.JDBCType;
import java.util.*;

@Repository
public class DataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public boolean initializeData(){
        createTable();
        jdbcTemplate.execute("INSERT INTO USERS( Name, Email ) VALUES ('Sunnychayan', 'sunnychayan@pala.com') ");
        jdbcTemplate.execute("INSERT INTO USERS( Name, Email ) VALUES ('Vijilesh', 'vijilesh@prakash.org') ");
        jdbcTemplate.execute("INSERT INTO USERS( Name, Email ) VALUES ('Luttappi', 'Luttappi@mayavi.com') ");
        return true;
    }

    @Transactional
    public boolean createTable(){
        jdbcTemplate.execute("CREATE TABLE USERS( ID SERIAL NOT NULL, Name VARCHAR(100) NOT NULL, Email VARCHAR(100) NOT NULL, PRIMARY KEY(ID))");
        return true;
    }

    public boolean addUsers(List<User> newUserList){
        System.out.println("userList: " + newUserList.toString());
        List<Object[]> paramList = new ArrayList<Object[]>();
        newUserList.forEach( user -> paramList.add(new String[]{user.getName(), user.getEmail()}));
        paramList.stream().forEach(param -> System.out.println("User: [" + param[0] + ", " + param[1] + "]"));
        jdbcTemplate.batchUpdate("INSERT INTO USERS(Name, Email) VALUES(?, ?)", paramList);
        return true;
    }

    public boolean addUser(User newUser){
        jdbcTemplate.update("INSERT INTO USERS(Name, Email) VALUES(?, ?)", new String[]{newUser.getName(), newUser.getEmail()}, new int[]{JDBCType.VARCHAR.getVendorTypeNumber(), JDBCType.VARCHAR.getVendorTypeNumber()});
        return true;
    }

    public boolean deleteUser(User deleteUser){
        System.out.println("Deleting User: " + deleteUser.toString());
        jdbcTemplate.execute("DELETE USER WHERE ID = " + deleteUser.getID());
        return true;
    }

    public boolean updateUser(User user){
        System.out.println("Updating user: " + user.toString());
        jdbcTemplate.update("UPDATE USERS Set Name = ?, Email = ? WHERE ID = ?", user);
        return true;
    }

    public User getUser(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE ID = ?", new Object[]{id}, new int[]{JDBCType.INTEGER.getVendorTypeNumber()}, User.class);
    }

    public List<User> getAllUsers(){
        System.out.println("Getting all users");
        return jdbcTemplate.query("SELECT * FROM USERS", (rs, rowNum) -> new User(rs.getInt("ID"), rs.getString("NAme"), rs.getString("Email")));
    }
}
