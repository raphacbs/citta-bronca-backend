package br.com.coelho.cittabronca.mapper;

import br.com.coelho.cittabronca.dto.AttachmentDTO;
import br.com.coelho.cittabronca.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttachmentMapper {
    AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

    Attachment toModel(AttachmentDTO attachmentDTO);

    List<Attachment> toModel(List<AttachmentDTO> attachmentDTOList);

    AttachmentDTO toDTO(Attachment attachment);

    List<AttachmentDTO> toDTO(List<Attachment> attachments);

}
