package com.io.ziblox.CinePass.mappers;

import com.io.ziblox.CinePass.dtos.DiscountDto;
import com.io.ziblox.CinePass.models.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DiscountMapper extends GenericMapper<DiscountDto, Discount> {
    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);
}