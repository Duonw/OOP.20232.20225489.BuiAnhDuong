package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Store {
    private String name;
    private int maxNumber;
    private DigitalVideoDisc itemsInStore[];
    private static int count = 0;

    public Store(String name, int maxNumber) {
        this.name = name;
        this.maxNumber = maxNumber;
        this.itemsInStore = new DigitalVideoDisc[maxNumber];
    }

    public void addDVD(DigitalVideoDisc dvd) {
        if (count == this.maxNumber) {
            System.out.println("Fail to add the disc because the store is full");
        } else {
            itemsInStore[count] = dvd;
            System.out.println("The disc has been added");
            count += 1;
        }
    }

    public void removeDVD(DigitalVideoDisc dvd) {
        boolean matched = false;
        for (int i = 0; i < count; i++) {
            if (itemsInStore[i].equals(dvd)) {
                for (int j = i; j < count - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[count - 1] = null;
                count -= 1;
                matched = true;
                break;
            }
        }
        if (matched == false) {
            System.out.println("No match found");
        }
    }

    public void print() {
        System.out.println("*********************** " + this.name + " ***********************");
        System.out.println("Items In hust.soict.dsai.aims.store.Store:");
        for (int i = 0; i < count; i++) {
            System.out.print(i + 1);
            System.out.println(". " + itemsInStore[i].toString());
        }
        System.out.println("************************************************************");
    }
}



















