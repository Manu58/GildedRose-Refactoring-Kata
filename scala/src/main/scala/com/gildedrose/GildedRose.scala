package com.gildedrose

import scala.math.{max, min}


class GildedRose(val items: Array[Item]) {

  /** Aged Brie item's quality always increases
   *
   * @param item an Aged Brie item
   * @return a tuple of the updated sellIn and quality of the item
   */
  private[this] def updateAgedBrie(item: Item): (Int, Int) = {
    (item.sellIn - 1, min(50, item.quality + 1))
  }

  /** Sulfuras item's quality never changes and never has to be sold
   *
   * @param item a Sulfuras item
   * @return a tuple of the updated sellIn and quality of the item
   */
  private[this] def updateSulfuras(item: Item): (Int, Int) = {
    (item.sellIn, item.quality)
  }

  /** Back Stage item's quality increases progressively the closer it is to the day of the concert, but drops
   * to zero after the concert
   *
   * @param item a Back Stage item
   * @return a tuple of the updated sellIn and quality of the item
   */
  private[this] def updateBackStage(item: Item): (Int, Int) = {
    item.sellIn match {
      case x: Int if x > 10 => (item.sellIn - 1, min(50, item.quality +1))
      case x: Int if x > 5 => (item.sellIn - 1, min(50, item.quality +2))
      case x: Int if x > 0 => (item.sellIn - 1, min(50, item.quality +3))
      case _ => (item.sellIn -1, 0)
    }
  }

  /** Normal item's quality decreases with one till its sell date, decreases twice as fast after.
   * For Conjured items the quality decreases twice as fast
   *
   * @param item a Normal or Conjured item
   * @param scale an optional scale factor to cover Conjured items
   * @return a tuple of the updated sellIn and quality of the item
   */
  private[this] def updateNormalItem(item: Item, scale: Int = 1):  (Int, Int) = {
    item.sellIn match {
      case x: Int if x > 0 => (item.sellIn - 1, max(0, item.quality - scale))
      case _ => (item.sellIn - 1, max(0, item.quality - 2 * scale))
    }
  }

  /** updates the quality and sellIn of an item depending on its name
   *
   * @param item a shop item
   * @return a tuple of the updated sellIn and quality of the item
   */
  private[this] def updaters(item: Item) = {
    item.name match {
      case "Aged Brie" => updateAgedBrie(item)
      case "Sulfuras, Hand of Ragnaros" => updateSulfuras(item)
      case "Backstage passes to a TAFKAL80ETC concert" => updateBackStage(item)
      case "Conjured Mana Cake" => updateNormalItem (item,2)
      case _ => updateNormalItem(item)
    }
  }

  def updateQuality() {
        for (item <- items) {
          val (sellIn, quality) = updaters(item)
          item.sellIn = sellIn
          item.quality = quality
        }
    }
}