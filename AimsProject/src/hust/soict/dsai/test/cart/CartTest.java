package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;

import java.util.ArrayList;

public class CartTest {
    public static void main(String[] args) {
        //Create a new cart
        Cart cart = new Cart();

        //Create new dvd objects and add them to the cart
        Book dvd1 = new Book("The Lion King",
                "Animation", 19.2f);
        cart.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        cart.addMedia(dvd3);

        //Test the print method
        /*
        cart.print();

        //To-do: Test the search methods here
        cart.isMatch(1);
        cart.isMatch(2);
        cart.isMatch(3);
        cart.isMatch(4);

        cart.isMatch("The Lion King");
        cart.isMatch("Star Wars");
        cart.isMatch("Aladin");
        cart.isMatch("My Disc");*/
    }
}