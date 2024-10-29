package com.carshop.mycarshop.service.shop;

import com.carshop.mycarshop.domain.shop.ItemOption;
import com.carshop.mycarshop.domain.shop.ItemOptionRepository;
import com.carshop.mycarshop.dto.shop.ItemOptionResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ItemOptionServiceImpl implements ItemOptionService {

    private final ItemOptionRepository itemOptionRepository;

    @Override
    public List<ItemOptionResDTO> getListItemOptionInfo(List<Long> listOptionId) {

        List<ItemOption> listItemOption = itemOptionRepository.findAllById(listOptionId);  // findAllById 은 주로 대량 조회

        return listItemOption.stream().map(itemOption ->
                ItemOptionResDTO.builder()
                        .itemOptionId(itemOption.getItemOptionId())
                        .optionName(itemOption.getOptionName())
                        .optionType(itemOption.getItemOptionType().getName())
                        .build()).collect(Collectors.toList());
    }
}