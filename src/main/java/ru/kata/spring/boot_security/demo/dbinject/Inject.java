package ru.kata.spring.boot_security.demo.dbinject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class Inject {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public Inject(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        roleService.add(role1);
        roleService.add(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(role1);
        roleAdmin.add(role2);

        User admin = new User("101", "admin@mail.ru", "admin", "admin", 33, roleAdmin);
        User user = new User("101","user@mail.ru","user","user",22,roleUser );
        User user2 = new User("101","user2@mail.ru","user","user",22,roleUser );
        User user3 = new User("101","user3@mail.ru","user","user",22,roleUser );

        userService.add(admin);
        userService.add(user);
        userService.add(user2);
        userService.add(user3);

    }
}
