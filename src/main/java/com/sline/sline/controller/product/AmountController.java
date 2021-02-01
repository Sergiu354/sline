package com.sline.sline.controller.product;

import com.sline.sline.dto.product.AmountDto;
import com.sline.sline.service.facade.project.product.amount.AmountFacade;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/amount")
@AllArgsConstructor
public class AmountController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

    private final AmountFacade amountFacade;

    @GetMapping(value = "/{uuid}")
    public AmountDto findByUuid(@PathVariable String uuid) {
        return amountFacade.findByUuid(uuid);
    }

    @GetMapping(value = "/find-by-type")
    public AmountDto findByType(@RequestParam("typeUUid") String typeUuid) {
        return amountFacade.findByTypeUuid(typeUuid);
    }

    @PostMapping(value = "/save")
    public AmountDto save(@RequestParam("uuid") String uuid,
                          @ModelAttribute AmountDto amountDto) {
        amountFacade.save(uuid, amountDto);
        return amountDto;
    }

    @PostMapping(value = "save-edit")
    public void saveEdit(@ModelAttribute AmountDto typeDto) {
        amountFacade.edit(typeDto);
    }

    @PostMapping(value = "/delete}")
    public void saveEditProduct(@RequestParam("uuid") String uuid) {
        amountFacade.delete(uuid);
    }
}
