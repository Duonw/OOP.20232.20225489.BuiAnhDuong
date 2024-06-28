package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

import java.util.Objects;

public class Track implements Playable{
    private String title;
    private int length;

    public String getTitle() {return title;}
    public int getLength() {return length;}

    public void setTitle(String title) {this.title = title;}
    public void setLength(int length) {this.length = length;}

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive");
        }
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null || (! (obj instanceof Track))) {
            return false;
        } else {
            Track track = (Track) obj;
            return ((this.title.equals(track.title)) && (Objects.equals(this.length, track.length)));
        }
    }
}



















