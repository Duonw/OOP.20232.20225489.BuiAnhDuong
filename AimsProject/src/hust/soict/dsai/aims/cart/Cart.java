package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hust.soict.dsai.aims.media.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public ArrayList<Media> getItemsOrdered() {
        return this.itemsOrdered;
    }

    public void clearMedia() {
        this.itemsOrdered.clear();
    }

    public void addMedia(Media newMedia) {
        boolean matched = false;
        if (this.itemsOrdered.isEmpty()) {
            this.itemsOrdered.add(newMedia);
            System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been added to the cart.");
        } else if (this.itemsOrdered.size() == MAX_NUMBERS_ORDERED){
            System.out.println("Fail to add because the cart is already full.");
        } else {
            for (Media oldMedia : this.itemsOrdered) {
                if (newMedia.equals(oldMedia)) {
                    System.out.println("The media is already in the cart.");
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                this.itemsOrdered.add(newMedia);
                System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been added to the cart.");
            }
        }
    }

    public void removeMedia (Media newMedia) {
        boolean matched = false;
        if (this.itemsOrdered.isEmpty()) {
            System.out.println("The cart is already empty.");
        } else {
            for (Media oldMedia : this.itemsOrdered) {
                if (newMedia.equals(oldMedia)) {
                    this.itemsOrdered.remove(newMedia);
                    System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been removed from the cart.");
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                System.out.println("Media not found.");
            }
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : this.itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public static void main (String [] args) {
        Cart mediae = new Cart();

        //Create a new CD
        Track track1 = new Track("Home 1", 3);
        Track track2 = new Track("Home 2", 4);
        List<Track> cdTrack = new ArrayList<Track> ();
        CompactDisc cd = new CompactDisc("My home", "Pop", "FB Music",
                3, "Taylor", cdTrack, 26.2f);
        cd.addTrack(track1);
        cd.addTrack(track2);

        //Create a new DVD
        DigitalVideoDisc dvd = new DigitalVideoDisc("Aladin", "Animation",
                "BB Film", 90, 20.1f);

        //Create a new book
        List<String> authors = new ArrayList<String>();
        Book book = new Book("Habits", "Self-help", 10.3f, authors);
        book.addAuthor("John Miller");
        book.addAuthor("Bill Smith");

        //Add them to the list
        mediae.addMedia(cd);
        mediae.addMedia(dvd);
        mediae.addMedia(book);

        //Print out information of the media
        for (Media m: mediae.itemsOrdered) {
            System.out.println(m.toString());
        }
    }













    /*
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
        System.out.println("************************CART************************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < qtyOrdered; i ++) {
            System.out.print(i + 1);
            System.out.println(". " + itemsOrdered.toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("****************************************************");
    }*/
}



