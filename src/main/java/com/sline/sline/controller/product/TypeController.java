package com.sline.sline.controller.product;

import com.sline.sline.dto.product.TypeDto;
import com.sline.sline.service.facade.project.product.type.TypeFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@AllArgsConstructor
public class TypeController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final TypeFacade typeFacade;

    @GetMapping(value = "/{uuid}")
    public TypeDto findByUuid(@PathVariable String uuid) {
        return typeFacade.findByUuid(uuid);
    }

    @GetMapping(value = "/find-all-by-product")
    public List<TypeDto> findAllByProduct(@RequestParam("productUuid") String productUuid) {
        return typeFacade.findAllByByProductUuid(productUuid);
    }

    @PostMapping(value = "/save")
    public TypeDto save(@RequestParam("productUuid") String productUuid,
                        @ModelAttribute TypeDto typeDto) {
        typeFacade.save(productUuid, typeDto);
        return typeDto;
    }

    @PostMapping(value = "save-edit")
    public void saveEdit(@ModelAttribute TypeDto typeDto) {
        typeFacade.edit(typeDto);
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        typeFacade.delete(uuid);
    }
}
