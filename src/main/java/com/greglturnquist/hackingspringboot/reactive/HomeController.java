package com.greglturnquist.hackingspringboot.reactive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private InventoryService inventoryService;

    public HomeController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    Mono<Rendering> home() { // <1>
        return Mono.just(Rendering.view("home.html") // <2>
                .modelAttribute("items", this.inventoryService.getInventory()) // <3>
                .modelAttribute("cart", this.inventoryService.getCart("My Cart") // <4>
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
    // end::2[]

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.inventoryService.addItemToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @DeleteMapping("/remove/{id}")
    Mono<String> removeFromCart(@PathVariable String id) {
        return this.inventoryService.removeOneFromCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @PostMapping
    Mono<String> createItem(@ModelAttribute Item newItem) {
        return this.inventoryService.saveItem(newItem) //
                .thenReturn("redirect:/");
    }

    @DeleteMapping("/delete/{id}")
    Mono<String> deleteItem(@PathVariable String id) {
        return this.inventoryService.deleteItem(id) //
                .thenReturn("redirect:/");
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
