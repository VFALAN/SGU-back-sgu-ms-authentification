package com.msc.ms.authentification.menu.service;

import com.msc.ms.authentification.common.error.DataBaseObjectNotFound;
import com.msc.ms.authentification.menu.model.dto.MenuSectionDTO;
import com.msc.ms.authentification.menu.model.entity.MenuSectionEntity;
import com.msc.ms.authentification.menu.repository.IMenuSectionRepository;
import com.msc.ms.authentification.profile.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MenuService {
    private final IMenuSectionRepository iMenuSectionRepository;
    private final ProfileService profileService;

    public MenuService(
            final IMenuSectionRepository pIMenuSectionRepository,
            final ProfileService pProfileService
    ) {
        iMenuSectionRepository = pIMenuSectionRepository;
        profileService = pProfileService;
    }


    public List<MenuSectionDTO> getMenuConfigurationFromProfile(Integer pIdProfile) throws DataBaseObjectNotFound {
        log.info("loading menu for the profile {}", pIdProfile);
        final var mListMenuSectionDTOMenuFromTop = new ArrayList<MenuSectionDTO>();
        final var mProfileEntity = profileService.findById(pIdProfile);
        iMenuSectionRepository.findAllTopByProfile(mProfileEntity).forEach(mEntity -> {
            mListMenuSectionDTOMenuFromTop.add(MenuSectionDTO.builder()
                    .name(mEntity.getName())
                    .path(mEntity.getPath())
                    .parent(mEntity.getIdParentMenu())
                    .subMenus(getMenuFromParent(mEntity.getIdMenuSection()))
                    .build());
        });
        return mListMenuSectionDTOMenuFromTop;
    }

    // recurrence function to load all sub levels in menus
    private List<MenuSectionDTO> getMenuFromParent(Integer idParent) {
        log.info("loading Sub Menus form menu parent {}", idParent);
        final List<MenuSectionDTO> menuSectionDTOS = new ArrayList<>();
        final var subMenus = iMenuSectionRepository.findAllByParent(idParent);
        log.info("has been founded {} items as sub Menus of menu parent {}", subMenus.size(), idParent);
        subMenus.forEach(s -> {
            final var mMenuSectionDTO = MenuSectionDTO.builder().parent(idParent).path(s.getPath()).name(s.getName()).subMenus(getMenuFromParent(s.getIdMenuSection())).build();
            menuSectionDTOS.add(mMenuSectionDTO);
        });
        return menuSectionDTOS;

    }
}
