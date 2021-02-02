package com.sline.sline.service.project.product.media;

import com.sline.sline.entity.project.protuct.Media;
import com.sline.sline.repository.project.product.MediaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final MediaRepository mediaRepository;

    @Override
    public Media findById(Long id) {
        return mediaRepository.findById(id).orElse(null);
    }

    @Override
    public Media findByUuid(String uuid) {
        return mediaRepository.findByUuid(uuid);
    }

    @Override
    public List<Media> findAllByTypeUuid(String typeUuid) {
        return mediaRepository.findAllByType_Uuid(typeUuid);
    }

    @Override
    public void save(Media media) {
        mediaRepository.save(media);
    }

    @Override
    public void delete(Media media) {
        mediaRepository.delete(media);
    }

    @Override
    public void delete(String uuid) {
        mediaRepository.deleteByUuid(uuid);
    }
}
