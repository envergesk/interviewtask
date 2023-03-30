package ru.kildeev.marketApplication.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kildeev.marketApplication.core.api.ProductDto;
import ru.kildeev.marketApplication.core.converters.ProductConverter;
import ru.kildeev.marketApplication.core.entities.Discount;
import ru.kildeev.marketApplication.core.entities.Order;
import ru.kildeev.marketApplication.core.entities.Product;
import ru.kildeev.marketApplication.core.exceptions.ResourceNotFoundException;
import ru.kildeev.marketApplication.core.repositories.ProductRepository;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    private final OrderService orderService;

    private final DiscountService discountService;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public ProductDto getProductById(Long productId) {
        return productConverter.entityToDto(productRepository.findById(productId).get());
    }

    public List<ProductDto> getAll() {
//        return productRepository.fetchAll().stream().map(p -> productConverter.entityToDto(p)).collect(Collectors.toList());
        return productRepository.findAll().stream().map(product -> productConverter.entityToDto(product)).collect(Collectors.toList());
    }

    public void changeProduct(ProductDto productDto) {
        Product product2 = productConverter.dtoToEntity(productDto);
        productRepository.save(product2);

    }

    @Transactional
    public void buyProduct(String username, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));
        Order order = new Order(null, username, product.getTitle(), product.getDiscount().getAmount(), product.getPrice());
        orderService.saveOrder(order);
    }

    public void changeProductDiscount(Long id, Integer discountAmount) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));
        Discount discount = discountService.getByAmount(discountAmount);
        product.setDiscount(discount);
        productRepository.save(product);
    }
}
