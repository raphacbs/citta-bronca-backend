package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.dto.AddressDTO;
import br.com.coelho.cittabronca.entity.Address;
import br.com.coelho.cittabronca.mapper.AddressMapper;
import br.com.coelho.cittabronca.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDTO create(AddressDTO addressDTO) {
        Address addressToSave = addressMapper.toModel(addressDTO);
        Address addressSaved = this.addressRepository.save(addressToSave);
        return addressMapper.toDTO(addressSaved);
    }

}
