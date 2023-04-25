package com.product.service.utils.request.jdbc.executor;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;


public interface JdbcExecutor<T, ID> extends ListCrudRepository<T, ID> {

    Optional<T> getWithNotData(ID id);
}
