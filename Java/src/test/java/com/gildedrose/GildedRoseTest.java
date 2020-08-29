package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateNormalItem() {
        Item[] items = new Item[] { new Item("foo", 1, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn, "sellIn decreases");
        assertEquals(2, app.items[0].quality, "quality decreases by 1 when sellIn > 0");
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality decreases by 2 when sellIn <= 0");
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never negative");
    }

    @Test
    void updateAgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn, "sellIn decreases to zero");
        assertEquals(49, app.items[0].quality, "quality increases by 1 when sellIn > 0");
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "quality increases by 1 when sellIn <= 0");
        app.updateQuality();
        assertEquals(50, app.items[0].quality, "quality is never larger than 50");
    }

    @Test
    void updateSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 20, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(20, app.items[0].sellIn, "sellIn does not change");
        assertEquals(80, app.items[0].quality, "quality does not change");
    }

    @Test
    void updateBackStage() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, app.items[0].sellIn, "sellIn decreases to zero");
        assertEquals(11, app.items[0].quality, "quality increases by 1 when sellIn > 10");
        app.updateQuality();
        assertEquals(13, app.items[0].quality, "quality increases by 2 when 5 < sellIn <= 10");
        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality, "quality increases by 2 when 5 < sellIn <= 10");
        app.updateQuality();
        assertEquals(15, app.items[0].quality, "quality increases by 3 when 0 < sellIn <= 5");
        items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality drops to 0 when sellIn <= 0");
    }

    @Test
    void updateConjured() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 1, 7) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn, "sellIn decreases");
        assertEquals(5, app.items[0].quality, "quality decreases by 2 when sellIn > 0");
        app.updateQuality();
        assertEquals(1, app.items[0].quality, "quality decreases by 4 when sellIn <= 0");
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never negative");
    }

}
