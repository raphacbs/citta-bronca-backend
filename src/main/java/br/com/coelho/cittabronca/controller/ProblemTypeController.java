package br.com.coelho.cittabronca.controller;

import br.com.coelho.cittabronca.dto.ProblemTypeDTO;
import br.com.coelho.cittabronca.service.ProblemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/problem-types")
public class ProblemTypeController {

    private final ProblemTypeService problemTypeService;

    @Autowired
    public ProblemTypeController(ProblemTypeService problemTypeService) {
        this.problemTypeService = problemTypeService;
    }

    @PostMapping
    public ProblemTypeDTO create(@RequestBody @Valid ProblemTypeDTO problemTypeDTO) {
        return problemTypeService.create(problemTypeDTO);
    }

}
