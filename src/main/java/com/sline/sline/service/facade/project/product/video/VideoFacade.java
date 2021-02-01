package com.sline.sline.service.facade.project.product.video;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.dto.product.VideoDto;
import com.sline.sline.entity.project.protuct.Video;

import java.util.List;

public interface VideoFacade {
    VideoDto findByUuid(String uuid);
    List<VideoDto> findAllByType(TypeDto typeDto);
    List<VideoDto> findAllByTypeUuid(String typeUuid);
    default VideoDto save(VideoDto videoDto) {
       return save(videoDto.getType().getUuid(), videoDto);
    }
    VideoDto save(String typeUuid, VideoDto videoDto);
    VideoDto edit(VideoDto videoDto);
    default void delete(VideoDto videoDto) {
        delete(videoDto.getUuid());
    }
    void delete(String uuid);
    void setUuid(Video video);
}
