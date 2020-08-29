package com.gildedrose;

import java.util.Map;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private static final Map<String, Updater> updaters = Map.of(
            "Aged Brie", new AgedBrieUpdater(),
            "Sulfuras, Hand of Ragnaros", new SulfurasUpdater(),
            "Backstage passes to a TAFKAL80ETC concert", new BackStageUpdater(),
            "Conjured Mana Cake", new NormalUpdater(2)
    );

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Updater updater = updaters.getOrDefault(items[i].name, new NormalUpdater());
            items[i] = updater.update(items[i]);
        }
    }
}