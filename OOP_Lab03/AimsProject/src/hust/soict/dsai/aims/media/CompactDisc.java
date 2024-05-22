package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static hust.soict.dsai.aims.Aims.getValidFloatInput;
import static hust.soict.dsai.aims.Aims.getValidIntegerInput;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private List<Track> tracks = new ArrayList<Track>();

    //Create getter method for artist
    public String getArtist() {return artist;}

    //Create constructors
    public CompactDisc(String title) {
        super(title);
    }
    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
    }
    public CompactDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }
    public CompactDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }
    public CompactDisc(String title, String category, String director, int length, String artist, List<Track> tracks, float cost) {
        super(title, category, director, length, cost);
        this.artist = artist;
        this.tracks = tracks;
    }

    //Create addTrack() method
    public void addTrack(Track newTrack) {
        boolean matched = false;
        for (Track oldTrack : this.tracks) {
            if (newTrack.equals(oldTrack)) {
                matched = true;
                System.out.println("The track is already in the list.");
                break;
            }
        }
        if (matched == false) {
            this.tracks.add(newTrack);
            System.out.println(newTrack.getTitle() + " has been added to the tracks' list.");
        }
    }

    //Create removeTrack() method
    public void removeTrack(Track newTrack) {
        boolean matched = false;
        for (Track oldTrack : this.tracks) {
            if (newTrack.equals(oldTrack)) {
                this.tracks.remove(newTrack);
                System.out.println(newTrack.getTitle() + " has been removed from the tracks' list.");
                matched = true;
                break;
            }
        }
        if (matched == false) {
            System.out.println("Track not found.");
        }
    }

    //Create getLength() method
    public int getLength() {
        return this.tracks.size();
    }

    //Implement play() for CompactDisc
    public void play() {
        System.out.println("Playing CD " + this.getTitle() + " by " + this.getArtist());
        System.out.println("CD length: " + this.getLength() + " tracks");
        for (Track track : this.tracks) {
            track.play();
        }
    }

    //Create toString() method
    public String toString() {
        return ("CD - " + this.getId() + " - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getArtist()
                + " - " + this.getLength() + ": " + this.getCost() + " $");
    }

    public static CompactDisc createCD(Scanner scanner) {
        while (true) {
            System.out.println("Enter the CD's information");
            System.out.print("Enter title: ");
            String cdTitle = scanner.nextLine();
            System.out.print("Enter category: ");
            String cdCategory = scanner.nextLine();
            System.out.print("Enter director: ");
            String cdDirector = scanner.nextLine();
            System.out.print("Enter length: ");
            int cdLength = getValidIntegerInput(scanner);
            System.out.print("Enter artist: ");
            String cdArtist = scanner.nextLine();
            List<Track> tracks = new ArrayList<Track>();
            System.out.print("Enter cost: ");
            float cdCost = getValidFloatInput(scanner);
            CompactDisc cd = new CompactDisc(cdTitle, cdCategory, cdDirector, cdLength, cdArtist, tracks, cdCost);

            while (true) {
                System.out.print("Enter the title of the track: ");
                String trackTitle = scanner.nextLine();
                System.out.print("Enter the length of the track: ");
                int trackLength = getValidIntegerInput(scanner);
                Track track = new Track(trackTitle, trackLength);
                cd.addTrack(track);

                System.out.print("Do you want to add another track? (yes/no): ");
                String response = scanner.nextLine();

                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }
            }
            return cd;
        }
    }
}
