package groseal.dao;

import groseal.models.Role;
import groseal.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(Long id, String name, String password, Set<Role> roles) {
        User updateUser = readUser(id);
        updateUser.setName(name);
        updateUser.setPassword(password);
        updateUser.setUserRoles(roles);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(readUser(id));
    }

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).
                setParameter("name", name).getSingleResult();
    }


}

