package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.dto.ProblemTypeDTO;
import br.com.coelho.cittabronca.entity.ProblemType;
import br.com.coelho.cittabronca.mapper.ProblemTypeMapper;
import br.com.coelho.cittabronca.repository.ProblemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemTypeService {
    private final ProblemTypeRepository problemTypeRepository;
    private final ProblemTypeMapper problemMapper = ProblemTypeMapper.INSTANCE;

    @Autowired
    public ProblemTypeService(ProblemTypeRepository problemTypeRepository) {
        this.problemTypeRepository = problemTypeRepository;
    }

    public ProblemTypeDTO create(ProblemTypeDTO problemDTO) {
        ProblemType problemToSave = problemMapper.toModel(problemDTO);
        ProblemType problemSaved = this.problemTypeRepository.save(problemToSave);
        return problemMapper.toDTO(problemSaved);
    }

}
