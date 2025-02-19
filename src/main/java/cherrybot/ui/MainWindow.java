package cherrybot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    private Button sendButton;

    private CherryBot cherryBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image cherrybotImage = new Image(this.getClass().getResourceAsStream("/images/cherrybot_bot.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "⋆˚࿔ CHERRYBOT 𝜗𝜚˚⋆\n" + "Hi! I'm CherryBot\n" + "What can I do for you?\n\n" +
                 "CherryBot is NOT case sensitive! Have fun making lists!";
        dialogContainer.getChildren().addAll(DialogBox.getGreeting(greeting, cherrybotImage));
    }

    /** Injects the Duke instance */
    public void setCherryBot(CherryBot c) {
        cherryBot = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cherryBot.getResponse(input);
        String commandType = cherryBot.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCherryBotDialog(response, cherrybotImage, commandType)
        );
        userInput.clear();
    }
}
