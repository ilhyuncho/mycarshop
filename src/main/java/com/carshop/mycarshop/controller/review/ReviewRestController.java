package com.carshop.mycarshop.controller.review;

import com.carshop.mycarshop.domain.user.User;
import com.carshop.mycarshop.dto.PageRequestDTO;
import com.carshop.mycarshop.dto.review.ReviewListResDTO;
import com.carshop.mycarshop.dto.review.ReviewResDTO;
import com.carshop.mycarshop.dto.review.ReviewWriteReqDTO;
import com.carshop.mycarshop.service.review.ReviewService;
import com.carshop.mycarshop.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewRestController {
    private final ReviewService reviewService;
    private final UserService userService;

    @GetMapping("/list")
    public ReviewListResDTO<ReviewResDTO> getListReview(PageRequestDTO pageRequestDTO, Long shopItemId){

        ReviewListResDTO<ReviewResDTO> listReview = reviewService.getListReview(pageRequestDTO, shopItemId);

        log.error(listReview);
        return listReview;
    }
    @GetMapping("/{reviewId}")
    public ReviewResDTO getReview(@PathVariable(name="reviewId") Long reviewId){
        return reviewService.getReview(reviewId);
    }

    @ApiOperation(value = "리뷰 등록", notes = "구매자가 요청")
    @PostMapping("/write")
    public Map<String,String> postWriteReview(@Valid @RequestBody ReviewWriteReqDTO reviewWriteReqDTO,
                                              BindingResult bindingResult,
                                              Principal principal ) throws BindException {
        if(bindingResult.hasErrors()){
            log.error("has errors.....");
            throw new BindException(bindingResult);
        }

        User user = userService.findUser(principal.getName());

        reviewService.writeReview(user, reviewWriteReqDTO);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return resultMap;
    }
}