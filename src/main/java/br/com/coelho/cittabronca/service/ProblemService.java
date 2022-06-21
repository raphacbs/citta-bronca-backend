package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.enums.ProblemStatusEnum;
import br.com.coelho.cittabronca.mapper.ProblemMapper;
import br.com.coelho.cittabronca.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProblemService {
    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper = ProblemMapper.INSTANCE;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem create(Problem problemToSave) {
        problemToSave.setStatus(ProblemStatusEnum.OPEN);
        problemToSave.setCreateAt(LocalDateTime.now());
        Problem problemSaved = this.problemRepository.save(problemToSave);
        return problemSaved;
    }

    public Optional<Problem> findById(UUID id) {
        return this.problemRepository.findById(id);
    }
}
