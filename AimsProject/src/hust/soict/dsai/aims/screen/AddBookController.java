package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AddBookController {
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
    @FXML protected Button btnFinish;
    @FXML protected Label header;
    @FXML protected VBox authorBoxContainer;

    private List<TextField> authorFields = new ArrayList<>();
    private Store store;
    private Cart cart;
    private AddBookToStoreScreen bookToStoreScreen;

    public AddBookController() {
    }

    public void setCartAndStore(Store store, Cart cart, AddBookToStoreScreen bookToStoreScreen) {
        this.store = store;
        this.cart = cart;
        this.bookToStoreScreen = bookToStoreScreen;
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
            this.bookToStoreScreen.setVisible(false);
            new StoreScreen(this.store, this.cart);
        });

        viewCart.setOnAction(event -> {
            this.bookToStoreScreen.setVisible(false);
            new CartScreen(cart, store);
        });

        addBook.setOnAction(event -> {
            this.bookToStoreScreen.setVisible(false);
            new AddBookToStoreScreen(store, cart);
        });

        addDVD.setOnAction(event -> {
            this.bookToStoreScreen.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        });

        addCD.setOnAction(event -> {
            this.bookToStoreScreen.setVisible(false);
            new AddCompactDiscToStoreScreen(store, cart);
        });
    }

    public void addItemToStore() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());

            List<String> authors = new ArrayList<>();
            for (TextField authorField : authorFields) {
                String author = authorField.getText().trim();
                if (!author.isEmpty()) {
                    authors.add(author);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Missing value error");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter remove button if you don't want to add author anymore.");
                    alert.showAndWait();
                    return;
                }
            }

            if (title.isEmpty() || category.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Missing value!");
                alert.showAndWait();
                return;
            }

            Book book = new Book(title, category, cost, authors);
            store.addMedia(book);
            System.out.println(book.toString());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Book");
            alert.setHeaderText(null);
            alert.setContentText("Book added to store successfully!");
            alert.showAndWait();
            this.bookToStoreScreen.setVisible(false);
            new AddBookToStoreScreen(store, cart);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number for cost.");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleAddAuthor() {
        addAuthorField(authorBoxContainer);
    }

    private void addAuthorField(VBox authorBoxContainer) {
        HBox authorBox = new HBox(10);
        TextField authorField = new TextField();
        authorField.setPromptText("Enter author's name");
        Button removeAuthorButton = new Button("Remove");
        HBox.setMargin(authorField, new Insets(10, 0, 0, 0));
        HBox.setMargin(removeAuthorButton, new Insets(10, 0, 0, 0));
        removeAuthorButton.setOnAction(event -> {
            authorBoxContainer.getChildren().remove(authorBox);
            authorFields.remove(authorField);
        });

        authorBox.getChildren().addAll(authorField, removeAuthorButton);
        authorBoxContainer.getChildren().add(authorBox);
        authorFields.add(authorField);
    }
}
