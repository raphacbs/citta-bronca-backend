package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.Attachment;
import br.com.coelho.cittabronca.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Optional<Attachment> getByProblem(Problem problem);
}
