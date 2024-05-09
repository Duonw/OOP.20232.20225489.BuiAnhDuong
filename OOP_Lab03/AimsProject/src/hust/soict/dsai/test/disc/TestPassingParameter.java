package hust.soict.dsai.test.disc;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        //TODO Auto-generated method stub
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        swap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
    }

    /*
    public static void swap (Object o1, Object o2) {
        hust.soict.dsai.aims.disc.DigitalVideoDisc dvd1 = (hust.soict.dsai.aims.disc.DigitalVideoDisc) o1;
        hust.soict.dsai.aims.disc.DigitalVideoDisc dvd2 = (hust.soict.dsai.aims.disc.DigitalVideoDisc) o2;

        String title_tmp = dvd1.getTitle();
        dvd1.setTitle(dvd2.getTitle());
        dvd2.setTitle(title_tmp);

        String category_tmp = dvd1.getCategory();
        dvd1.setCategory(dvd2.getCategory());
        dvd2.setCategory(category_tmp);

        String director_tmp = dvd1.getDirector();
        dvd1.setDirector(dvd2.getDirector());
        dvd2.setDirector(director_tmp);

        int length_tmp = dvd1.getLength();
        dvd1.setLength(dvd2.getLength());
        dvd2.setLength(length_tmp);

        float cost_tmp = dvd1.getCost();
        dvd1.setCost(dvd2.getCost());
        dvd2.setCost(cost_tmp);
    }
     */

    public static void swap(Object o1, Object o2) {
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}
