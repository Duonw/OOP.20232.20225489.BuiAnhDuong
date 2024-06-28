package hust.soict.dsai.aims.media;

import java.util.*;

import static hust.soict.dsai.aims.Aims.getValidFloatInput;

public abstract class Media implements Comparable<Media> {
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    public static final Comparator<Media> COMPARE_FOR_DVD = new ComparatorForDVD();

    private int id;
    private String title;
    private String category;
    private float cost;
    private static int numberOfMedia = 0;

    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getCategory() {return category;}
    public float getCost() {return cost;}

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setCategory(String category) {this.category = category;}
    public void setCost(float cost) {this.cost = cost;}

    public Media(String title) {
        this.title = title;
        numberOfMedia ++;
        this.id = numberOfMedia;
    }
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        numberOfMedia ++;
        this.id = numberOfMedia;
    }
    @Override
    public boolean equals (Object obj) {
        if (obj == null || (! (obj instanceof Media))) {
            return false;
        } else {
            Media media = (Media) obj;
            return this.title.equals(media.title);
        }
    }

    @Override
    public int compareTo(Media m2) {
        if (this.getCost() < m2.getCost()) {
            return 1;
        } else if (this.getCost() > m2.getCost()) {
            return -1;
        } else {
            return this.getTitle().compareTo(m2.getTitle());
        }
    }

    public void createMedia(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter cost: ");
        float cost = getValidFloatInput(scanner);
    }
    public static void main (String [] args) {
        List<Media> mediae = new ArrayList<Media> ();

        Track track1 = new Track("Home 1", 3);
        Track track2 = new Track("Home 2", 4);
        List<Track> cdTrack = new ArrayList<Track> ();
        CompactDisc cd = new CompactDisc("My home", "Pop", "FB Music", 3, "Taylor", cdTrack, 26.2f);
        cd.addTrack(track1);
        cd.addTrack(track2);
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Aladin", "Animation", "BB Film", 90, 20.1f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Aion", "Action", "DD Film", 101, 12.4f);
        List<String> authors = new ArrayList<String>();
        Book book = new Book("Habits", "Self-help", 20.1f, authors);
        book.addAuthor("John Miller");
        book.addAuthor("Bill Smith");

        mediae.add(cd);
        mediae.add(dvd1);
        mediae.add(book);
        mediae.add(dvd2);

        System.out.println("\nThe list before sorting:");
        for (Media m: mediae) {System.out.println(m.toString());}

        System.out.println("\nThe list after using special comparator for DVD:");
        Collections.sort(mediae, Media.COMPARE_FOR_DVD);
        for (Media m: mediae) {System.out.println(m.toString());}

        //System.out.println("\nThe list after sorting by cost then by title:");
        //Collections.sort(mediae, Media.COMPARE_BY_COST_TITLE);
        //Collections.sort(mediae);
        //for (Media m: mediae) {System.out.println(m.toString());}

        //System.out.println("\nThe list after sorting by title then by cost:");
        //Collections.sort(mediae, Media.COMPARE_BY_TITLE_COST);
        //for (Media m: mediae) {System.out.println(m.toString());}
    }
}






















