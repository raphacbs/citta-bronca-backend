package br.com.coelho.cittabronca.controller;

import br.com.coelho.cittabronca.dto.AttachmentDTO;
import br.com.coelho.cittabronca.entity.Attachment;
import br.com.coelho.cittabronca.entity.Problem;
import br.com.coelho.cittabronca.exception.ResouceNotFoundException;
import br.com.coelho.cittabronca.service.AttachmentService;
import br.com.coelho.cittabronca.service.ProblemService;
import br.com.coelho.cittabronca.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/attachments")
public class AttachmentController {
    private final AttachmentService attachmentService;
    private final ProblemService problemService;
    private final StorageService storageService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService, ProblemService problemService, StorageService storageService) {
        this.attachmentService = attachmentService;
        this.problemService = problemService;
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<AttachmentDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("problemId") UUID problemId) throws Exception {
        Problem problem = this.problemService.findById(problemId).orElseThrow(
                () -> new ResouceNotFoundException("Problem not found" + problemId));
        return ResponseEntity.ok(attachmentService.save(file, problem));
    }

    @GetMapping
    public ResponseEntity<Attachment> getByProblemId(@RequestParam("problemId") UUID problemId) throws Exception {
        Problem problem = this.problemService.findById(problemId).orElseThrow(
                () -> new ResouceNotFoundException("Problem not found" + problemId));

        Attachment attachment = this.attachmentService.getByProblemId(problem).orElseThrow(
                () -> new ResouceNotFoundException("attachment not found"));
        return ResponseEntity.ok().body(attachment);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("problemId") UUID problemId, HttpServletRequest request) throws Exception {
        Problem problem = this.problemService.findById(problemId).orElseThrow(
                () -> new ResouceNotFoundException("Problem not found" + problemId));
        Attachment attachment = this.attachmentService.download(problem).orElseThrow(
                () -> new ResouceNotFoundException("attachment not found"));
        Resource resource = this.storageService.get(attachment);
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
