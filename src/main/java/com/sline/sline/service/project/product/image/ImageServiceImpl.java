package com.sline.sline.service.project.product.image;

import com.sline.sline.entity.project.protuct.Image;
import com.sline.sline.repository.project.product.ImagesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final ImagesRepository imagesRepository;

    @Override
    public Image findById(Long id) {
        return imagesRepository.findById(id).orElse(null);
    }

    @Override
    public Image findByUuid(String uuid) {
        return imagesRepository.findByUuid(uuid);
    }

    @Override
    public List<Image> findAllByTypeUuid(String typeUuid) {
        return imagesRepository.findAllByType_Uuid(typeUuid);
    }

    @Override
    public void save(Image image) {
        imagesRepository.save(image);
    }

    @Override
    public void delete(Image image) {
        imagesRepository.delete(image);
    }

    @Override
    public void delete(String uuid) {
        imagesRepository.deleteByUuid(uuid);
    }
}
