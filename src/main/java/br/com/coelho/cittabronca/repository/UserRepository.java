package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
