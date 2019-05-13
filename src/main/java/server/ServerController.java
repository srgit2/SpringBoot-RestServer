package server;

import data.DataRepository;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public String addUser(){
        User user1 = new User(0, "Charlie Chaplin", "chaplin@movie.com");
        User user2 = new User(0, "Christopher Nolan", "c.nolan@fox.com");
        List<User> userArrayList = new ArrayList<User>();
        userArrayList.add(user1);
        userArrayList.add(user2);
        return Boolean.toString(dataRepository.addUsers(userArrayList));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id){
        User user = dataRepository.getUser(id);
        return Boolean.toString(dataRepository.deleteUser(user));
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable int id){
        User user = dataRepository.getUser(id);
        return user;
    }

    @RequestMapping (value = "/user", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user){
        System.out.println("Request came in updateUser");
        return Boolean.toString(dataRepository.updateUser(user));
    }

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers(){
        return dataRepository.getAllUsers();
    }


}
