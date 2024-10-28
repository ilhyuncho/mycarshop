package com.carshop.mycarshop.service.review;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.review.ReviewListResDTO;
import com.carshop.mycarshop.dto.review.ReviewResDTO;
import com.carshop.mycarshop.dto.review.ReviewWriteReqDTO;

public interface ReviewService {
    ReviewListResDTO<ReviewResDTO> getListReview(PageRequestDTO pageRequestDTO, Long shopItemId);
    ReviewResDTO getReview(Long reviewId);
    void writeReview(User user, ReviewWriteReqDTO reviewWriteReqDTO);
}