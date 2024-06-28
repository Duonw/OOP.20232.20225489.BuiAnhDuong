package hust.soict.dsai.aims.media;

import java.util.*;

import static hust.soict.dsai.aims.Aims.getValidFloatInput;

public class Book extends Media{
    private List<String> authors = new ArrayList<String>();

    public List<String> getAuthors() {return authors;}

    public Book(String title) {
        super(title);
    }
    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }
    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        this.authors = authors;
    }

    public void addAuthor(String authorName) {
        List<String> listAuthors = this.getAuthors();
        boolean matched = false;
        if (!listAuthors.isEmpty()) {
            for (int i = 0; i < listAuthors.size(); i ++) {
                if (authorName.equals(listAuthors.get(i))) {
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                listAuthors.add(authorName);
                System.out.println(authorName + " has been added to the list of authors.");
            } else {
                System.out.println(authorName + " is already in the authors' list.");
            }
        } else {
            listAuthors.add(authorName);
            System.out.println(authorName + " has been added to the list of authors.");
        }
    }


    public void removeAuthor(String authorName) {
        List<String> listAuthors = this.getAuthors();
        boolean matched = false;
        if (!listAuthors.isEmpty()) {
            for (int i = 0; i <listAuthors.size(); i ++) {
                if (authorName.equals(listAuthors.get(i))) {
                    for (int j = i; j < listAuthors.size() - 1; j ++) {
                        listAuthors.set(j, listAuthors.get(j + 1));
                    }
                    listAuthors.remove(listAuthors.size() - 1);
                    System.out.println(authorName + " has been removed from the authors' list.");
                    matched = true;
                    break;
                }
            }
            if (matched == false) {
                System.out.println("Author not found.");
            }
        } else {
            System.out.println("The authors' list is already empty.");
        }
    }

    public String toString() {
        return ("Book - " + this.getId() + " - " + this.getTitle() + " - " + this.getCategory()
                + " - " + this.getAuthors() + ": " + this.getCost() + " $");
    }

    public static Book createBook(Scanner scanner) {
        while (true) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            List<String> authors = new ArrayList<String>();
            System.out.println("Enter authors (type 'done' to finish):");
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("done")) {
                    break;
                }
                authors.add(input);
            }
            System.out.print("Enter cost: ");
            float cost = getValidFloatInput(scanner);

            return new Book(title, category, cost, authors);
        }
    }
}
