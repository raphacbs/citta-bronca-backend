package br.com.coelho.cittabronca.controller;

import br.com.coelho.cittabronca.dto.AttachmentDTO;
import br.com.coelho.cittabronca.service.AttachmentService;
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

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public AttachmentDTO uploadFile(@RequestParam("file") MultipartFile file,
                                    @RequestParam("problemId") UUID problemId) throws Exception {
        return attachmentService.save(file, problemId);
    }

    @GetMapping
    public AttachmentDTO getByProblemId(@RequestParam("problemId") UUID id) throws Exception {
        return attachmentService.getByProblemId(id);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getByProblemId(@RequestParam("problemId") UUID id, HttpServletRequest request) throws Exception {
        Resource resource = attachmentService.download(id);
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
