package hust.soict.dsai.aims.media;
import java.util.Comparator;
public class ComparatorForDVD implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        if (m1 instanceof DigitalVideoDisc && m2 instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd1 = (DigitalVideoDisc) m1;
            DigitalVideoDisc dvd2 = (DigitalVideoDisc) m2;
            if (dvd1.getTitle().compareTo(dvd2.getTitle()) == 0) {
                if (dvd1.getLength() > dvd2.getLength()) {
                    return -1;
                } else if (dvd1.getLength() < dvd2.getLength()) {
                    return 1;
                } else {
                    if (dvd1.getCost() > dvd2.getCost()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            } else {return dvd1.getTitle().compareTo(dvd2.getTitle());}
        } else {
            if (m1.getTitle().compareTo(m2.getTitle()) == 0) {
                if (m1.getCost() > m2.getCost()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {return m1.getTitle().compareTo(m2.getTitle());}
        }
    }
}
