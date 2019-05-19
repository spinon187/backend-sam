package com.lambdaschool.health;

import com.lambdaschool.health.model.Role;
import com.lambdaschool.health.model.User;
import com.lambdaschool.health.model.UserInfo;
import com.lambdaschool.health.model.UserRoles;
import com.lambdaschool.health.repository.RoleRepository;
import com.lambdaschool.health.repository.UserInfoRepository;
import com.lambdaschool.health.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    RoleRepository rolerepos;
    UserRepository userrepos;
    UserInfoRepository inforepo;

    public SeedData(RoleRepository rolerepos, UserRepository userrepos, UserInfoRepository inforepo)
    {
        this.rolerepos = rolerepos;
        this.userrepos = userrepos;
        this.inforepo = inforepo;
    }

    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = rolerepos.save(r1);
        r2 = rolerepos.save(r2);
        r3 = rolerepos.save(r3);

        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));

        ArrayList<UserRoles> data = new ArrayList<>();
        data.add(new UserRoles(new User(), r2));
        data.add(new UserRoles(new User(), r3));


        User u1 = new User("test", "testing", users);

        User u2 = new User("admin", "password", admins);

        User u3 = new User("sam", "gottem", data);

        UserInfo i3 = new UserInfo("sam","Sam", "Rodriguez", 140);

        inforepo.save(i3);
        userrepos.save(u1);
        userrepos.save(u2);
        userrepos.save(u3);
    }
}
