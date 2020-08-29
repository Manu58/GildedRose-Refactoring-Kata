# -*- coding: utf-8 -*-
from functools import partial


def update_aged_brie(item):
    """
    Aged Brie's quality always increases
    """
    quality = min(50, item.quality + 1)
    sell_in = item.sell_in - 1
    return sell_in, quality


def update_sulfuras(item):
    """
    sulfuras keeps it quality and has not to be sold
    """
    return item.sell_in, item.quality


def update_backstage(item):
    """
    backstage pass increases its quality the faster the closer it is to its concert date, but is worthless once the
    concert has been
    """
    if item.sell_in > 10:
        quality = min(50, item.quality + 1)
    elif item.sell_in > 5:
        quality = min(50, item.quality + 2)
    elif item.sell_in > 0:
        quality = min(50, item.quality + 3)
    else:
        quality = 0
    return item.sell_in - 1, quality


def normal_updater(item, scale=1):
    """
    All not special articles and the conjured article with double the quality degradation speed
    """
    quality = max(0, item.quality - scale if item.sell_in > 0 else item.quality - 2 * scale)
    return item.sell_in - 1, quality


updaters = {
    'Aged Brie': update_aged_brie,
    'Sulfuras, Hand of Ragnaros': update_sulfuras,
    'Backstage passes to a TAFKAL80ETC concert': update_backstage,
    'Conjured Mana Cake': partial(normal_updater, scale=2)
}


class GildedRose:

    def __init__(self, items):
        self.items = items

    def update_quality(self):
        for item in self.items:
            item.sell_in, item.quality = updaters.get(item.name, normal_updater)(item)


class Item:
    def __init__(self, name, sell_in, quality):
        self.name = name
        self.sell_in = sell_in
        self.quality = quality

    def __repr__(self):
        return "%s, %s, %s" % (self.name, self.sell_in, self.quality)
