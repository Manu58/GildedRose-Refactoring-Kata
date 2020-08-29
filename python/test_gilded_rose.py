# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):
    def test_foo(self):
        items = [Item("foo", 1, 3)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].sell_in)
        self.assertEqual(2, items[0].quality, 'quality decreases with 1 when sell_in is positive')
        gilded_rose.update_quality()
        self.assertEqual(-1, items[0].sell_in)
        self.assertEqual(0, items[0].quality, 'quality decreases with 2 when sell_in becomes negative')
        gilded_rose.update_quality()
        self.assertEqual(-2, items[0].sell_in)
        self.assertEqual(0, items[0].quality, 'quality never becomes negative')

    def test_aged_brie(self):
        items = [Item('Aged Brie', 1, 48)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].sell_in, 'sell_in decreases')
        self.assertEqual(49, items[0].quality, 'quality increases positive sell_in')
        gilded_rose.update_quality()
        self.assertEqual(-1, items[0].sell_in)
        self.assertEqual(50, items[0].quality, 'quality increases negative sell_in')
        gilded_rose.update_quality()
        self.assertEqual(-2, items[0].sell_in)
        self.assertEqual(50, items[0].quality,  'quality does not increase higher than 50')

    def test_sulfuras(self):
        items = [Item("Sulfuras, Hand of Ragnaros", 20, 80)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(20, items[0].sell_in, 'never has to be sold')
        self.assertEqual(80, items[0].quality, 'quality never decreases')

    def test_backstage(self):
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(10, items[0].sell_in)
        self.assertEqual(11, items[0].quality, 'quality increases by one when sell_in > 10')
        gilded_rose.update_quality()
        self.assertEqual(9, items[0].sell_in)
        self.assertEqual(13, items[0].quality, 'quality increases by two when 5 < sell_in <= 10')
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 6, 10)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(5, items[0].sell_in)
        self.assertEqual(12, items[0].quality, 'quality increases by two when sell_in <= 10')
        gilded_rose.update_quality()
        self.assertEqual(4, items[0].sell_in)
        self.assertEqual(15, items[0].quality, 'quality increases by three when 0 < sell_in <= 5')
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(-1, items[0].sell_in)
        self.assertEqual(0, items[0].quality, 'quality drops to zero when sell_in <= 0')

    def test_conjured(self):
        items = [Item("Conjured Mana Cake", 1, 7)]
        gilded_rose = GildedRose(items)
        gilded_rose.update_quality()
        self.assertEqual(0, items[0].sell_in)
        self.assertEqual(5, items[0].quality, 'quality decreases with 2 when sell_in is positive')
        gilded_rose.update_quality()
        self.assertEqual(-1, items[0].sell_in)
        self.assertEqual(1, items[0].quality, 'quality decreases with 4 when sell_in becomes negative')
        gilded_rose.update_quality()
        self.assertEqual(-2, items[0].sell_in)
        self.assertEqual(0, items[0].quality, 'quality never becomes negative')


if __name__ == '__main__':
    unittest.main()
