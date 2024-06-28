package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class AddCompactDiscController {
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
    @FXML protected TextField tfArtist;
    @FXML protected Button btnFinish;
    @FXML protected Label header;
    @FXML protected VBox tracksBoxContainer;

    private List<TextField> titleTrackFields = new ArrayList<>();
    private List<TextField> lengthTrackFields = new ArrayList<>();
    private Store store;
    private Cart cart;
    private AddCompactDiscToStoreScreen cdToStoreScreen;

    public AddCompactDiscController() {
    }

    public void setCartAndStore(Store store, Cart cart, AddCompactDiscToStoreScreen cdToStoreScreen) {
        this.store = store;
        this.cart = cart;
        this.cdToStoreScreen = cdToStoreScreen;
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
            this.cdToStoreScreen.setVisible(false);
            new StoreScreen(this.store, this.cart);
        });

        viewCart.setOnAction(event -> {
            this.cdToStoreScreen.setVisible(false);
            new CartScreen(cart, store);
        });

        addBook.setOnAction(event -> {
            this.cdToStoreScreen.setVisible(false);
            new AddBookToStoreScreen(store, cart);
        });

        addDVD.setOnAction(event -> {
            this.cdToStoreScreen.setVisible(false);
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        });

        addCD.setOnAction(event -> {
            this.cdToStoreScreen.setVisible(false);
            new AddCompactDiscToStoreScreen(store, cart);
        });
    }

    public void addItemToStore() {
        try {
            String title = tfTitle.getText().trim();
            String category = tfCategory.getText().trim();
            float cost = Float.parseFloat(tfCost.getText().trim());
            String director = tfDirector.getText().trim();
            int length = 0;
            String artist = tfArtist.getText().trim();
            List<Track> tracks = new ArrayList<>();

            if (title.isEmpty() || category.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Missing value!");
                alert.showAndWait();
                return;
            }

            CompactDisc cd = new CompactDisc(title, category, director, length, artist, tracks, cost);

            for (int i = 0; i < titleTrackFields.size(); i ++) {
                String titleTrack = titleTrackFields.get(i).getText().trim();
                String lengthTrackText = lengthTrackFields.get(i).getText().trim();

                if (!titleTrack.isEmpty() && !lengthTrackText.isEmpty()) {
                    try {
                        int lengthTrack = Integer.parseInt(lengthTrackText);
                        cd.addTrack(new Track(titleTrack, lengthTrack));
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid number for length.");
                        alert.showAndWait();
                        return;
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Missing value!");
                    alert.showAndWait();
                    return;
                }
            }

            store.addMedia(cd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add CD");
            alert.setHeaderText(null);
            alert.setContentText("CD added to store successfully!");
            alert.showAndWait();

            this.cdToStoreScreen.setVisible(false);
            new AddCompactDiscToStoreScreen(store, cart);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid number for cost.");
            alert.showAndWait();
        }
    }

    @FXML
    public void btnAddTrackPressed(ActionEvent event) {
        handleAddTrack(tracksBoxContainer);
    }

    private void handleAddTrack(VBox tracksBoxContainer) {
        HBox trackBox = new HBox(10);
        TextField titleTrackField = new TextField();
        TextField lengthTrackFiled = new TextField();
        titleTrackField.setPromptText("Enter track's title");
        lengthTrackFiled.setPromptText("Enter track's length");
        Button removeTrackButton = new Button("Remove");
        HBox.setMargin(titleTrackField, new Insets(10, 0, 0, 0));
        HBox.setMargin(lengthTrackFiled, new Insets(10, 0, 0, 0));
        HBox.setMargin(removeTrackButton, new Insets(10, 0, 0, 0));

        removeTrackButton.setOnAction(event -> {
            tracksBoxContainer.getChildren().remove(trackBox);
            titleTrackFields.remove(titleTrackField);
            lengthTrackFields.remove(lengthTrackFiled);
        });

        trackBox.getChildren().addAll(titleTrackField, lengthTrackFiled, removeTrackButton);
        tracksBoxContainer.getChildren().add(trackBox);
        titleTrackFields.add(titleTrackField);
        lengthTrackFields.add(lengthTrackFiled);
    }
}