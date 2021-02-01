package com.sline.sline.service.facade.project.product.image;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.ImageDto;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Image;
import com.sline.sline.service.project.product.type.TypeService;
import com.sline.sline.service.project.product.image.ImageService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageFacadeImpl implements ImageFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final ImageService imageService;
    private final TypeService typeService;

    @Override
    public ImageDto findByUuid(String uuid) {
        Image image = imageService.findByUuid(uuid);
        if (image!=null) {
            return convertEntityToDto(image);
        }
        LOGGER.info("Not found find by UUID: " + uuid);
        return null;
    }

    @Override
    public List<ImageDto> findAllByType(TypeDto typeDto) {
        return findAllByTypeUuid(typeDto.getUuid());
    }

    @Override
    public List<ImageDto> findAllByTypeUuid(String uuid) {
        List<Image> images = imageService.findAllByTypeUuid(uuid);
        if (images!=null) {
            return images.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        LOGGER.info("Not found find by type UUID: " + uuid);
        return null;
    }

    @Override
    public ImageDto save(ImageDto imageDto) {
        return save(imageDto.getType().getUuid(), imageDto);
    }

    @Override
    public ImageDto save(String typeUuid, ImageDto imageDto) {
        Type type = typeService.findByUuid(typeUuid);
        if (type !=null) {
            Image image = new Image();
            convertDtoToEntity(imageDto, image);
            image.setType(type);
            imageService.save(image);
            return convertEntityToDto(image);
        } else {
            LOGGER.info("Not found find by type UUID: " + typeUuid);
        }

        return null;
    }

    @Override
    public ImageDto edit(ImageDto imageDto) {
        Image image = imageService.findByUuid(imageDto.getUuid());
        if (image!=null) {
            convertDtoToEntity(imageDto, image);
            imageService.save(image);
            return convertEntityToDto(image);
        } else {
            LOGGER.info("Not found find by UUID: " + imageDto.getUuid());
        }

        return null;
    }

    @Override
    public void delete(ImageDto imageDto) {
        delete(imageDto.getUuid());
    }

    @Override
    public void delete(String uuid) {
        imageService.delete(uuid);
    }

    @Override
    public void setUuid(Image image) {

    }

    private ImageDto convertEntityToDto(Image image) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(image, ImageDto.class);
    }

    private void convertDtoToEntity(ImageDto imageDto, Image image) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<ImageDto, Image>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.map(imageDto, image);
    }
}
