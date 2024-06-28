package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

public class CartScreenController {
    private Cart cart;
    private Store store;
    private FilteredList<Media> filteredList;
    private CartScreen cartScreen;

    @FXML private RadioButton radioBtnFilterId;
    @FXML private RadioButton radioBtnFilterTitle;
    @FXML private TableView<Media> tblMedia;
    @FXML private TextField tfFilter;
    @FXML private TableColumn<Media, String> colMediaTitle;
    @FXML private TableColumn<Media, String> colMediaCategory;
    @FXML private TableColumn<Media, Float> colMediaCost;
    @FXML private Button btnPlay;
    @FXML private Button btnRemove;
    @FXML private Label lblTotalCost;

    public CartScreenController() {

    }
    public void setCartAndStore(Cart cart, Store store, CartScreen cartScreen) {
        this.cart = cart;
        this.store = store;
        this.cartScreen = cartScreen;
        if (this.cart != null) {
            ObservableList<Media> observableItemList = FXCollections.observableArrayList(this.cart.getItemsOrdered());
            filteredList = new FilteredList<>(observableItemList, p -> true);
            tblMedia.setItems(filteredList);
            updateTotalCost();
        }
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                }
        );

        tfFilter.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        showFilteredMedia(newValue);
                    }
                }
        );
    }

    private void updateButtonBar (Media media) {
        btnRemove.setVisible(true);
        if(media instanceof Playable) {btnPlay.setVisible(true);}
        else {btnPlay.setVisible(false);}
    }

    private void showFilteredMedia(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            filteredList.setPredicate(null);
        } else {
            Predicate<Media> filterPredicate = media -> {
                if (radioBtnFilterId.isSelected()) {
                    return String.valueOf(media.getId()).contains(filterText);
                } else if (radioBtnFilterTitle.isSelected()) {
                    return media.getTitle() != null && media.getTitle().toLowerCase().contains(filterText.toLowerCase());
                }
                return false;
            };
            filteredList.setPredicate(filterPredicate);
        }
    }

    @FXML
    private void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            ObservableList<Media> observableItemList = FXCollections.observableArrayList(this.cart.getItemsOrdered());
            filteredList = new FilteredList<>(observableItemList, p -> true);
            tblMedia.setItems(filteredList);
            updateTotalCost();
        }
    }

    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        if (cart == null || cart.getItemsOrdered().isEmpty()) {
            System.out.println("The cart is empty.");
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setTitle("Order Placed");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Your cart is empty!");
            confirmation.showAndWait();
        } else {
            System.out.println("Placed order successfully!");
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setTitle("Order Placed");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Your order has been placed successfully!");
            confirmation.showAndWait();

            cart.clearMedia();
            updateTotalCost();
            ObservableList<Media> observableItemList = FXCollections.observableArrayList(this.cart.getItemsOrdered());
            filteredList = new FilteredList<>(observableItemList, p -> true);
            tblMedia.setItems(filteredList);
        }
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        JDialog playDialog = new JDialog();
        playDialog.setTitle("Playing Media");
        playDialog.setSize(300, 200);
        playDialog.setLayout(new FlowLayout());
        playDialog.add(new JLabel("Playing: " + media.getTitle()));
        playDialog.setVisible(true);
        try {
            ((Playable) media).play();
        } catch (PlayerException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Illegal Length");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Length of item is less than or equal to 0");
            errorAlert.showAndWait();
        }
    }

    private void updateTotalCost() {
        if (cart == null || cart.getItemsOrdered().isEmpty()) {
            lblTotalCost.setText("0 $");
        } else {
            float totalCost = 0;
            for (Media media : this.cart.getItemsOrdered()) {
                totalCost += media.getCost();
            }
            lblTotalCost.setText(String.format("%.2f $", totalCost));
        }
    }

    @FXML
    private void btnViewStoreScreenPressed(ActionEvent event) {
        this.cartScreen.setVisible(false);
        new StoreScreen(this.store, this.cart);
    }

    @FXML
    private void menuAddBookPressed(ActionEvent event) {
        this.cartScreen.setVisible(false);
        new AddBookToStoreScreen(this.store, this.cart);
    }

    @FXML
    private void menuAddDVDPressed(ActionEvent event) {
        this.cartScreen.setVisible(false);
        new AddDigitalVideoDiscToStoreScreen(this.store, this.cart);
    }

    @FXML
    private void menuAddCDPressed(ActionEvent event) {
        this.cartScreen.setVisible(false);
        new AddCompactDiscToStoreScreen(this.store, this.cart);
    }
}
























