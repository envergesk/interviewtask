package ru.kildeev.marketApplication.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.ProductDto;
import ru.kildeev.marketApplication.core.entities.Product;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CommentConverter commentConverter;
    private final HashtagConverter hashtagConverter;

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setCompany(product.getCompany().getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setRemains(product.getRemains());
        productDto.setDiscount(product.getDiscount().getAmount().toString());
        productDto.setComments(product.getComments().stream().map(comment -> commentConverter.entityToDto(comment)).collect(Collectors.toList()));
        productDto.setHashtags(product.getHashtags().stream().map(hashtag -> hashtagConverter.entityToDto(hashtag)).collect(Collectors.toList()));

        productDto.setSpecifications(product.getSpecifications());
        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
//        product.setCompany(productDto.getCompany());
//        product.setDiscount(productDto.getDiscount());
//        product.setFeedback(productDto.getFeedback());
//        product.setHashtags(productDto.getHashtags());
        product.setId(productDto.getId());
        product.setPrice(productDto.getPrice());
        product.setRemains(productDto.getRemains());
        product.setRemains(productDto.getRemains());
        product.setSpecifications(productDto.getSpecifications());
        return product;
    }
}
