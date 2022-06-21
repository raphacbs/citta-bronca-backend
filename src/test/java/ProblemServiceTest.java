import br.com.coelho.cittabronca.entity.Address;
import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.enums.ProblemStatusEnum;
import br.com.coelho.cittabronca.enums.ProblemTypeEnum;
import br.com.coelho.cittabronca.repository.ProblemRepository;
import br.com.coelho.cittabronca.service.ProblemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;


public class ProblemServiceTest {
    @InjectMocks
    ProblemService problemService;

    @Mock
    ProblemRepository problemRepository;

    private Address address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        address = Address.builder()
                .city("Recife")
                .country("Brazil")
                .countryCode("BR")
                .neighborhood("COHAB")
                .latitude(-8.1129044)
                .longitude(-34.9548118)
                .street("Avenida Senador Antonio Farias")
                .postalCode("51345380")
                .state("Pernambuco")
                .stateCode("PE")
                .text("OK")
                .build();
    }

    @Test
    public void createProblemWithSuccess() {
        Problem problemToSave = Problem.builder()
                .createAt(LocalDateTime.now())
                .type(ProblemTypeEnum.HOLE)
                .address(address)
                .build();
        when(problemRepository.save(problemToSave)).thenReturn(problemToSave);
        Problem problemSaved = problemService.create(problemToSave);
        Assertions.assertNotNull(problemSaved);
        Assertions.assertEquals(problemSaved.getStatus(), ProblemStatusEnum.OPEN);
        verify(problemRepository, times(1)).save(problemToSave);
    }

    @Test
    public void getByIdWithSuccess() throws Exception {
        UUID problemId = UUID.randomUUID();
        when(problemRepository.findById(problemId)).thenReturn(Optional.ofNullable(Problem.builder()
                .createAt(LocalDateTime.now())
                .type(ProblemTypeEnum.HOLE)
                .address(address)
                .id(problemId)
                .status(ProblemStatusEnum.OPEN)
                .build()));
        Assertions.assertTrue(problemService.findById(problemId).isPresent());
    }

    @Test
    public void getByIdNotFound() throws Exception {
        UUID problemId = UUID.randomUUID();
        when(problemRepository.findById(UUID.randomUUID())).thenReturn(Optional.ofNullable(Problem.builder()
                .createAt(LocalDateTime.now())
                .type(ProblemTypeEnum.HOLE)
                .address(address)
                .id(UUID.randomUUID())
                .status(ProblemStatusEnum.OPEN)
                .build()));
        Assertions.assertFalse(problemService.findById(problemId).isPresent());
    }
}
