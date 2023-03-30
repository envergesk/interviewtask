package ru.kildeev.marketApplication.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kildeev.marketApplication.core.api.ProductDto;
import ru.kildeev.marketApplication.core.entities.Product;
import ru.kildeev.marketApplication.core.services.ProductService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/change_product")
    @ResponseStatus(HttpStatus.OK)
    public void changeProductInfo(@RequestBody ProductDto productDto) {
        productService.changeProduct(productDto);
    }

    @GetMapping("/{id}/buy")
    @ResponseStatus(HttpStatus.OK)
    public void buyProduct(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        productService.buyProduct(username, id);
    }
}
