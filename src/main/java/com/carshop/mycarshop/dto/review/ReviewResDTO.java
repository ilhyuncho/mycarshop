package com.carshop.mycarshop.dto.review;

import com.carshop.mycarshop.dto.ImageListDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewResDTO extends ImageListDTO {
    private Long reviewId;
    private String reviewer;
    private String reviewText;
    private int score;
    private LocalDate regDate;
}
