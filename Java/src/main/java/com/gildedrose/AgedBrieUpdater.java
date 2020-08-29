package com.gildedrose;

import static java.lang.Math.min;

public class AgedBrieUpdater extends Updater{
    @Override
    public Item update(Item item) {
        return new Item("Aged Brie", item.sellIn - 1, min(50, item.quality + 1));
    }
}
