package com.product.service.utils.request.jdbc.executor;

import org.springframework.data.repository.CrudRepository;

public interface JdbcExecutor<T, ID> extends CrudRepository<T, ID> {
}
