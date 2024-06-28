package hust.soict.dsai.aims.store;

import hust.soict.dsai.aims.media.Media;
import java.util.ArrayList;
import java.util.Scanner;

import static hust.soict.dsai.aims.Aims.getValidFloatInput;
import static hust.soict.dsai.aims.Aims.getValidIntegerInput;
import static hust.soict.dsai.aims.media.Book.createBook;
import static hust.soict.dsai.aims.media.CompactDisc.createCD;
import static hust.soict.dsai.aims.media.DigitalVideoDisc.createDVD;

public class Store {
    private String storeName;
    private int maxNumberOfItems;
    private ArrayList<Media> itemsInStore;
    private static int count = 0;

    public Store(String storeName, int maxNumberOfItems) {
        this.storeName = storeName;
        this.maxNumberOfItems = maxNumberOfItems;
        this.itemsInStore = new ArrayList<Media>();
    }

    public String getStoreName() {return this.storeName;}

    public ArrayList<Media> getItemsInStore() {
        return this.itemsInStore;
    }

    public void addMedia(Media newMedia) {
        boolean matched = false;
        if (this.itemsInStore.isEmpty()) {
            this.itemsInStore.add(newMedia);
            System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been added to the store.");
        } else if (this.itemsInStore.size() == this.maxNumberOfItems){
            System.out.println("Fail to add because the store is already full.");
        } else {
            for (Media oldMedia : this.itemsInStore) {
                if (newMedia.equals(oldMedia)) {
                    System.out.println("The media is already in the store.");
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                this.itemsInStore.add(newMedia);
                System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been added to the store.");
            }
        }
    }

    public void removeMedia (Media newMedia) {
        boolean matched = false;
        if (this.itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            for (Media oldMedia : this.itemsInStore) {
                if (newMedia.equals(oldMedia)) {
                    this.itemsInStore.remove(newMedia);
                    System.out.println("The " + newMedia.getClass().getSimpleName() + " \"" + newMedia.getTitle() + "\" has been removed from the store.");
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                System.out.println("Media not found.");
            }
        }
    }

    public void print() {
        System.out.println("********************************");
        System.out.println("Items In " + this.storeName + ":");
        for (Media media: this.itemsInStore) {
            System.out.println("- " + media.getTitle());
        }
        System.out.println("********************************");
    }

    public void updateStoreMenu(Scanner scanner) {
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("Options: ");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("Please choose a number: 0-1-2");
            System.out.println("--------------------------------");
            int updStoChoice = getValidIntegerInput(scanner);

            switch (updStoChoice) {
                case 1:
                    addMediaMenu(scanner);
                    break;
                case 2:
                    removeMediaMenu(scanner);
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    public Media getMedia(String title) {
        int matched = -1;
        for (Media media: this.itemsInStore) {
            if (media.getTitle() == title) {
                matched = this.itemsInStore.indexOf(media);
                break;
            }
        }
        if (matched == -1) {
            return null;
        } else {
            return this.itemsInStore.get(matched);
        }
    }

    public void addMediaMenu(Scanner scanner) {
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("Choose media's type: ");
            System.out.println("1. Book");
            System.out.println("2. Compact Disc");
            System.out.println("3. Digital Video Disc");
            System.out.println("0. Back");
            System.out.println("Please choose a number: 0-1-2-3");
            System.out.println("--------------------------------");
            int choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    this.addMedia(createBook(scanner));
                    break;
                case 2:
                    this.addMedia(createCD(scanner));
                    break;
                case 3:
                    this.addMedia(createDVD(scanner));
                    break;
                case 0:
                    System.out.println("Returning to the update store menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    public void removeMediaMenu(Scanner scanner) {
        while (true) {
            if (this.itemsInStore.isEmpty()) {
                System.out.println("The store is empty.");
                return;
            } else {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                boolean matched = false;
                for (Media oldMedia: this.itemsInStore) {
                    if (title.equals(oldMedia.getTitle())) {
                        this.itemsInStore.remove(oldMedia);
                        System.out.println("The " + oldMedia.getClass().getSimpleName() + " \"" + oldMedia.getTitle() + "\" has been removed from the store.");
                        matched = true;
                        break;
                    }
                }
                if (matched == false) {
                    System.out.println("Media not found.");
                }
                System.out.print("Try again? (yes/no): ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }


}
