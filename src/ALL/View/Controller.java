package ALL.View;

import ALL.Model.Dictionary;
import ALL.Model.DictionaryManagement;
import ALL.Model.TextSpeech;
import ALL.Model.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import ALL.MainApp;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.scene.image.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private MainApp mainApp;
    private TextSpeech textSpeech = new TextSpeech();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @FXML
    private TextField text = new TextField();
    @FXML
    private Button nhap;
    @FXML
    private WebView browser = new WebView();
    @FXML
    private WebEngine webEngine = browser.getEngine();
    @FXML
    private VBox vBox;
    @FXML
    ChoiceBox cb = new ChoiceBox();

    public void setMainApp(MainApp mainApp) {
    }

    public void showDetails(ActionEvent event) {
        //imageView.setVisible(false);
        String a = text.getText();
        String b = Dictionary.dictionaryLookup(a);
        webEngine.loadContent(b);
        vBox.getChildren().setAll(browser);

    }

    @FXML
    private ListView<String> listView;
    private ObservableList<String> resultView = FXCollections.observableArrayList();

    boolean check1 = true;
    public void TextFieldListener()  {

        if(check1 == true) {
        //Listen for textfied
            text.textProperty().addListener((observable, oldValue, newValue) -> {
                //System.out.println(oldValue + " " + newValue);
                if (!newValue.equals("")) {

                    System.out.println(oldValue);
                    System.out.println(newValue);
                    listView.setItems(Dictionary.addToListView(newValue));
                }
                if(newValue.equals("")) {
                    resultView.clear();
                }
                check1 = false;
            });}
    }

    boolean check2 = true;
    public  void SetChoiceBox() {
        cb.setAccessibleText("hello");
        ActionEvent event = null;
        cb.setItems(FXCollections.observableArrayList(
                "Add Word", "Remove Word")
        );
        if(check2 == true) {
            cb.getSelectionModel().selectedIndexProperty().addListener(new
               ChangeListener<Number>() {
                   @Override
                   public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                       //System.out.println(oldValue + " "+ newValue);
                       if(newValue.equals(0)) {
                           AddButtonHandle(event);
                       }
                       else if(newValue.equals(1)) {
                           RemoveHandle(event);
                       }


                   }

               }
            );}
        check2 = false;
    }

    public void setOnCLick() {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() <= 2) {
                    text.setText(listView.getSelectionModel().getSelectedItem());
                    String a = text.getText();
                    String b = Dictionary.dictionaryLookup(a);
                    webEngine.loadContent(b);
                    vBox.getChildren().setAll(browser);
                }
            }
        });
    }

    @FXML
    Button add;

    public void AddButtonHandle(ActionEvent event)  {
        GridPane secondLayout = new GridPane();
        secondLayout.setHgap(10);
        secondLayout.setVgap(10);
        secondLayout.setPadding(new Insets(20, 150, 10, 10));
        Scene secondScene = new Scene(secondLayout, 350, 150);
        Stage newWindow = new Stage();
        newWindow.getIcons().add(new Image("https://png.pngtree.com/element_our/md/20180518/md_5afec9fa6eed3.jpg"));
        newWindow.setTitle("Nhap tu va nghia ban muon them vao");
        TextField eWord = new TextField();
        eWord.setPromptText("English Word");
        TextField vWord = new TextField();
        vWord.setPromptText("Viet Nam Word");
        Button ok = new Button();
        ok.setPrefSize(26,20);
        ok.setText("Finish");
        ok.setMaxSize(30,7);
        secondLayout.add(new Label("English Word"), 0, 1, 10, 1);
        secondLayout.add(eWord, 11, 1, 18, 1);
        secondLayout.add(new Label("Viet Nam Word"), 0, 2, 10, 2);
        secondLayout.add(vWord, 11, 2, 18, 2);
        secondLayout.add(ok, 15, 5);
        newWindow.setScene(secondScene);
        newWindow.show();
        eWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String word_target = eWord.getText();
                //System.out.println(word_target);
                vWord.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String word_explain = vWord.getText();
                        Word newWord = new Word(word_target, word_explain);
                        Dictionary.getWord_List().add(newWord);
                        DictionaryManagement.addToDatabase(word_target, word_explain);
                    }
                });
            }
        });
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.close();
                //mainApp.getWordlist().showAllWords(15, 20, 20);
            }
        });
    }

    @FXML
    private Button remove;
    public void RemoveHandle(ActionEvent event) {
        GridPane secondLayout = new GridPane();
        secondLayout.setHgap(10);
        secondLayout.setVgap(10);
        secondLayout.setPadding(new Insets(20, 150, 10, 10));
        Scene secondScene = new Scene(secondLayout, 350, 150);
        Stage newWindow = new Stage();
        newWindow.getIcons().add(new Image("https://png.pngtree.com/element_our/md/20180518/md_5afec9fa6eed3.jpg"));
        newWindow.setTitle("Nhap tu va nghia ban muon xoa");
        TextField eWord = new TextField();
        eWord.setPromptText("English Word");
        Button ok = new Button();
        ok.setText("OK");
        secondLayout.add(new Label("English Word"), 0, 1, 10, 1);
        secondLayout.add(eWord, 11, 1, 18, 1);
        secondLayout.add(ok, 15, 4);
        newWindow.setScene(secondScene);
        newWindow.show();
        eWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String word = eWord.getText();
                DictionaryManagement.removeFromDatabase(word);
            }
        });
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newWindow.close();
            }
        });
    }


    @FXML
    private Button sound = new Button();
    public void soundButton(ActionEvent event) {
        String word = text.getText();
        textSpeech.speakWord(word);
    }
}