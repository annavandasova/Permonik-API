package cz.incad.nkp.inprove.permonikapi.originentities.user;

import cz.incad.nkp.inprove.permonikapi.originentities.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/update")
    public Boolean updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

}
