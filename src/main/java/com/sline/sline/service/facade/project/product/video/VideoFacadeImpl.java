package com.sline.sline.service.facade.project.product.video;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.VideoDto;
import com.sline.sline.entity.project.protuct.Type;
import com.sline.sline.entity.project.protuct.Video;
import com.sline.sline.service.project.product.type.TypeService;
import com.sline.sline.service.project.product.video.VideoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VideoFacadeImpl implements VideoFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final VideoService videoService;
    private final TypeService typeService;

    @Override
    public VideoDto findByUuid(String uuid) {
        Video video = videoService.findByUuid(uuid);
        if (video!=null) {
            return convertEntityToDto(video);
        }
        LOGGER.info("Not found find by UUID: " + uuid);
        return null;
    }

    @Override
    public List<VideoDto> findAllByType(TypeDto typeDto) {
        return findAllByTypeUuid(typeDto.getUuid());
    }

    @Override
    public List<VideoDto> findAllByTypeUuid(String typeUuid) {
        List<Video> videos = videoService.findAllByTypeUuid(typeUuid);
        if (videos!=null) {
            return videos.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        LOGGER.info("Not found by type UUID: " + typeUuid);
        return null;
    }

    @Override
    public VideoDto save(String typeUuid, VideoDto videoDto) {
        Type type = typeService.findByUuid(typeUuid);
        if (type !=null) {
            Video video = new Video();
            convertDtoToEntity(videoDto, video);
            video.setType(type);
            videoService.save(video);
            return convertEntityToDto(video);
        } else
            LOGGER.info("Not found type by UUID: " + typeUuid);
        return null;
    }

    @Override
    public VideoDto edit(VideoDto videoDto) {
        Video video = videoService.findByUuid(videoDto.getUuid());
        if (video!=null) {
            convertDtoToEntity(videoDto, video);
            videoService.save(video);
            return convertEntityToDto(video);
        }
        LOGGER.info("Not Found find video by UUID: " + videoDto.getUuid());
        return null;
    }

    @Override
    public void delete(String uuid) {
        videoService.delete(uuid);
    }

    @Override
    public void setUuid(Video video) {
        video.setUuid(UUID.randomUUID().toString());
    }

    private VideoDto convertEntityToDto(Video video) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(video, VideoDto.class);
    }

    private void convertDtoToEntity(VideoDto videoDto, Video video) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(new PropertyMap<VideoDto, Video>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        modelMapper.map(videoDto, video);
    }
}
