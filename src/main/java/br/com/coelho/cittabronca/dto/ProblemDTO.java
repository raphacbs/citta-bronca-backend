package br.com.coelho.cittabronca.dto;

import br.com.coelho.cittabronca.enums.ProblemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDTO {
    private UUID id;
    private ProblemTypeEnum type;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private ProblemStatusDTO status;
    private AddressDTO address;
}
