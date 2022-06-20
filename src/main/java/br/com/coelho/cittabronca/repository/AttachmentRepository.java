package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.Attachment;
import br.com.coelho.cittabronca.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Attachment getByProblem(Problem problem);
}
