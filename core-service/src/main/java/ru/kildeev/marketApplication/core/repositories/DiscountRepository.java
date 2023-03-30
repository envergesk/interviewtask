package ru.kildeev.marketApplication.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kildeev.marketApplication.core.entities.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findByAmount(Integer amount);
}
