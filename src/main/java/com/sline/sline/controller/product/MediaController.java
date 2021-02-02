package com.sline.sline.controller.product;

import com.sline.sline.dto.product.MediaDto;
import com.sline.sline.service.facade.project.product.media.MediaFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class MediaController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final MediaFacade mediaFacade;

    @GetMapping(value = "/{uuid}")
    public MediaDto findByUuid(@PathVariable String uuid) {
        return mediaFacade.findByUuid(uuid);
    }

    @GetMapping(value = "/find-by-type")
    public List<MediaDto> findByType(@RequestParam("typeUUid") String typeUuid) {
        return mediaFacade.findAllByTypeUuid(typeUuid);
    }

    @PostMapping(value = "/save")
    public MediaDto save(@RequestParam("uuid") String uuid,
                         @ModelAttribute MediaDto mediaDto) {
        mediaFacade.save(uuid, mediaDto);
        return mediaDto;
    }

    @PostMapping(value = "save-edit")
    public void saveEdit(@ModelAttribute MediaDto mediaDto) {
        mediaFacade.edit(mediaDto);
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        mediaFacade.delete(uuid);
    }
}
