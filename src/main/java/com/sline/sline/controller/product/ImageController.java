package com.sline.sline.controller.product;

import com.sline.sline.dto.product.ImageDto;
import com.sline.sline.service.facade.project.product.image.ImageFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
@AllArgsConstructor
public class ImageController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final ImageFacade imageFacade;

    @GetMapping(value = "/{uuid}")
    public ImageDto findByUuid(@PathVariable String uuid) {
        return imageFacade.findByUuid(uuid);
    }

    @GetMapping(value = "/find-by-type")
    public List<ImageDto> findByType(@RequestParam("typeUUid") String typeUuid) {
        return imageFacade.findAllByTypeUuid(typeUuid);
    }

    @PostMapping(value = "/save")
    public ImageDto save(@RequestParam("uuid") String uuid,
                          @ModelAttribute ImageDto imageDto) {
        imageFacade.save(uuid, imageDto);
        return imageDto;
    }

    @PostMapping(value = "save-edit")
    public void saveEdit(@ModelAttribute ImageDto imageDto) {
        imageFacade.edit(imageDto);
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        imageFacade.delete(uuid);
    }
}
