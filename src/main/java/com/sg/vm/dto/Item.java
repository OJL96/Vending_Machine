/*
DTO
 */
package com.sg.vm.dto;

public class Item {

    private String itemName;
    private int itemCost;
    private int numOfItems;

    private int id;
    public static int numOfUniqueItems = 0;

    public Item(String itemName, int itemCost, int numOfItems) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.numOfItems = numOfItems;
        this.id = ++numOfUniqueItems;

    }

    public Item() {
        this.id = ++numOfUniqueItems;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item{" + "itemName="
                + itemName
                + ", itemCost="
                + itemCost
                + ", numOfItems="
                + numOfItems
                + '}';
    }

}
