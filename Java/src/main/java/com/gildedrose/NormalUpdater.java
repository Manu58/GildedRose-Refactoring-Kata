package com.gildedrose;

import static java.lang.Math.max;

public class NormalUpdater extends Updater{
    private final int scale;

    public NormalUpdater() {
        this.scale = 1;
    }

    public NormalUpdater(int scale) {
        this.scale = scale;
    }

    @Override
    public Item update(Item item) {
        int updatedQuality = item.sellIn > 0 ? item.quality - this.scale: item.quality - 2 * scale;
        return new Item(item.name, item.sellIn - 1, max(0, updatedQuality));
    }
}
