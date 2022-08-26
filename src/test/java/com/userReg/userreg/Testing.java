package com.userReg.userreg;

import com.userReg.userreg.user.User;
import com.userReg.userreg.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.swing.text.html.Option;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class Testing {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test()
    {
        User user = new User();
        user.setEmail("sow@gmail.com");
        user.setPassword("Doura@123");
        user.setFirstname("Dybala");
        user.setLastname("Diallo");

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void TestListing()
    {
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user: users)
        {
            System.out.println(user);
            
        }
    }

    @Test
    public void Testupdate()
    {
        Integer userid = 1;
        Optional<User> optionalUser = userRepository.findById(userid);
        User user = optionalUser.get();
        user.setPassword("sow@123");
        userRepository.save(user);
        User updatedUser = userRepository.findById(userid).get();

        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("sow@123");
    }

    @Test
    public void TestGet()
    {
        Integer userid = 1;
        Optional<User> optionalUser = userRepository.findById(userid);

        Assertions.assertThat(optionalUser.isPresent());
        System.out.println(optionalUser);
    }

    @Test
    public void TestDelete()
    {
        Integer userid = 2;
        userRepository.deleteById(userid);

        Optional<User> optionalUser = userRepository.findById(userid);

        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
