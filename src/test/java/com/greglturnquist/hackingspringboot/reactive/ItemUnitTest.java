package com.greglturnquist.hackingspringboot.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ItemUnitTest {
    @Test
    void itemBasicsShouldWork() {
        Item sampleItem = new Item("item1","TV Tray","Alf TV Tray", 19.99);
        Assert.isTrue(sampleItem.getId().equals("item1"),"ID getter failed");
        Assert.isTrue(sampleItem.getName().equals("TV Tray"),"Name getter failed");
        Assert.isTrue(sampleItem.getDescription().equals("Alf TV Tray"),"Description getter failed");
        Assert.isTrue(sampleItem.getPrice() == 19.99,"Price getter failed");
        Assert.isTrue(sampleItem.toString().equals("Item{id='item1', name='TV Tray', description='Alf TV Tray', price=19.99}"),"toString() failed");
        Item sampleItem2 = new Item("item1","TV Tray","Alf TV Tray", 19.99);
        Assert.isTrue(sampleItem.equals(sampleItem2),"equals() failed");
    }
}
