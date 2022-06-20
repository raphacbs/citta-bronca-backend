package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.dto.UserDTO;
import br.com.coelho.cittabronca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getByEmail(String email) {
        return UserDTO.builder().email("raphaelcoel@gmail.com").password("123456").build();
    }
}
