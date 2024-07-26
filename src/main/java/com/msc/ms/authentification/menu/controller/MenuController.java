package com.msc.ms.authentification.menu.controller;

import com.msc.ms.authentification.common.error.DataBaseObjectNotFound;
import com.msc.ms.authentification.menu.model.dto.MenuSectionDTO;
import com.msc.ms.authentification.menu.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/menu")
@CrossOrigin("*")
@Slf4j
public class MenuController {
    private final MenuService menuService;

    public MenuController(final MenuService pMenuService) {
        menuService = pMenuService;
    }

    @GetMapping("/v1/getMenu")
    public ResponseEntity<List<MenuSectionDTO>> getMenuForProfile(
            @RequestParam(name = "idProfile", required = true) Integer pIdProfile
    ) throws DataBaseObjectNotFound {
        return ResponseEntity.ok(menuService.getMenuConfigurationFromProfile(pIdProfile));
    }
}
