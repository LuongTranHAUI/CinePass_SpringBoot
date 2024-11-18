package com.io.ziblox.CinePass.services;

import com.io.ziblox.CinePass.dtos.DiscountDto;
import com.io.ziblox.CinePass.exceptions.DataNotFoundException;
import com.io.ziblox.CinePass.mappers.DiscountMapper;
import com.io.ziblox.CinePass.models.Discount;
import com.io.ziblox.CinePass.repositories.DiscountRepository;
import com.io.ziblox.CinePass.services.interfaces.IDiscountServie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService implements IDiscountServie {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public Discount createDiscount(DiscountDto discountDto) {
        discountRepository.findByCode(discountDto.getCode())
                .ifPresent(discount -> {
                    throw new RuntimeException("Discount already exists");
                });
        Discount newDiscount = discountMapper.toEntity(discountDto);
        return discountRepository.save(newDiscount);
    }

    @Override
    public Discount getDiscountById(int id) throws DataNotFoundException {
        return discountRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Discount not found"));
    }

    @Override
    public List<Discount> getAllDiscount() {
        return discountRepository.findAll();
    }

    @Override
    public Discount updateDiscount(int id, DiscountDto discountDto) throws DataNotFoundException {
        Discount existingDiscount = getDiscountById(id);
        Discount updatedDiscount = discountMapper.toEntity(discountDto);
        updatedDiscount.setId(existingDiscount.getId());
        updatedDiscount.setCreatedAt(existingDiscount.getCreatedAt());
        return discountRepository.save(updatedDiscount);
    }

    @Override
    public void deleteDiscount(int id) {
        discountRepository.deleteById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return discountRepository.existsByCode(code);
    }
}