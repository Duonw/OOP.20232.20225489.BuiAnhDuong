package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddDVDController {
    @FXML protected MenuBar menuBar;
    @FXML protected Menu menuOptions;
    @FXML protected Menu updateStore;
    @FXML protected MenuItem viewStore;
    @FXML protected MenuItem viewCart;
    @FXML protected MenuItem addBook;
    @FXML protected MenuItem addCD;
    @FXML protected MenuItem addDVD;
    @FXML protected TextField tfTitle;
    @FXML protected TextField tfCategory;
    @FXML protected TextField tfCost;
    @FXML protected TextField tfDirector;
    @FXML protected TextField tfLength;
    @FXML protected Button btnFinish;
    @FXML protected Label header;

    private Store store;
    private Cart cart;
    private AddDigitalVideoDiscToStoreScreen dvdToStoreScreen;

    public AddDVDController() {
    }

    public void setCartAndStore(Store store, Cart cart, AddDigitalVideoDiscToStoreScreen dvdToStoreScreen) {
        this.store = store;
        this.cart = cart;
        this.dvdToStoreScreen = dvdToStoreScreen;
    }

    @FXML
    public void initialize() {
        setupMenu();
        btnFinish.setOnAction(event -> {
            addItemToStore();
        });
    }

    public void setupMenu() {
        viewStore.setOnAction(event -> {
            this.dvdToStoreScreen.setVisible(false);
            new StoreScreen(this.store, this.cart);
        });

        viewCart.setOnAction(event -> {
            this.dvdToStoreScreen.setVisible(false);
            new CartScreen(cart, store);
        });

        addBook.setOnAction(event -> {
            this.dvdToStoreScreen.setVisible(false);
            new AddBookToStoreScreen(store, cart);
        });

        addDVD.setOnAction(event -> {
            this.dvdToStoreScreen.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        });

        addCD.setOnAction(event -> {
            this.dvdToStoreScreen.setVisible(false);
            new AddCompactDiscToStoreScreen(store, cart);
        });
    }

    public void addItemToStore() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());
            String director = tfDirector.getText().trim();
            int length = Integer.parseInt(tfLength.getText().trim());

            if (title.isEmpty() || category.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Missing value!");
                alert.showAndWait();
                return;
            }

            DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
            store.addMedia(dvd);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add DVD");
            alert.setHeaderText(null);
            alert.setContentText("DVD added to store successfully!");
            alert.showAndWait();
            this.dvdToStoreScreen.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number.");
            alert.showAndWait();
        }
    }
}
