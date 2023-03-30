package ru.kildeev.marketApplication.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.marketApplication.core.entities.Discount;
import ru.kildeev.marketApplication.core.repositories.DiscountRepository;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public Discount getByAmount(Integer amount) {
        return discountRepository.findByAmount(amount);
    }
}
