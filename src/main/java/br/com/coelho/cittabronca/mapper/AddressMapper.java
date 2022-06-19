package br.com.coelho.cittabronca.mapper;

import br.com.coelho.cittabronca.dto.AddressDTO;
import br.com.coelho.cittabronca.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(AddressDTO addressDTO);

    List<Address> toModel(List<AddressDTO> addressDTOList);

    AddressDTO toDTO(Address address);

    List<AddressDTO> toDTO(List<Address> addresses);

}
