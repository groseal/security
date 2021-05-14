package groseal.service;

import groseal.models.Role;

public interface RoleService {

    Role getRoleByName(String role);

    void deleteRole(Long id);

}
