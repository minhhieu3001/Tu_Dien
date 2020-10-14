package ALL;

import ALL.Model.Dictionary;
import ALL.Model.DictionaryManagement;
import ALL.View.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Dictionary wordlist = new Dictionary();

    public MainApp() throws IOException {
        DictionaryManagement.readDatabase(wordlist);
    }
    public Dictionary getWordlist() {
        return wordlist;
    }
    @Override
    public void start(Stage primaryStage)  {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EV DICTIONARY");
        this.primaryStage.getIcons().add(new Image("https://i.imgur.com/87aBBVG.png"));

        initRootLayout();

        showPersonOverview();

        primaryStage.show();
        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });


    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("View\\RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("View\\Content.fxml"));
            StackPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            Controller control = loader.getController();
            control.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
