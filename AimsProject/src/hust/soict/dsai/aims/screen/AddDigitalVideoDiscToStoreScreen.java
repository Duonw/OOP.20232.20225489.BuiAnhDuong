package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;

public class AddDigitalVideoDiscToStoreScreen extends JFrame {
    private Cart cart;
    private Store store;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super();

        this.cart = cart;
        this.store = store;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Add DVD to Store");
        this.setVisible(true);
        this.setSize(500, 500);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dvd.fxml"));
                    Parent root = loader.load();
                    AddDVDController controller = loader.getController();
                    controller.setCartAndStore(store, cart, AddDigitalVideoDiscToStoreScreen.this);
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
