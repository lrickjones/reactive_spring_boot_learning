package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    //private InventoryService inventoryService;
    private final CartService cartService;

    public HomeController(ItemRepository itemRepository,CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = new CartService(itemRepository,cartRepository);
        //this.inventoryService = new InventoryService(itemByExampleRepository);
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items",this.itemRepository.findAll())
                .modelAttribute("cart",this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart("My Cart",id).thenReturn("redirect:/");
    }
/*
    @GetMapping("/search")
    Mono<Rendering> search (
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String description,
            @RequestParam boolean useAnd) {
        return Mono.just(Rendering.view("home.html").modelAttributes("results",
                inventoryService.searchByExample(name,description,useAnd)).build());
    }
 */
}
