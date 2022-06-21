package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.dto.AttachmentDTO;
import br.com.coelho.cittabronca.dto.ProblemDTO;
import br.com.coelho.cittabronca.entity.Attachment;
import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.mapper.AttachmentMapper;
import br.com.coelho.cittabronca.mapper.ProblemMapper;
import br.com.coelho.cittabronca.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper = AttachmentMapper.INSTANCE;
    private final ProblemMapper problemMapper = ProblemMapper.INSTANCE;
    private final ProblemService problemService;
    private final StorageService storageService;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository,
                             ProblemService problemService,
                             StorageService storageService) {
        this.attachmentRepository = attachmentRepository;
        this.problemService = problemService;
        this.storageService = storageService;
    }

    public AttachmentDTO save(MultipartFile file, Problem probrem) throws Exception {
        ProblemDTO problemDTO = problemMapper.toDTO(probrem);
        AttachmentDTO attachmentDTO = AttachmentDTO.builder()
                .size(file.getSize())
                .createAt(LocalDateTime.now())
                .problem(problemDTO)
                .build();
        Attachment attachmentToSave = attachmentMapper.toModel(attachmentDTO);
        Attachment attachmentSaved = this.attachmentRepository.save(attachmentToSave);
        attachmentDTO = attachmentMapper.toDTO(attachmentSaved);
        attachmentDTO.setFile(file);
        storageService.store(attachmentDTO);
        return attachmentDTO;
    }

    public Optional<Attachment> getByProblemId(Problem probrem) throws MalformedURLException {
        ProblemDTO problemDTO = problemMapper.toDTO(probrem);
        return attachmentRepository.getByProblem(problemMapper.toModel(problemDTO));

    }

    public Optional<Attachment> download(Problem probrem) throws MalformedURLException {
        ProblemDTO problemDTO = problemMapper.toDTO(probrem);
        return attachmentRepository.getByProblem(problemMapper.toModel(problemDTO));
    }

}
