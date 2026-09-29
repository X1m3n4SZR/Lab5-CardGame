package lab03.lab5cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    Pane base;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Pane base = new Pane();//fxmlLoader.load();
        Scene scene = new Scene(base,1250, 500);

        Button shuffle = new Button();
        shuffle.setText("Shuffle");
        shuffle.setLayoutX(600);
        shuffle.setLayoutY(450);
        base.setStyle("-fx-background-color: #00FF00");
        base.getChildren().add(shuffle);
        displayCards();

        stage.setTitle("Lab 5:Shuffle Deck of Cards");
        stage.setScene(scene);
        stage.show();


    }

    void displayCards(){
        System.out.println("Its working");
        File cards = new File("src/main/Cards");
        File[] listOfCards = cards.listFiles();
        int col = 100;
        int row = 100;
        int count = 0;

        if(listOfCards != null){
            for(File file:listOfCards){
                if(file.isFile() && file.getName().endsWith(".png")){

                    Image image = new Image(file.toURI().toString());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(95);
                    imageView.setPreserveRatio(true);
                    imageView.setLayoutX(col);
                    imageView.setLayoutY(row);

                    base.getChildren().add(imageView);
                    if(count == 13){
                        row += 20;
                        col = 100;
                        count = 0;
                    }
                    col += 80;
                    count++;
                }
            }
            System.out.println("It goes to the end");
        }
        System.out.println("It goes to pthe end");
    }


    public static void main(String[] args) {
        launch();
    }
}