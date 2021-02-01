package com.sline.sline.service.facade.system.user;

import com.sline.sline.entity.system.User;
import com.sline.sline.model.CurrentProfile;
import com.sline.sline.service.system.user.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        if (user!=null) {
            if (user.isEnabled()) {
                CurrentProfile currentProfile = new CurrentProfile(user.getUuid(), user.getPassword(), AuthoritiesUser.getAuthorities(user.getRoles()));
                currentProfile.setFullName(user.getFirstName() + " " + user.getLastName());
                currentProfile.setLocale(user.getLanguage());
                currentProfile.setImageAvatar(user.getAvatarImage());
                return currentProfile;
            } else {
                LOGGER.info("Profile is blocked user: " + s);
            }
        }
        LOGGER.info("Profile not found by " + s);
        throw new UsernameNotFoundException("Profile not found by " + s);
    }
}
