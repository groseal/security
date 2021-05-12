package groseal.service;

import groseal.models.Role;
import groseal.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    void createUser(User user);

    User readUser(Long id);

    void updateUser(Long id, String name, String password, Set<Role> roles);

    void deleteUser(Long id);

    List<User> getAllUser();

    User getUserByName(String name);

}
