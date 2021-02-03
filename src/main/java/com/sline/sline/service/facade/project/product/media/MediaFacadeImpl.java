package com.sline.sline.service.facade.project.product.media;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.MediaDto;
import com.sline.sline.entity.project.protuct.Media;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.service.project.product.type.TypeService;
import com.sline.sline.service.project.product.media.MediaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediaFacadeImpl implements MediaFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final MediaService mediaService;
    private final TypeService typeService;
    private final ModelMapper modelMapper;

    @PostConstruct
    private void construct() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<MediaDto, Media>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    @Override
    public MediaDto findByUuid(String uuid) {
        Media media = mediaService.findByUuid(uuid);
        if (media !=null) {
            return convertEntityToDto(media);
        }
        LOGGER.info("Not found find by UUID: " + uuid);
        return null;
    }

    @Override
    public List<MediaDto> findAllByType(TypeDto typeDto) {
        return findAllByTypeUuid(typeDto.getUuid());
    }

    @Override
    public List<MediaDto> findAllByTypeUuid(String uuid) {
        List<Media> media = mediaService.findAllByTypeUuid(uuid);
        if (media !=null) {
            return media.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        LOGGER.info("Not found find by type UUID: " + uuid);
        return null;
    }

    @Override
    public MediaDto save(MediaDto mediaDto) {
        return save(mediaDto.getType().getUuid(), mediaDto);
    }

    @Override
    public MediaDto save(String typeUuid, MediaDto mediaDto) {
        Type type = typeService.findByUuid(typeUuid);
        if (type !=null) {
            Media media = new Media();
            convertDtoToEntity(mediaDto, media);
            media.setType(type);
            mediaService.save(media);
            return convertEntityToDto(media);
        } else {
            LOGGER.info("Not found find by type UUID: " + typeUuid);
        }

        return null;
    }

    @Override
    public MediaDto edit(MediaDto mediaDto) {
        Media media = mediaService.findByUuid(mediaDto.getUuid());
        if (media !=null) {
            convertDtoToEntity(mediaDto, media);
            mediaService.save(media);
            return convertEntityToDto(media);
        } else {
            LOGGER.info("Not found find by UUID: " + mediaDto.getUuid());
        }

        return null;
    }

    @Override
    public void delete(MediaDto mediaDto) {
        delete(mediaDto.getUuid());
    }

    @Override
    public void delete(String uuid) {
        mediaService.delete(uuid);
    }

    @Override
    public void setUuid(Media media) {

    }

    private MediaDto convertEntityToDto(Media media) {
        return modelMapper.map(media, MediaDto.class);
    }

    private void convertDtoToEntity(MediaDto mediaDto, Media media) {
        modelMapper.map(mediaDto, media);
    }
}
