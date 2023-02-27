package com.product.service.productservice.sevice;

import com.product.service.productservice.dao.ProductRepository;
import com.product.service.productservice.dto.ProductRequest;
import com.product.service.productservice.dto.ProductResponse;
import com.product.service.productservice.model.Product;
import com.product.service.productservice.utils.convertor.ProductConvertor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConvertor convertor;

    public ProductResponse addPost(ProductRequest productRequest) {
        Product product = convertor.convert(productRequest);
        // TODO: 2/25/2023 create image checker which will make get request of file-receiver
        return convertor.convert(productRepository.save(product));
    }

    public ProductResponse getPostById(String id) {
        return convertor.convert(productRepository.findById(id)
                .orElseThrow(() ->  new NoSuchElementException("no element in database")));
    }

    public List<ProductResponse> getAll() {
        return productRepository
                .findAll()
                .parallelStream()
                .map(convertor::convert)
                .toList();
    }

    public ProductResponse update(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setActive(false);
        productRepository.save(product);
        Product modifiedProduct = convertor.convert(product, productRequest);
        return convertor.convert(productRepository.save(modifiedProduct));
    }

    public ProductResponse edit(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setActive(!product.isActive());
        return convertor.convert(productRepository.save(product));
    }
}
