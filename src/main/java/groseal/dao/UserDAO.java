package groseal.dao;

import groseal.models.Role;
import groseal.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDAO {

    void createUser(User user);

    User readUser(Long id);

    void updateUser(Long id, String name, String password, Set<Role> roles);

    void deleteUser(Long id);

    List<User> getAllUser();

    User getUserByName(String name);
}
