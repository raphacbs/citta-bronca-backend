package br.com.coelho.cittabronca.controller;

import br.com.coelho.cittabronca.dto.ProblemDTO;
import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.exception.ResouceNotFoundException;
import br.com.coelho.cittabronca.mapper.ProblemMapper;
import br.com.coelho.cittabronca.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Problem> create(@RequestBody @Valid ProblemDTO problemDTO) {
        ProblemMapper problemMapper = ProblemMapper.INSTANCE;
        Problem problem = problemMapper.toModel(problemDTO);
        return ResponseEntity.ok(problemService.create(problem));
    }

    @GetMapping("/{id:^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$}")
    public ResponseEntity<Problem> findById(@PathVariable("id") UUID id) {
        Problem problem = this.problemService.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Problem not found" + id));
        return ResponseEntity.ok().body(problem);
    }
}
