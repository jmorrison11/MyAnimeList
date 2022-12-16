package cis2087finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable
{
    // Controls from FXML GUI Interface.
    @FXML
    private TextField nameOfAnimeTextField;  
    @FXML
    private TextField dateStartedTextField; 
    @FXML
    private TextField favCharacterTextField;
    @FXML
    private Button findQuoteButton;
    @FXML
    private Button addAnimeButton;
    @FXML
    private Button updateAnimeButton;
    @FXML
    private Button deleteAnimeButton;
    @FXML
    private Button saveDataButton;
    @FXML
    private Button loadDataButton;
    @FXML
    private ListView animeListView;
    @FXML
    private Label hiddenQuoteLabel;
    @FXML
    private Label toStringLabel;
    private AnimeQuoteGenerator animeQuoteGenerator;
    private static final String FILE_NAME = "animeData.txt";
    
    // The list of students to display in the ListView (essentially an ArrayList).
    private ObservableList<AnimeListingInfo> animeList = FXCollections.observableArrayList(AnimeListingInfo.extractor);
    
    // Listens for a change in the selected item in the ListView.
    // When the user changes the selected item, this listener fires.
    private ChangeListener<AnimeListingInfo> animeChangeListener;
    
    // Used to store the Student object the user has selected.
    private AnimeListingInfo selectedAnime;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        animeQuoteGenerator = new AnimeQuoteGenerator();
        animeList.add(new AnimeListingInfo("Naruto", "03/2020", "Hinata"));
        animeList.add(new AnimeListingInfo("Madoka Magica", "01/2013", "Mami"));
        animeList.add(new AnimeListingInfo("Attack on Titan", "03/2013", "Hanji Zoe"));
        animeListView.setItems(animeList);
        
        // Set up the change listener.
        animeListView.getSelectionModel().selectedItemProperty().addListener(
            animeChangeListener = (observable, oldValue, newValue) -> {
                // Set the selected Student.
                selectedAnime = newValue;
                
                if (newValue != null) {
                    // Populate the GridView controls.
                    nameOfAnimeTextField.setText(selectedAnime.getNameOfAnime());
                    dateStartedTextField.setText(selectedAnime.getDate());
                    favCharacterTextField.setText(selectedAnime.getFavoriteCharacter());
                }
                else {
                    // Clear the GridView controls if a Student is not selected.
                    nameOfAnimeTextField.setText("");
                    dateStartedTextField.setText("");
                    favCharacterTextField.setText("");
                }
            }
        );
        
        // Set up the button binding statements.  Determine when they are
        // active and when they are disabled.
        // New Button.
        addAnimeButton.disableProperty().bind(
            nameOfAnimeTextField.textProperty().isEmpty()
                    .or(nameOfAnimeTextField.textProperty().isEmpty()));
        
        // Update and Delete Buttons.
        updateAnimeButton.disableProperty().bind(animeListView.getSelectionModel().selectedItemProperty().isNull());
        deleteAnimeButton.disableProperty().bind(animeListView.getSelectionModel().selectedItemProperty().isNull());
    } 
    
    @FXML
    private void AddAnimeButton(ActionEvent actionEvent) {
        // Get data from form controls.
        String nameOfAnime = nameOfAnimeTextField.getText();
        String date = dateStartedTextField.getText();
        String favoriteCharacter = favCharacterTextField.getText();
        
        // Create Student object.
        AnimeListingInfo anime = new AnimeListingInfo(nameOfAnime, date, favoriteCharacter);
        
        // Add the Student to the list.
        animeList.add(anime);
        
        // Select the Student in the ListView.
        animeListView.getSelectionModel().select(anime);
    }

    @FXML
    private void updateAnimeButton(ActionEvent actionEvent) {
        // Get the object the user selected from the ListView.
        AnimeListingInfo anime = (AnimeListingInfo) animeListView.getSelectionModel().getSelectedItem();
        
        // Temporarily remove the change listener while we adjust the Student data.
        animeListView.getSelectionModel().selectedItemProperty().removeListener(animeChangeListener);
        
        // Get the updated data from the controls.
        String nameOfAnime = nameOfAnimeTextField.getText();
        String date = dateStartedTextField.getText();
        String favoriteCharacter = favCharacterTextField.getText();
        
        // Update the data in the object.
        anime.setNameOfAnime(nameOfAnime);
        anime.setDate(date);
        anime.setFavoriteCharacter(favoriteCharacter);
        
        // Add the change listener back in.
        animeListView.getSelectionModel().selectedItemProperty().addListener(animeChangeListener);
    } 
    
    @FXML
    private void deleteAnimeButton(ActionEvent actionEvent) {
        // User has chosen to delete the selected Student.
        // Remove it from the list.
        animeList.remove(selectedAnime);
    }
    
    @FXML 
    private void findQuoteButton(ActionEvent actionevent) {
        findQuote();
    }
    private void findQuote()
    {
        String animeName = nameOfAnimeTextField.getText();
        // Call the api to get a  .
        String animeQuote = animeQuoteGenerator.getAnimeQuote(animeName);

        // Display and in the controls in the scene.
        hiddenQuoteLabel.setText(animeQuote);   
    }
    
    @FXML
    private void saveDataButton(){      
        try (PrintWriter writer = new PrintWriter(new File(FILE_NAME)))
        {
            File file = new File(FILE_NAME);
            //writer = new PrintWriter(file);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Error, file not found." + exception.getMessage());
        }
    }
    
    @FXML
    private void loadDataButton(){
        //Scanner fileInput = null;
        String line;
        try (Scanner fileInput = new Scanner(new File(FILE_NAME)))
        {
            //fileInput = new Scanner(new File(FILE_NAME));

            while(fileInput.hasNext())
            {
                line = fileInput.nextLine();
            }
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Error, " + FILE_NAME + " could not be found.");
            System.out.println("More details: " + exception.getMessage());
        }
    }   
}
