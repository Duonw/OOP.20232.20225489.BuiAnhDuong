package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.store.Store;

import java.util.Collections;
import java.util.Scanner;

import static java.util.Collections.*;

public class Aims {
    public static void showMenu() {
        System.out.println("--------------------------------");
        System.out.println("AIMS: ");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("Please choose a number: 0-1-2-3");
        System.out.println("--------------------------------");
    }

    public static void storeMenu(Store store, Cart cart, Scanner scanner) {
        while (true) {
            System.out.println("--------------------------------");
            System.out.println("Option: ");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("Please choose a number: 0-1-2-3-4");
            System.out.println("--------------------------------");
            int subChoice = getValidIntegerInput(scanner);

            switch (subChoice) {
                case 1:
                    mediaDetailMenu(store, cart, scanner);
                    break;
                case 2:
                    while (true) {
                        if (store.getItemsInStore().isEmpty()) {
                            System.out.println("The store is empty.");
                            break;
                        } else {
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            boolean matched = false;
                            for (Media media: store.getItemsInStore()) {
                                if (title.equals(media.getTitle())) {
                                    cart.addMedia(media);
                                    System.out.println("Number of items in the cart: " + cart.getItemsOrdered().size());
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
                    break;
                case 3:
                    while (true) {
                        if (store.getItemsInStore().isEmpty()) {
                            System.out.println("The store is empty.");
                            break;
                        } else {
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            boolean matched = false;
                            for (Media media: store.getItemsInStore()) {
                                if (title.equals(media.getTitle()) && media instanceof Playable) {
                                    Playable newMedia = (Playable) media;
                                    newMedia.play();
                                    matched = true;
                                    break;
                                } else if (title.equals(media.getTitle()) && !(media instanceof Playable)) {
                                    System.out.println("Media cannot be played.");
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
                    break;
                case 4:
                    for (Media media: cart.getItemsOrdered()) {
                        System.out.println(media.toString());
                    }
                    cartMenu(cart, scanner);
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
            }
        }
    }

    public static void mediaDetailMenu(Store store, Cart cart, Scanner scanner) {
        while (true) {
            if (store.getItemsInStore().isEmpty()) {
                System.out.println("The store is empty.");
                return;
            } else {
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                boolean matched = false;
                Media matchedMedia = null;
                for (Media media: store.getItemsInStore()) {
                    if (title.equals(media.getTitle())) {
                        System.out.println(media.toString());
                        matched = true;
                        matchedMedia = media;
                        break;
                    }
                }
                if (matched == false) {
                    System.out.println("Media not found. Please try again!");
                    continue;
                }
                while (true) {
                    System.out.println("--------------------------------");
                    System.out.println("Option: ");
                    System.out.println("1. Add to cart");
                    System.out.println("2. Play");
                    System.out.println("0. Back");
                    System.out.println("Please choose a number: 0-1-2");
                    System.out.println("--------------------------------");
                    int mediaChoice = getValidIntegerInput(scanner);

                    switch (mediaChoice) {
                        case 1:
                            cart.addMedia(matchedMedia);
                            System.out.println("Number of items in the cart: " + cart.getItemsOrdered().size());
                            break;
                        case 2:
                            if (matchedMedia instanceof Playable) {
                                Playable newMedia = (Playable) matchedMedia;
                                newMedia.play();
                            } else {
                                System.out.println("Media cannot be played.");
                            }
                            break;
                        case 0:
                            System.out.println("Returning to the store menu...");
                            return;
                        default:
                            System.out.println("Invalid input. Please enter a valid option.");
                    }
                }
            }
        }
    }

    public static void cartMenu(Cart cart, Scanner scanner) {
        if (cart.getItemsOrdered().isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        } else {
            while (true) {
                System.out.println("--------------------------------");
                System.out.println("Options: ");
                System.out.println("1. Filter medias in cart");
                System.out.println("2. Sort media in cart");
                System.out.println("3. Remove media from cart");
                System.out.println("4. Play a media");
                System.out.println("5. Place order");
                System.out.println("0. Back");
                System.out.println("Please choose a number: 0-1-2-3-4-5");
                System.out.println("--------------------------------");
                int cartChoice = getValidIntegerInput(scanner);

                switch (cartChoice) {
                    case 1:
                        while (true) {
                            System.out.println("--------------------------------");
                            System.out.println("Filtering options: ");
                            System.out.println("1. By id");
                            System.out.println("2. By title");
                            System.out.println("0. Back");
                            System.out.println("Please choose a number: 0-1-2");
                            System.out.println("--------------------------------");
                            int choice = getValidIntegerInput(scanner);

                            switch (choice) {
                                case 1:
                                    while (true) {
                                        System.out.print("Enter id: ");
                                        int id = getValidIntegerInput(scanner);
                                        boolean matched = false;
                                        for (Media media : cart.getItemsOrdered()) {
                                            if (id == media.getId()) {
                                                System.out.println(media.toString());
                                                matched = true;
                                                break;
                                            }
                                        }
                                        if (matched == false) {
                                            System.out.println("No media found.");
                                        }
                                        System.out.println("Try again? (yes/no): ");
                                        String response = scanner.nextLine();
                                        if (!response.equalsIgnoreCase("yes")) {
                                            break;
                                        }
                                    }
                                    break;
                                case 2:
                                    while (true) {
                                        System.out.print("Enter title: ");
                                        String title = scanner.nextLine();
                                        boolean matched = false;
                                        for (Media media : cart.getItemsOrdered()) {
                                            if (title.equals(media.getTitle())) {
                                                System.out.println(media.toString());
                                                matched = true;
                                                break;
                                            }
                                        }
                                        if (matched == false) {
                                            System.out.println("Media not found.");
                                        }
                                        System.out.println("Try again? (yes/no): ");
                                        String response = scanner.nextLine();
                                        if (!response.equalsIgnoreCase("yes")) {
                                            break;
                                        }
                                    }
                                    break;
                                case 0:
                                    return;
                                default:
                                    System.out.println("Invalid input. Please try again!");
                            }
                        }
                    case 2: // Sort media
                        while (true) {
                            System.out.println("--------------------------------");
                            System.out.println("Sorting options: ");
                            System.out.println("1. By title then by cost");
                            System.out.println("2. By cost then by title");
                            System.out.println("0. Back");
                            System.out.println("Please choose a number: 0-1-2");
                            System.out.println("--------------------------------");
                            int choice = getValidIntegerInput(scanner);

                            switch (choice) {
                                case 1:
                                    Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_TITLE_COST);
                                    System.out.println("The cart after sorting: ");
                                    for (Media media: cart.getItemsOrdered()) {
                                        System.out.println(media.toString());
                                    }
                                    break;
                                case 2:
                                    Collections.sort(cart.getItemsOrdered(), Media.COMPARE_BY_COST_TITLE);
                                    System.out.println("The cart after sorting: ");
                                    for (Media media: cart.getItemsOrdered()) {
                                        System.out.println(media.toString());
                                    }
                                    break;
                                case 0:
                                    return;
                                default:
                                    System.out.println("Invalid input. Please try again!");
                            }
                        }
                    case 3: // Remove media from cart
                        while (true) {
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            boolean matched = false;
                            for (Media oldMedia : cart.getItemsOrdered()) {
                                if (title.equals(oldMedia.getTitle())) {
                                    cart.removeMedia(oldMedia);
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
                        break;
                    case 4: // Play a media
                        while (true) {
                            System.out.print("Enter title: ");
                            String title = scanner.nextLine();
                            boolean matched = false;
                            for (Media media : cart.getItemsOrdered()) {
                                if (title.equals(media.getTitle()) && media instanceof Playable) {
                                    Playable newMedia = (Playable) media;
                                    newMedia.play();
                                    matched = true;
                                    break;
                                } else if (title.equals(media.getTitle()) && !(media instanceof Playable)) {
                                    System.out.println("Media cannot be played.");
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
                        break;
                    case 5:
                        System.out.println("The order has been created successfully.");
                        cart.clearMedia();
                        break;
                    case 0:
                        System.out.println("Returning to the media menu...");
                        return;
                    default:
                        System.out.println("Invalid input. Please enter a valid option.");
                }
            }
        }

    }

    public static float getValidFloatInput(Scanner scanner) {
        while (!scanner.hasNextFloat()) {
            System.out.println("Invalid input. Please enter a float number.");
            scanner.next();
        }
        float choice = scanner.nextFloat();
        scanner.nextLine();
        return choice;
    }

    public static int getValidIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public static void main(String[] args) {
        Store store1 = new Store("Timmy's Store", 30);
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice = Aims.getValidIntegerInput(scanner);

            switch (choice) {
                case 1: // View store
                    store1.print();
                    storeMenu(store1, cart, scanner);
                    break;
                case 2: // Update store
                    store1.updateStoreMenu(scanner);
                    break;
                case 3: // See current cart
                    for (Media media: cart.getItemsOrdered()) {
                        System.out.println(media.toString());
                    }
                    cartMenu(cart, scanner);
                    break;
                case 0: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}


