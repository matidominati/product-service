package com.matidominati.productservie.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "shopping-cart-service", url = "${shopping.cart.api.url}")
public interface ShoppingCartClient {
}
