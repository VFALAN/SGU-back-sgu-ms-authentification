package com.msc.ms.authentification.menu.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemDTO {

    private String name;
    private String path;
    private String icon;
}
