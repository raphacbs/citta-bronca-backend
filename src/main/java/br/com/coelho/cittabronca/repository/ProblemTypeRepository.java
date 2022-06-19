package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.ProblemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTypeRepository extends JpaRepository<ProblemType, Long> {
}
