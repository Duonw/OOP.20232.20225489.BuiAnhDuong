package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        ActionListener option = new OptionListener();
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        menu.add(smUpdateStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(option);
        menu.add(viewCart);

        menu.add(new JMenuItem("View store"));

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(option);
        smUpdateStore.add(addBook);

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(option);
        smUpdateStore.add(addCD);

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(option);
        smUpdateStore.add(addDVD);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        ActionListener option = new OptionListener();
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.addActionListener(option);
        cart.setPreferredSize(new Dimension(100,50));
        cart.setMaximumSize(new Dimension(100,50));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter(Cart cart) {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        int numberOfItems = mediaInStore.size();

        if (numberOfItems == 0) {
            center.add(new JLabel("No media available in store."));
        } else {
            for (int i = 0; i < 9; i++) {
                if (i < numberOfItems) {
                    MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
                    center.add(cell);
                } else {
                    center.add(new JPanel());
                }
            }
        }

        return center;
    }

    private class OptionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();

            if (button.equals("View cart")) {
                setVisible(false);
                CartScreen newScreen = new CartScreen(cart, store);
            } else if (button.equals("Add Book")) {
                setVisible(false);
                new AddBookToStoreScreen(store, cart);
            } else if (button.equals("Add DVD")) {
                setVisible(false);
                new AddDigitalVideoDiscToStoreScreen(store, cart);
            } else if (button.equals("Add CD")) {
                setVisible(false);
                new AddCompactDiscToStoreScreen(store, cart);
            }
        }
    }

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(cart), BorderLayout.CENTER);

        setVisible(true);
        setTitle(store.getStoreName());
        setSize(1024, 768);
    }

    public static void main (String[] args) {
        Cart cart_1 = new Cart();
        Store store_1 = new Store("Tom Shop", 20);
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        Book book1 = new Book("So Lucky", "Comedy", 22.1f);
        Book book2 = new Book("Pyramid", "History", 14.52f);
        CompactDisc cd1 = new CompactDisc("Get Up", "Pop", 32.2f);
        CompactDisc cd2 = new CompactDisc("The Visitors", "Jazz", 28.95f);

        //Test the addDVD method
        store_1.addMedia(dvd1);
        store_1.addMedia(cd2);
        store_1.addMedia(dvd2);
        store_1.addMedia(book2);
        store_1.addMedia(dvd3);
        store_1.addMedia(book1);
        store_1.addMedia(cd1);
        new StoreScreen(store_1, cart_1);
    }
}













