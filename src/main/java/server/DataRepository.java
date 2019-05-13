package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DataRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean addUser(List<User> newUserList){
        System.out.println("userList: " + newUserList.toString());
        List<String[]> paramList = new ArrayList<String[]>();
        newUserList.forEach( user -> paramList.add(new String[]{user.getName(), user.getEmail()}));
        paramList.stream().forEach(param -> System.out.println("User: [" + param[0] + ", " + param[1] + "]"));
//        jdbcTemplate.batchUpdate("INSERT INTO USERS(Name, Email) VALUES(?, ?)"))
        return true;
    }
}
