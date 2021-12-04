package com.example.eeaassignment;

public class ItemDTO {
    private Long id;

    private String name;

    private String availability;

    private String price;

    private String description;

    private String specifications;

    private String suitableFor;

    private String howToUse;

    private String ingredients;

    private String delivery;

    private String returnItem;

    private String itemType;

    private String image;

    public ItemDTO(String name, String price, String description, String specifications, String suitableFor, String howToUse, String ingredients, String delivery, String returnItem, String itemType, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.specifications = specifications;
        this.suitableFor = suitableFor;
        this.howToUse = howToUse;
        this.ingredients = ingredients;
        this.delivery = delivery;
        this.returnItem = returnItem;
        this.itemType = itemType;
        this.image = image;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getSuitableFor() {
        return suitableFor;
    }

    public void setSuitableFor(String suitableFor) {
        this.suitableFor = suitableFor;
    }

    public String getHowToUse() {
        return howToUse;
    }

    public void setHowToUse(String howToUse) {
        this.howToUse = howToUse;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getReturnItem() {
        return returnItem;
    }

    public void setReturnItem(String returnItem) {
        this.returnItem = returnItem;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
