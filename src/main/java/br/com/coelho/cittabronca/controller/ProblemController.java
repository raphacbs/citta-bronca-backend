package br.com.coelho.cittabronca.controller;

import br.com.coelho.cittabronca.dto.ProblemDTO;
import br.com.coelho.cittabronca.dto.ProblemStatusDTO;
import br.com.coelho.cittabronca.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    public ProblemDTO create(@RequestBody @Valid ProblemDTO problemDTO) {
        problemDTO.setCreateAt(LocalDateTime.now());
        problemDTO.setStatus(ProblemStatusDTO.OPEN);
        return problemService.create(problemDTO);
    }

    @GetMapping
    public ProblemDTO findById(@RequestParam("id") UUID id) {
        return this.problemService.findById(id);
    }
}
