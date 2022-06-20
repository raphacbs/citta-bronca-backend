package br.com.coelho.cittabronca.entity;

import br.com.coelho.cittabronca.enums.ProblemStatusEnum;
import br.com.coelho.cittabronca.enums.ProblemTypeEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Problem implements Persistable<UUID> {
    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Enumerated(EnumType.ORDINAL)
    private ProblemTypeEnum type;
    @Column(nullable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @Enumerated(EnumType.ORDINAL)
    private ProblemStatusEnum status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
    private Address address;

    @Override
    public boolean isNew() {
        return id == null;
    }
}