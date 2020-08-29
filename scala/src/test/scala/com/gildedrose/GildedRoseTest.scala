package com.gildedrose

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class GildedRoseTest  extends AnyFlatSpec with Matchers {
  it should "decrease quality by 1 for a normal item when sellIn is > 0" in {
    val items = Array[Item](new Item("foo", 1, 3))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal (0)
    app.items(0).quality should equal (2)
  }

  it should "decrease quality by 2 for a normal item when sellIn is <= 0" in {
    val items = Array[Item](new Item("foo", 0, 3))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(-1)
    app.items(0).quality should equal(1)
  }

  it should "never have a negative quality" in {
    val items = Array[Item](new Item("foo", 2, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality should equal(0)
  }

  it should "increase Aged Brie's quality by 1" in {
    val items = Array[Item](new Item("Aged Brie", 2, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(1)
    app.items(0).quality should equal(11)
  }

  it should "never increase Aged Brie's quality above 50" in {
    val items = Array[Item](new Item("Aged Brie", 2, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).quality should equal(50)
  }

  it should "never change Sulfuras quality and sellIn" in {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 10, 80))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(10)
    app.items(0).quality should equal(80)
  }

  it should "increase Backstage passes quality by one when sellIn > 10" in {
    val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(10)
    app.items(0).quality should equal(11)
  }

  it should "increase Backstage passes quality by two when 5 < sellIn <= 10" in {
    val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(5)
    app.items(0).quality should equal(12)
  }

  it should "increase Backstage passes quality by three when 0 < sellIn <= 5" in {
    val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(4)
    app.items(0).quality should equal(13)
  }

  it should "set Backstage passes quality to zero when sellIn <= 0" in {
    val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(-1)
    app.items(0).quality should equal(0)
  }

  it should "decrease quality by two for a Conjured item when sellIn is > 0" in {
    val items = Array[Item](new Item("Conjured Mana Cake", 1, 3))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal (0)
    app.items(0).quality should equal (1)
  }

  it should "decrease quality by four for a Conjured item when sellIn is <= 0" in {
    val items = Array[Item](new Item("Conjured Mana Cake", 0, 5))
    val app = new GildedRose(items)
    app.updateQuality()
    app.items(0).sellIn should equal(-1)
    app.items(0).quality should equal(1)
  }

}