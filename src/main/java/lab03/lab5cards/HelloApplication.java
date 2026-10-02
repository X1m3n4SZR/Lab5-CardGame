package lab03.lab5cards;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HelloApplication extends Application {
    Pane base;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //fxmlLoader.load();
        base = new Pane();
        Scene scene = new Scene(base,1250, 500);

        Button shuffle = new Button();
        shuffle.setText("Shuffle");
        shuffle.setLayoutX(600);
        shuffle.setLayoutY(450);
        base.setStyle("-fx-background-color: #00FF00");
        base.getChildren().add(shuffle);
        displayCards();

        Button blackjack = new Button();
        blackjack.setText("Play BlackJack");
        blackjack.setLayoutX(400);
        blackjack.setLayoutY(450);
        base.setStyle("-fx-background-color: #00FF00");
        base.getChildren().add(blackjack);

        stage.setTitle("Lab 5: Shuffle a Deck of Cards");
        stage.setScene(scene);
        stage.show();

        shuffle.setOnAction(actionEvent -> {
            shuffleCards();
        });

        blackjack.setOnAction(actionEvent -> {
            Stage blackJack = new Stage();
            Pane pane = new Pane();
            Scene show = new Scene(pane, 1250, 500);

            blackJack.setTitle("Playing Black Jack!");
            blackJack.setScene(show);
            blackJack.show();

            //new BlackjackGame().show();
        });
    }



    void displayCards(){
        System.out.println("Its working");
        File cards = new File("src/main/Cards");
        File[] listOfCards = cards.listFiles();
        int col = 100;
        int row = 25;
        int count = 0;

        //Compares 2 objects' suit and actual value to sort
        Arrays.sort(listOfCards, (a, b) -> {
            String name1 = a.getName();
            String name2 = b.getName();

            int suit1 = getSuitValue(a.getName());
            int suit2 = getSuitValue(b.getName());

            //Checks to see if the suits are the same
            if(suit1 != suit2){
                //If negative, then suit1 comes before, if positive, then suit1 comes after suit2
                return suit1 - suit2;
            }else{
                int suitVal1 = getCardValue(a.getName());
                int suitVal2 = getCardValue(b.getName());

                //If both suits are the same, then looks for actual value: Same logic with suit values
                return suitVal1 - suitVal2;
            }
        });

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
                    if(count == 12){
                        row += 100;
                        col = 100;
                        count = 0;
                    }
                    else{
                        col += 80;
                        count++ ;
                    }
                }
            }
        }
    }

    void shuffleCards(){
        File cards = new File("src/main/Cards");
        File[] listOfCards = cards.listFiles();

        if(listOfCards == null) {
            System.out.println("Could not find the Cards");
            return;
        }

        //puts the cards into a random order starting at the last card
        //then swaps it with a random card at or before it then step back and repeat
        Random rand = new Random();
        for(int i = listOfCards.length - 1; i > 0; i--){
            int j = rand.nextInt(i + 1);

            File temp = listOfCards[i];
            listOfCards[i] = listOfCards[j];
            listOfCards[j] = temp;
        }
        //removes old card pictures from the screen (keeping the current buttons)
        base.getChildren().removeIf(node -> node instanceof ImageView);

        //draws the cards in their new order (using the same layout as displayCards)
        int col = 100;
        int row = 25;
        int count = 0;

        for(File file : listOfCards){
            if(file.isFile() && file.getName().endsWith(".png")){

                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(70);
                imageView.setFitHeight(95);
                imageView.setPreserveRatio(true);
                imageView.setLayoutX(col);
                imageView.setLayoutY(row);

                base.getChildren().add(imageView);
                if(count == 12){
                    row += 100;
                    col = 100;
                    count = 0;
                }
                else{
                    col += 80;
                    count++ ;
                }
            }
        }
    }


    private int getSuitValue(String suit){
        if(suit.startsWith("club") || suit.startsWith("clubs")){
            return 0;
        }else if(suit.startsWith("diamond") || suit.startsWith("diamonds")){
            return 1;
        }else if(suit.startsWith("heart") || suit.startsWith("hearts")){
            return 2;
        }else{
            return 3;
        }
    }


    private int getCardValue(String val){
        if(val.endsWith("1.png")){
            return 1;
        }else if(val.endsWith("2.png")){
            return 2;
        }else if(val.endsWith("3.png")){
            return 3;
        }else if(val.endsWith("4.png")){
            return 4 ;
        }else if(val.endsWith("5.png")){
            return 5 ;
        }else if(val.endsWith("6.png")){
            return 6 ;
        }else if(val.endsWith("7.png")){
            return 7 ;
        }else if(val.endsWith("8.png")){
            return 8 ;
        }else if(val.endsWith("9.png")){
            return 9 ;
        }else if(val.endsWith("10.png")){
            return 10 ;
        }else if(val.endsWith("Jack.png")){
            return 11 ;
        }else if(val.endsWith("Queen.png")){
            return 12 ;
        }else{
            return 13 ;
        }
    }

    public static void main(String[] args) { launch();}
}