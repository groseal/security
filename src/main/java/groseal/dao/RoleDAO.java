package groseal.dao;

import groseal.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface RoleDAO {
public interface RoleDAO extends JpaRepository<Role, Long> {
}
