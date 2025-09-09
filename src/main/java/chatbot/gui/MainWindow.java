package chatbot.gui;

import chatbot.ui.B33pbop;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private B33pbop b33pbop;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image b33pbopImage = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the B33pbop instance */
    public void setDuke(B33pbop bb) {
        b33pbop = bb;

        if (b33pbop != null) {
            dialogContainer.getChildren().add(DialogBox.getBotDialog(b33pbop.getGreeting(), b33pbopImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = b33pbop.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, b33pbopImage)
        );

        userInput.clear();
    }
}
