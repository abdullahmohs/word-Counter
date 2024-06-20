package word.counter;

import java.util.ArrayDeque;
import java.util.Queue;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WordCounter extends Application {

    private Stage stage = new Stage();
    private Pane root = new Pane();
    private Scene scene = new Scene(root, 500, 500);
    private TextArea textArea = new TextArea();
    private Label label = new Label("Word");
    private TextField result = new TextField();

    @Override
    public void start(Stage primaryStage) {

        stage.setTitle("Word Counter");
        stage.setResizable(false);
        stage.setScene(scene);
        textArea.setPromptText("Write Here");
        Platform.runLater(() -> textArea.requestFocus());
        result.setText("0");
        textArea.setLayoutX(25);
        textArea.setLayoutY(15);
        result.setLayoutX(200);
        result.setLayoutY(450);
        label.setLayoutX(140);
        label.setLayoutY(450);
        result.setAlignment(Pos.CENTER);

        try {
            textArea.setStyle(" -fx-pref-width: 450px;\n"
                    + "    -fx-pref-height: 400px;\n"
                    + "    -fx-min-width: 450px;\n"
                    + "    -fx-min-height: 400px;\n"
                    + "    -fx-max-width: 450px;\n"
                    + "    -fx-max-height: 400px;\n"
                    + "    -fx-font-size: 13px;\n"
                    + "    -fx-font-weight:bold\n"
            );
            result.setStyle("-fx-font-size: 13px; -fx-font-weight:bold;"
                    + "-fx-pref-width: 80px;\n"
                    + "-fx-pref-height: 25px;\n"
                    + "    -fx-min-width: 80px;\n"
                    + "    -fx-min-height: 25px;\n"
                    + "    -fx-max-width: 80px;\n"
                    + "    -fx-max-height: 25px;\n"
                    + "    -fx-font-size: 13px;\n");
            label.setStyle("-fx-font-size: 15px; -fx-font-weight:bold;");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    wordCounter();
                }
            });
        });
        root.getChildren()
                .addAll(textArea, result, label);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void wordCounter() {
        Queue queue = new ArrayDeque<>();
        int startIndex = -1, endIndex = -1;
        String text = textArea.getText();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ' || i == 0) {
                startIndex = i;
                for (int j = i + 1; j < text.length(); j++) {
                    if (text.charAt(j) == ' ' || j == text.length() - 1) {
                        endIndex = j;
                        queue.offer((text.substring(startIndex, endIndex)));
                        break;
                    }
                }
            }
        }
        result.setText(queue.size() + "");
        queue.clear();
    }
}
