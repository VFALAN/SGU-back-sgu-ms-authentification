package com.msc.ms.authentification.menu.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuSectionDTO {
    private String name;
    private String path;
    private Integer parent;
    private List<MenuSectionDTO> subMenus;
}
