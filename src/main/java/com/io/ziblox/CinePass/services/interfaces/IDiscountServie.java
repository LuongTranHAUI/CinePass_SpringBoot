package com.io.ziblox.CinePass.services.interfaces;

import com.io.ziblox.CinePass.dtos.DiscountDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.models.Discount;

import java.util.List;

public interface IDiscountServie {
    Discount createDiscount(DiscountDto discountDto);
    Discount getDiscountById(int id) throws DataNotFoundException;
    List<Discount> getAllDiscount();
    Discount updateDiscount(int discountId, DiscountDto discountDto) throws DataNotFoundException;
    void deleteDiscount(int discountId);
    boolean existsByCode(String code);
}
