package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UpdateStoreController {
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

    protected Stage stage;
    protected Store store;
    protected Cart cart;

    @FXML
    public void initialize() {
        setupMenu();
        btnFinish.setOnAction(event -> {
            addItemToStore();
            stage.close();
        });
    }

    public void setupMenu() {
        viewStore.setOnAction(event -> {
            stage.close();
            StoreScreen newScreen = new StoreScreen(store, cart);
            newScreen.setVisible(true);
        });

        viewCart.setOnAction(event -> {
            stage.close();
            CartScreen newScreen = new CartScreen(cart, store);
            newScreen.setVisible(true);
        });

        addBook.setOnAction(event -> {
            Platform.runLater(() -> {
                AddBookToStoreScreen newScreen = new AddBookToStoreScreen(store, cart);
                newScreen.show();
            });
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
                }
            }

            Book book = new Book(title, category, cost, authors);
            store.addMedia(book);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Book");
            alert.setHeaderText(null);
            alert.setContentText("Book added to store successfully!");
            alert.showAndWait();
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setStore(Store store) {
        this.store = store;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void show() {
        stage.show();
    }
    private void addAuthorField(VBox authorBoxContainer) {
        HBox authorBox = new HBox(10);
        TextField authorField = new TextField();
        authorField.setPromptText("Enter author's name");
        Button removeAuthorButton = new Button("Remove");

        removeAuthorButton.setOnAction(event -> {
            authorBoxContainer.getChildren().remove(authorBox);
            authorFields.remove(authorField);
        });

        authorBox.getChildren().addAll(authorField, removeAuthorButton);
        authorBoxContainer.getChildren().add(authorBox);
        authorFields.add(authorField);
    }
}
