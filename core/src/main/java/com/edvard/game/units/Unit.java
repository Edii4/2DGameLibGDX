package com.edvard.game.units;

public abstract class Unit {
    private boolean heroUnit;
    private int quantity;
    private int damage;
    private int hp;
    private int defense;

    public Unit(boolean heroUnit, int quantity, int damage, int hp, int defense) {
        this.heroUnit = heroUnit;
        this.quantity = quantity;
        this.damage = damage;
        this.hp = hp;
        this.defense = defense;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isHeroUnit() {
        return heroUnit;
    }

    public void setHeroUnit(boolean heroUnit) {
        this.heroUnit = heroUnit;
    }
}
