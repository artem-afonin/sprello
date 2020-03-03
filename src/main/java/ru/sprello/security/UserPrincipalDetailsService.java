package ru.sprello.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sprello.config.WebSecurityConfig;
import ru.sprello.model.User;
import ru.sprello.repo.UserRepository;

import java.util.Optional;

/**
 * Вспомогательный service-класс для Spring Security
 *
 * @see WebSecurityConfig
 */
@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Будем искать по id (!!!)
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found by id");
        }

        User user = optionalUser.get();

        return new UserPrincipal(user);
    }
}
