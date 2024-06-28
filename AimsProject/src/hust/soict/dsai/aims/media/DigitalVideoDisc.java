package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

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

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive");
        }
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
