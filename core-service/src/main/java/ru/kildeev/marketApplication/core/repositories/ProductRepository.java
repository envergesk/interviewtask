package ru.kildeev.marketApplication.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kildeev.marketApplication.core.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query(value = "SELECT p FROM Product p LEFT join Comment c")
//    List<Product> fetchAll();
}
