package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.dto.ProblemDTO;
import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.mapper.ProblemMapper;
import br.com.coelho.cittabronca.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ProblemDTO create(ProblemDTO problemDTO) {
        Problem problemToSave = problemMapper.toModel(problemDTO);
        Problem problemSaved = this.problemRepository.save(problemToSave);
        return problemMapper.toDTO(problemSaved);
    }

    public ProblemDTO findById(UUID id) {
        Optional<Problem> optionalProblem = this.problemRepository.findById(id);
        return this.problemMapper.toDTO(optionalProblem.orElse(null));
    }

    public ProblemDTO findByNeighborhood(Optional<String> neighborhood) {
        return null;
    }
}
