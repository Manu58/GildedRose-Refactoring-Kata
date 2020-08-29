package com.gildedrose;

import static java.lang.Math.min;

public class BackStageUpdater extends Updater {
    @Override
    public Item update(Item item) {
        int updatedQuality = (item.sellIn > 10) ? (item.quality + 1) : ((item.sellIn > 5) ? (item.quality + 2) :
                ((item.sellIn > 0) ? (item.quality + 3) : 0));
        return new Item(item.name, item.sellIn - 1, min(50, updatedQuality));
    }
}
