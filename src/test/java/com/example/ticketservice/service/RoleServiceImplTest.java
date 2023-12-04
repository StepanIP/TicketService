package com.example.ticketservice.service;

import com.example.ticketservice.exception.NullEntityReferenceException;
import com.example.ticketservice.model.Role;
import com.example.ticketservice.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateRole_ValidRole_Success() {
        Role role = new Role();
        role.setRoleName("ADMIN");

        Role createdRole = roleService.create(role);

        assertNotNull(createdRole);
        assertNotNull(createdRole.getId());
        assertEquals(role.getRoleName(), createdRole.getRoleName());
    }

    @Test
    public void testCreateRole_NullRole_ThrowsNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> roleService.create(null));
    }

    @Test
    public void testReadRoleById_ExistingRoleId_Success() {
        Role role = new Role();
        role.setRoleName("ADMIN");
        Role savedRole = roleRepository.save(role);

        Role foundRole = roleService.readById(savedRole.getId());

        assertNotNull(foundRole);
        assertEquals(savedRole.getId(), foundRole.getId());
        assertEquals(savedRole.getRoleName(), foundRole.getRoleName());
    }

    @Test
    public void testReadRoleById_NonExistingRoleId_ThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> roleService.readById(999L));
    }

    @Test
    public void testUpdateRole_ValidRole_Success() {
        Role role = new Role();
        role.setRoleName("ADMIN");
        Role savedRole = roleRepository.save(role);

        Role updatedRole = new Role();
        updatedRole.setId(savedRole.getId());
        updatedRole.setRoleName("SUPERADMIN");

        Role returnedRole = roleService.update(updatedRole);

        assertNotNull(returnedRole);
        assertEquals(savedRole.getId(), returnedRole.getId());
        assertEquals(updatedRole.getRoleName(), returnedRole.getRoleName());
    }

    @Test
    public void testUpdateRole_NullRole_ThrowsNullEntityReferenceException() {
        assertThrows(NullEntityReferenceException.class, () -> roleService.update(null));
    }

    @Test
    public void testUpdateRole_NonExistingRoleId_ThrowsEntityNotFoundException() {
        Role role = new Role();
        role.setId(999L);
        role.setRoleName("ADMIN");

        assertThrows(EntityNotFoundException.class, () -> roleService.update(role));
    }

    @Test
    public void testDeleteRole_ExistingRoleId_Success() {
        Role role = new Role();
        role.setRoleName("ADMIN");
        Role savedRole = roleRepository.save(role);

        roleService.delete(savedRole.getId());

        assertFalse(roleRepository.existsById(savedRole.getId()));
    }

    @Test
    public void testDeleteRole_NonExistingRoleId_ThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> roleService.delete(999L));
    }

    @Test
    public void testGetAllRoles_TwoRolesInDatabase_ReturnsTwoElementsList() {
        List<Role> roles = roleService.getAll();

        assertNotNull(roles);
        assertEquals(2, roles.size());
    }

    @Test
    public void testGetAllRoles_RolesInDatabase_ReturnsListOfRoles() {
        Role role1 = new Role();
        role1.setRoleName("ADMIN");
        Role savedRole1 = roleRepository.save(role1);

        Role role2 = new Role();
        role2.setRoleName("USER");
        Role savedRole2 = roleRepository.save(role2);

        List<Role> roles = roleService.getAll();

        assertNotNull(roles);
        assertEquals(4, roles.size());
        assertTrue(roles.contains(savedRole1));
        assertTrue(roles.contains(savedRole2));
    }
}
