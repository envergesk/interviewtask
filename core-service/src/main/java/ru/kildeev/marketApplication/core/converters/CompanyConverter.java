package ru.kildeev.marketApplication.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kildeev.marketApplication.core.api.CompanyDto;
import ru.kildeev.marketApplication.core.api.ProductDto;
import ru.kildeev.marketApplication.core.entities.Company;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyConverter {

    private final ProductConverter productConverter;

    public CompanyDto entityToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(company.getId());
        companyDto.setTitle(company.getTitle());

        List<ProductDto> products = company.getProducts().stream().map(product -> productConverter.entityToDto(product))
                .collect(Collectors.toList());
        companyDto.setProducts(products);

        companyDto.setUsername(company.getUser().getUsername());

        return companyDto;
    }
}
