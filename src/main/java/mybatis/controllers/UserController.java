import java.util.ArrayList;
import mybatis.model.User;
import mybatis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // Get users with a specific age
    @RequestMapping("/age")
    public ArrayList<User> getUsers(@RequestParam(value="age") int age) {
        return userService.getUserByAge(age);
    }

    // Get all active users
    @RequestMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getAllUsers();
    }

    // Create User
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public User addNew(@RequestBody User user) {
        return userService.addNew(user);
    }

    // Read User
    @RequestMapping(method= RequestMethod.GET, value = "/{id}")
    public User getById(@PathVariable(value="id")Integer id) {
        return userService.getById(id);
    }

    // Update User
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public User updateById(@RequestBody User user) {
        return userService.updateById(user);
    }

    // Delete User (set isActive = false)
    @RequestMapping(method= RequestMethod.DELETE, value="/{id}")
    public User deleteById(@PathVariable(value="id") Integer id){
        return userService.deleteById(id);
    }
}