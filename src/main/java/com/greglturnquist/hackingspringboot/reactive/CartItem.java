package com.greglturnquist.hackingspringboot.reactive;

import java.util.Objects;

public class CartItem {
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private Item item;
    private int quantity;

    private CartItem() {}

    CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increment() {
        quantity++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity &&
                item.equals(cartItem.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

    public void decrement() {
        if (quantity > 0) quantity--;
    }
}
