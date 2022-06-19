package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.Address;
import br.com.coelho.cittabronca.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProblemRepository extends JpaRepository<Problem, UUID> {
    List<Problem> findByAddress(Address address);
}
