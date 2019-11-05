package WordAnalyzerPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Creates a WordAnalyzer Object.
 *
 * The object will read in a txt file
 * and output a result string with the
 * top 20 most frequently used words.
 *
 * @author Manuel Armenta Batt
 *
 */
public class WordAnalyzer extends Application{


    Button fileButton;

    /**
     * start will take a JavaFX Primary stage
     * and will initialize the first screen of
     * the GUI.
     * A file will be asked to uploaded through
     * JavaFX Filechooser for processing.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("WordAnalyzerPackage.fxml"));
        primaryStage.setTitle("Text Analyzer");

        //Intro Text formatting
        Text introText = new Text("Welcome to Manny Batt's Text Analyzer!");
        introText.setFont(Font.font("Verdana", 24));
        introText.setFill(Color.DARKBLUE);

        //Directions Text formatting
        Text introText2 = new Text("Upload a text file to see the 10 most common words");
        introText2.setFont(Font.font("Verdana", 16));
        introText2.setFill(Color.DARKSLATEGREY);

        /* FileChooser and Buttons formatting
         ** All of the action occurs when a file is chosen from filechooser
         ** Text is automatically processed and results are displayed
         */
        String path = "";
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        fileButton = new Button("Upload a file");
        fileButton.setOnAction(e -> {
            File chosenFile = filechooser.showOpenDialog(primaryStage);

            Text preambleResultString = new Text("The 20 most commonly found words in \n");
            preambleResultString.setFont(Font.font("Bell MT", 26));
            preambleResultString.setFill(Color.DARKBLUE);

            Text fileNameText = new Text(chosenFile.getName() + "\n\n");
            fileNameText.setFont(Font.font("Bell MT", 26));
            fileNameText.setFill(Color.DARKGREEN);

            Text resultHeader = new Text("Word           Occurances");
            resultHeader.setFont(Font.font("Bell MT", 22));
            resultHeader.setFill(Color.PURPLE);

            //System.out.println(analyzer(chosenFile));
            Text resultString = null;
            try {
                resultString = new Text(WordAnalyzer.analyzer(chosenFile));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            resultString.setFont(Font.font("Bell MT", 22));
            resultString.setFill(Color.DARKRED);

            VBox resultLayout = new VBox(9); // Vertical Box
            resultLayout.setAlignment(Pos.CENTER);
            resultLayout.getChildren().add(preambleResultString);
            resultLayout.getChildren().add(fileNameText);
            resultLayout.getChildren().add(resultString);
            resultLayout.getChildren().add(fileButton);

            Scene resultScene = new Scene(resultLayout, 550, 800);
            primaryStage.setScene(resultScene);
            primaryStage.setResizable(false);
            primaryStage.show();

        });


        VBox layout = new VBox(10); // Vertical Box
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(introText);
        layout.getChildren().add(introText2);
        layout.getChildren().add(fileButton);

        Scene scene = new Scene(layout, 550, 200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }


    /**
     * Kickstarts the start method.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * analyzer is responsible for the heavy
     * lifting in this project. It accepts a
     * File as a parameter and returns a String
     * of Results detailing the 20 most commonly
     * used words in a text file.
     *
     * @param file
     * @throws FileNotFoundException
     */
    public static String analyzer(File file) throws FileNotFoundException {
        Map<String, Integer> wordMap = new HashMap<>();
        Scanner scan;

        //Attempt to scan the passed along file
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            return "File Not Found!";
        }

        //Iterate through file, send words to lowercase, remove punctuation, and store word in wordmap
        if (scan.hasNext() == true) {

            if(scan.hasNextFloat()){

            }

            //This scans through the text file returns a not valid string if there's less than 20 words in file
            int counter = 0;
            while (scan.hasNext() == true) {
                scan.next();
                counter++;
            }
            try {
                scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File Error, but not really...");
            }

            while (scan.hasNext()) {
                String word = "";
                if(scan.hasNextFloat() == true){
                    scan.nextFloat();
                    //This scans for numerical numbers and decimals and skips them
                }
                else{
                    word = scan.next().toLowerCase();
                    word = word.replaceAll("\\p{Punct}", "");
                }


                if(word.equals("")){
                    //Skips special characters
                }
                else if (wordMap.containsKey(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                }
                else {
                    wordMap.put(word, 1);
                }
            }
        } else if (scan.hasNext() == false) { //Empty File Coverage
            return "This file is EMPTY!\n\n";
        }


        //place words in wordmap into a treemap for easier manipulation
        TreeMap<String, Integer> sortedWordMap = new TreeMap<>();
        sortedWordMap.putAll(wordMap);


        String[] topX = new String[0];
        String[] top20 = new String[0];
        int[] topXQuantity = new int[0];
        int[] top20Quantity = new int[0];
        String toReturn = "";
        int i = 0;

        //if contains instructions for files LESS than 20 words
        if (sortedWordMap.size() < 19) {
            topX = new String[sortedWordMap.size()];
            topXQuantity = new int[sortedWordMap.size()];

            //Makes an array of the top sortedWordMap.size() words
            for (int j = 10000; j > 0; j--) {
                for (Map.Entry<String, Integer> singleWord : sortedWordMap.entrySet()) {
                    String key = singleWord.getKey();
                    Integer value = singleWord.getValue();

                    if (value == j) {
                        topX[i] = key;
                        topXQuantity[i] = value;
                        i++;
                    }
                    if (i == sortedWordMap.size()) {
                        j = 0;
                        break;
                    }
                }
            }

            //Makes one large string to return with the top sortedWordMap.size() results
            for (i = 0; i < sortedWordMap.size(); i++) {
                String tabs = "";
                if (topX[i].length() == 6 || topX[i].length() == 7 || topX[i].length() == 8) {  //Whitespace formatting nonsense
                    tabs += "\t\t";
                } else if (topX[i].length() <= 5 && topX[i].length() >= 1) {
                    tabs += "\t\t\t";
                }

                if (i <= 9) {  //More whitespace formatting nonsense...
                    String spaces = "";
                    if (topX[i].length() > 3) {
                        spaces = "   ";
                    } else {
                        spaces = "   ";
                    }
                    toReturn += ((i + 1) + ".  " + topX[i] + spaces + tabs + topXQuantity[i] + "\n");
                } else {
                    toReturn += ((i + 1) + ". " + topX[i] + tabs + topXQuantity[i] + "\n");
                }
            }
        }

        //else contains the instructions for files with more than 20 words
        else {
            System.out.println("20");
            top20 = new String[20];
            top20Quantity = new int[20];

            //Makes an array of the top 20 words
            for (int j = 10000; j > 0; j--) {
                for (Map.Entry<String, Integer> singleWord : sortedWordMap.entrySet()) {
                    String key = singleWord.getKey();
                    Integer value = singleWord.getValue();

                    if (value == j) {
                        top20[i] = key;
                        top20Quantity[i] = value;
                        i++;
                    }
                    if (i == 20) {
                        j = 0;
                        break;
                    }
                }
            }

            //Makes one large string to return with the top 20 results
            for (i = 0; i < 20; i++) {
                String tabs = "";
                if (top20[i].length() == 6 || top20[i].length() == 7 || top20[i].length() == 8) {  //Whitespace formatting nonsense
                    tabs += "\t\t";
                }
                else if (top20[i].length() <= 5 && top20[i].length() >= 1) {
                    tabs += "\t\t\t";
                }

                if (i <= 9) {  //More whitespace formatting nonsense...
                    String spaces = "";
                    if (top20[i].length() > 3) {
                        spaces = "   ";
                    } else {
                        spaces = "   ";
                    }
                    toReturn += ((i + 1) + ".  " + top20[i] + spaces + tabs + top20Quantity[i] + "\n");
                }
                else {
                    toReturn += ((i + 1) + ". " + top20[i] + tabs + top20Quantity[i] + "\n");
                }
            }
        }
        if (toReturn.equals("")) { //Covers for empty files
            return "No Words Found!\n\n";
        }
        else{
            return toReturn;
        }
    }
}