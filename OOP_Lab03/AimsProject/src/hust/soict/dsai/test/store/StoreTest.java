package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        //Create a new store
        Store store_1 = new Store("John's hust.soict.dsai.aims.store.Store", 100);

        //Create new dvd objects
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);

        //Test the addDVD method
        store_1.addMedia(dvd1);
        store_1.addMedia(dvd2);
        store_1.addMedia(dvd3);
        //store_1.print();

        //Test the removeDVD method
        System.out.println("Result after remove dvd2:");
        store_1.removeMedia(dvd2);
        //store_1.print();
    }
}
