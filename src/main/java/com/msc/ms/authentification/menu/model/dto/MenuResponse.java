package com.msc.ms.authentification.menu.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuResponse {

    private List<MenuSectionDTO> menuSection;
}
