package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Component.CENTER_ALIGNMENT;

public class MediaStore extends JPanel{
    private Cart cart;
    private Media media;
    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(new AddToCartButtonListener());
        container.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new PlayButtonListener());
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private class AddToCartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cart.addMedia(media);
        }
    }

    private class PlayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog playDialog = new JDialog();
            playDialog.setTitle("Playing Media");
            playDialog.setSize(300, 200);
            playDialog.setLayout(new FlowLayout());
            playDialog.add(new JLabel("Playing: " + media.getTitle()));
            playDialog.setVisible(true);
            try {
                ((Playable) media).play();
            } catch (PlayerException ex) {
                ex.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Illegal Length");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Length of item is less than or equal to 0");
                errorAlert.showAndWait();
            }
        }
    }
}
















