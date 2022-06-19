package br.com.coelho.cittabronca.mapper;

import br.com.coelho.cittabronca.dto.ProblemDTO;
import br.com.coelho.cittabronca.entity.Problem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    Problem toModel(ProblemDTO problemDTO);

    List<Problem> toModel(List<ProblemDTO> problemDTO);

    ProblemDTO toDTO(Problem problem);

    List<ProblemDTO> toDTO(List<Problem> problems);

}
