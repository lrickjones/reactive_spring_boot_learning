package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Service;

@Service
public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {
}
