package com.edvard.game.characters;

public class FightHero extends Character{
    int goldValue = 1000;

    int vitPrice = 5;
    int intPrice = 5;
    int strPrice = 5;
    int mpowPrice = 5;
    int luckPrice = 5;

    int peasantPrice = 5;
    int archerPrice = 15;
    int warriorPrice = 25;
    int wizardPrice = 30;
    int gryffPrice = 50;

    int peasantAmount = 10;
    int archerAmount = 10;
    int warriorAmount = 10;
    int wizardAmount = 10;
    int gryffAmount = 10;


    public FightHero(int vit, int mana, int strength, int magicPower, int luck) {
        super(vit, mana, strength, magicPower, luck);
    }

    public int getGoldValue() {
        return goldValue;
    }
    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }

    public int getVitPrice() {
        return vitPrice;
    }

    public void setVitPrice(int vitPrice) {
        this.vitPrice = vitPrice;
    }

    public int getIntPrice() {
        return intPrice;
    }

    public void setIntPrice(int intPrice) {
        this.intPrice = intPrice;
    }

    public int getStrPrice() {
        return strPrice;
    }

    public void setStrPrice(int strPrice) {
        this.strPrice = strPrice;
    }

    public int getMpowPrice() {
        return mpowPrice;
    }

    public void setMpowPrice(int mpowPrice) {
        this.mpowPrice = mpowPrice;
    }

    public int getLuckPrice() {
        return luckPrice;
    }

    public void setLuckPrice(int luckPrice) {
        this.luckPrice = luckPrice;
    }

    public int getPeasantPrice() {
        return peasantPrice;
    }

    public void setPeasantPrice(int peasantPrice) {
        this.peasantPrice = peasantPrice;
    }

    public int getArcherPrice() {
        return archerPrice;
    }

    public void setArcherPrice(int archerPrice) {
        this.archerPrice = archerPrice;
    }

    public int getWarriorPrice() {
        return warriorPrice;
    }

    public void setWarriorPrice(int warriorPrice) {
        this.warriorPrice = warriorPrice;
    }

    public int getWizardPrice() {
        return wizardPrice;
    }

    public void setWizardPrice(int wizardPrice) {
        this.wizardPrice = wizardPrice;
    }

    public int getGryffPrice() {
        return gryffPrice;
    }

    public void setGryffPrice(int gryffPrice) {
        this.gryffPrice = gryffPrice;
    }

    public int getPeasantAmount() {
        return peasantAmount;
    }

    public void setPeasantAmount(int peasantAmount) {
        this.peasantAmount = peasantAmount;
    }

    public int getArcherAmount() {
        return archerAmount;
    }

    public void setArcherAmount(int archerAmount) {
        this.archerAmount = archerAmount;
    }

    public int getWarriorAmount() {
        return warriorAmount;
    }

    public void setWarriorAmount(int warriorAmount) {
        this.warriorAmount = warriorAmount;
    }

    public int getWizardAmount() {
        return wizardAmount;
    }

    public void setWizardAmount(int wizardAmount) {
        this.wizardAmount = wizardAmount;
    }

    public int getGryffAmount() {
        return gryffAmount;
    }

    public void setGryffAmount(int gryffAmount) {
        this.gryffAmount = gryffAmount;
    }
}
