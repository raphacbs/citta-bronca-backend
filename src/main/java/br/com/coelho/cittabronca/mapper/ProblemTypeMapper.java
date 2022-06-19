package br.com.coelho.cittabronca.mapper;

import br.com.coelho.cittabronca.dto.ProblemTypeDTO;
import br.com.coelho.cittabronca.entity.ProblemType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProblemTypeMapper {
    ProblemTypeMapper INSTANCE = Mappers.getMapper(ProblemTypeMapper.class);

    ProblemType toModel(ProblemTypeDTO problemTypeDTO);

    List<ProblemType> toModel(List<ProblemTypeDTO> problemTypeDTO);

    ProblemTypeDTO toDTO(ProblemType problemType);

    List<ProblemTypeDTO> toDTO(List<ProblemType> problemTypes);

}
