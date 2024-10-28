package com.carshop.mycarshop.dto.notification;


import com.carshop.mycarshop.dto.ImageListDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NotiResDTO extends ImageListDTO {
    private Long notiId;
    private String name;
    private String title;
    private String message;
    private LocalDate regDate;
    private Boolean isUse;
    private Boolean isPopup;

}