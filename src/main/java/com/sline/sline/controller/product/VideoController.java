package com.sline.sline.controller.product;

import com.sline.sline.dto.product.VideoDto;
import com.sline.sline.service.facade.project.product.video.VideoFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@AllArgsConstructor
public class VideoController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final VideoFacade videoFacade;

    @GetMapping(value = "/{uuid}")
    public VideoDto findByUuid(@PathVariable String uuid) {
        return videoFacade.findByUuid(uuid);
    }

    @GetMapping(value = "/find-by-type")
    public List<VideoDto> findByType(@RequestParam("typeUUid") String typeUuid) {
        return videoFacade.findAllByTypeUuid(typeUuid);
    }

    @PostMapping(value = "/save")
    public VideoDto save(@RequestParam("uuid") String uuid,
                         @ModelAttribute VideoDto videoDto) {
        videoFacade.save(uuid, videoDto);
        return videoDto;
    }

    @PostMapping(value = "save-edit")
    public void saveEdit(@ModelAttribute VideoDto videoDto) {
        videoFacade.edit(videoDto);
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        videoFacade.delete(uuid);
    }
}
