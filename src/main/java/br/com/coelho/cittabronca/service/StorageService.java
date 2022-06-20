package br.com.coelho.cittabronca.service;

import br.com.coelho.cittabronca.config.FileStorageProperties;
import br.com.coelho.cittabronca.dto.AttachmentDTO;
import br.com.coelho.cittabronca.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StorageService {
    private final Path fileStorageLocation;

    @Autowired
    public StorageService(FileStorageProperties fileStorageProperties) throws IOException {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation);
    }

    public AttachmentDTO store(AttachmentDTO attachmentDTO) throws Exception {
        String fileName = StringUtils.cleanPath(attachmentDTO.getFile().getOriginalFilename());

        if (fileName.contains("..")) {
            throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
        }
        Pattern pattern = Pattern.compile("\\.\\w+");
        Matcher matcher = pattern.matcher(fileName);
        String extension = "";
        while (matcher.find()) {
            extension = matcher.group();
        }
        Path targetLocation = this.fileStorageLocation.resolve(attachmentDTO.getId().toString() + extension);
        Files.copy(attachmentDTO.getFile().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return attachmentDTO;
    }

    public Resource get(Attachment attachment) throws MalformedURLException {
        Path filePath = this.fileStorageLocation.resolve(attachment.getId().toString() + ".png").normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return resource;
    }
}
