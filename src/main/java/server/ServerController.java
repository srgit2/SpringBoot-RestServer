package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
public class ServerController {

    @Autowired
    DataRepository dataRepository;

    @RequestMapping("/")
    public String ping(){
        return "Hello from Rest Server. Time now is :" + DateFormat.getTimeInstance().format(Calendar.getInstance().getTime()) + "\n";
    }

    @RequestMapping("/add")
    public String addUser(){
        User user1 = new User("Charlie Chaplin", "chaplin@movie.com");
        User user2 = new User("Christopher Nolan", "c.nolan@fox.com");
        List<User> userArrayList = new ArrayList<User>();
        userArrayList.add(user1);
        userArrayList.add(user2);
        return Boolean.toString(dataRepository.addUser(userArrayList));
    }
}
