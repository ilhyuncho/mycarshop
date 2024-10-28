package com.carshop.mycarshop.dto.notification;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class NotiDeleteRegDTO {
    //private String name;
    private List<String> fileNames= new ArrayList<>();
}
