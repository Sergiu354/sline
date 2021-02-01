package com.sline.sline.configuration;

import com.sline.sline.entity.system.User;
import com.sline.sline.service.system.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class InitialDataLoader {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    //@EventListener(ContextClosedEvent.class)
    public void start() {
        User user = userService.findByEmail("admin@admin.md");
        if (user==null) {
            user = new User();
            user.setUserName("SuperAdmin");
            user.setFirstName("Admin");
            user.setLastName("Admin");
            user.setEmail("admin@admin.md");
            user.setSecretText("adminadmin");
            user.setPassword(passwordEncoder.encode("superadmin"));
            user.setEnabled(true);
            userService.save(user);
        }
    }
}
