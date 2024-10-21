package com.edvard.game.characters;

public abstract class Character {
    private int vit;
    public int mana;
    public int strength;
    public int magicPower;
    public int luck;
    public boolean fireball;
    public boolean struck;
    public boolean shield;

    public Character(int vit, int mana, int strength, int magicPower, int luck) {
        this.vit = vit;
        this.mana = mana;
        this.strength = strength;
        this.magicPower = magicPower;
        this.luck = luck;
        this.fireball = false;
        this.struck = false;
        this.shield = false;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public boolean isFireball() {
        return fireball;
    }

    public void setFireball(boolean fireball) {
        this.fireball = fireball;
    }

    public boolean isStruck() {
        return struck;
    }

    public void setStruck(boolean struck) {
        this.struck = struck;
    }

    public boolean isShield() {
        return shield;
    }

    public void setShield(boolean shield) {
        this.shield = shield;
    }
}
