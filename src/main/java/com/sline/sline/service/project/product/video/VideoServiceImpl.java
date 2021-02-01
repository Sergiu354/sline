package com.sline.sline.service.project.product.video;

import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Video;
import com.sline.sline.repository.project.product.VideoRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final VideoRepository videoRepository;

    @Override
    public Video findById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public Video findByUuid(String uuid) {
        return videoRepository.findByUuid(uuid);
    }

    @Override
    public List<Video> findAllByType(Type type) {
        return findAllByTypeUuid(type.getUuid());
    }

    @Override
    public List<Video> findAllByTypeUuid(String typeUuid) {
        return findAllByTypeUuid(typeUuid);
    }

    @Override
    public void save(Video video) {
        videoRepository.save(video);
    }

    @Override
    public void delete(Video video) {
        videoRepository.delete(video);
    }

    @Override
    public void delete(String uuid) {
        videoRepository.deleteByUuid(uuid);
    }
}
