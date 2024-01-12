package com.matidominati.productservie.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shopping-cart-service", url = "http://localhost:8081")
public interface ShoppingCartClient {
}
