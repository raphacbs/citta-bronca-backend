package br.com.coelho.cittabronca.repository;

import br.com.coelho.cittabronca.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
