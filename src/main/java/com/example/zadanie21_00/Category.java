package com.example.zadanie21_00;

public enum Category {

    GROCERIES("art. spożywcze"),
    HOUSEHOLD_ITEMS("art. gospodarstwa domowego"),
    OTHER("inne");

    private String descriptionPl;

    Category(String descriptionPl) {
        this.descriptionPl = descriptionPl;
    }

    public String getDescriptionPl() {
        return descriptionPl;
    }

    public void setDescriptionPl(String descriptionPl) {
        this.descriptionPl = descriptionPl;
    }
}
