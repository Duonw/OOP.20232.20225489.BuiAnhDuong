package hust.soict.dsai.aims.cart;

import java.util.Objects;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private static int qtyOrdered = 0;

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is full");
            return;
        } else {
            if (qtyOrdered == MAX_NUMBERS_ORDERED - 1) {
                System.out.println("The cart is almost full");
                itemsOrdered[qtyOrdered] = disc;
                qtyOrdered += 1;
            } else {
                System.out.println("The disc has been added");
                itemsOrdered[qtyOrdered] = disc;
                qtyOrdered += 1;
            }
        }
    }

    /*
    public void addDigitalVideoDisc(hust.soict.dsai.aims.disc.DigitalVideoDisc[] dvdList) {
        for (int i = 0; i < dvdList.length; i ++) {
            if (qtyOrdered == MAX_NUMBERS_ORDERED) {
                System.out.printf("Cannot add from the %dth dvd because the cart is full\n", i + 1);
                break;
            } else {
                itemsOrdered[qtyOrdered] = dvdList[i];
                System.out.printf("The %dth dvd has been added\n", i + 1);
                qtyOrdered += 1;
            }
        }
    }

    public void addDigitalVideoDisc(hust.soict.dsai.aims.disc.DigitalVideoDisc... dvds) {
        int i = 0;
        for (hust.soict.dsai.aims.disc.DigitalVideoDisc dvd: dvds) {
            if (qtyOrdered == MAX_NUMBERS_ORDERED) {
                System.out.printf("Cannot add from the %dth dvd because the cart is full\n", i + 1);
                break;
            } else {
                itemsOrdered[qtyOrdered] = dvd;
                System.out.printf("The %dth dvd has been added\n", i + 1);
                qtyOrdered += 1;
                i += 1;
            }
        }
    }

    public void addDigitalVideoDisc(hust.soict.dsai.aims.disc.DigitalVideoDisc dvd1, hust.soict.dsai.aims.disc.DigitalVideoDisc dvd2) {
        if (qtyOrdered + 1 < MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = dvd1;
            itemsOrdered[qtyOrdered + 1] = dvd2;
            System.out.println("The DVDs have been added successfully.");
        } else if (qtyOrdered + 1 == MAX_NUMBERS_ORDERED) {
            itemsOrdered[qtyOrdered] = dvd1;
            System.out.println("The first dvd has been added successfully.\nCannot add the second one because the cart is full.");
        } else {
            System.out.println("Fail to add because the cart is full.");
        }
    }
    */

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].equals(disc)) {
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                qtyOrdered -= 1;
                break;
            }
        }
        System.out.println("The disc has been removed");
    }

    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    public void isMatch (int id) {
        boolean matched = false;
        for (int i = 0; i < qtyOrdered; i ++) {
            if (itemsOrdered[i].getId() == id) {
                System.out.println(itemsOrdered[i].toString());
                matched = true;
                break;
            }
        }
        if (matched == false) {
            System.out.println("No match is found");
        }
    }

    public void isMatch (String title) {
        boolean matched = false;
        for (int i = 0; i < qtyOrdered; i ++) {
            if (itemsOrdered[i].getTitle() == title) {
                System.out.println(itemsOrdered[i].toString());
                matched = true;
                break;
            }
        }
        if (matched == false) {
            System.out.println("No match is found");
        }
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < qtyOrdered; i ++) {
            System.out.print(i + 1);
            System.out.println(". " + itemsOrdered.toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("**************************************************");
    }
}



