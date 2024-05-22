package hust.soict.dsai.aims.media;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static hust.soict.dsai.aims.Aims.getValidFloatInput;
import static hust.soict.dsai.aims.Aims.getValidIntegerInput;

public class DigitalVideoDisc extends Disc implements Playable{
    public DigitalVideoDisc(String title) {
        super(title);
    }
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public String toString() {
        return ("DVD - " + this.getId() + " - " + this.getTitle() + " - " + this.getCategory()
                + " - " + this.getLength() + ": " + this.getCost() + " $");
    }

    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }

    public static DigitalVideoDisc createDVD(Scanner scanner) {
        while (true) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter director: ");
            String director = scanner.nextLine();
            System.out.print("Enter length: ");
            int length = getValidIntegerInput(scanner);
            System.out.print("Enter cost: ");
            float cost = getValidFloatInput(scanner);

            return new DigitalVideoDisc(title, category, director, length, cost);
        }
    }
}
