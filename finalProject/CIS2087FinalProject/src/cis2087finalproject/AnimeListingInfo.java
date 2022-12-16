/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis2087finalproject;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 * Here is where we are storing the properties needed displayed in the FXML document
 * 
 * @author Jade
 * version 1.0
 */
public class AnimeListingInfo {
    public StringProperty nameOfAnime;
    public StringProperty date;
    public boolean ongoing;
    public StringProperty favoriteCharacter;
    
    /**
     * Empty constructor for strings
     */
    public AnimeListingInfo()
    {
        nameOfAnime = new SimpleStringProperty(this, "nameOfAnime", "");
        date = new SimpleStringProperty(this, "date", "");
        favoriteCharacter = new SimpleStringProperty(this, "favoriteCharacter", "");
    }
    
    /**
     * This is out not empty constructor, it sets the variables to StringProperties
     * 
     * @param inNameOfAnime what we are using in our set methods to set the name of the anime
     * @param inDate what we are using in our set methods to set the date
     * @param inFavoriteCharacter what we are using in our set methods for the favorite character input
     */
    public AnimeListingInfo(String inNameOfAnime, String inDate, String inFavoriteCharacter) {
        nameOfAnime = new SimpleStringProperty(this, "nameOfAnime", inNameOfAnime);
        date = new SimpleStringProperty(this, "date", inDate);
        ongoing = false;
        favoriteCharacter = new SimpleStringProperty(this, "favoriteCharacter", inFavoriteCharacter);
    }

    /**
     * returns a string instead of a StringProperty of the name of the anime 
     * @return String nameofAnime 
     */
    public String getNameOfAnime()
    {
        return nameOfAnime.get();
    }
    
    /**
     * returns a StringProperty instead of a String of the name of the anime 
     * @return StringProperty nameofAnime 
     */
    public StringProperty nameOfAnimeProperty()
    {
        return nameOfAnime;
    }
    
    /**
     * sets the nameOfAnime to the String variable inNameOfAnime 
     * @param inNameOfAnime 
     */
    public void setNameOfAnime(String inNameOfAnime)
    {
        nameOfAnime.set(inNameOfAnime);
    }
    
    /**
     * returns a string instead of a StringProperty of the date they enter
     * @return String date 
     */
    public String getDate()
    {
        return date.get();
    }
    
    /**
     * returns a StringProperty instead of a String of the name of the date 
     * @return StringProperty date 
     */
    public StringProperty dateProperty()
    {
        return date;
    }
    
    /**
     * Sets the date to the String variable inDate
     * @param inDate 
     */
    public void setDate(String inDate)
    {
        date.set(inDate);
    }
    
    /**
     * returns a string instead of a StringProperty of the favorite character 
     * @return String favoriteCharacter
     */
    public String getFavoriteCharacter()
    {
        return favoriteCharacter.get();
    }
    
    /**
     * returns a StringProperty instead of a String of the name of the anime 
     * @return StringProperty favoriteCharacter
     */
    public StringProperty favoriteCharacterProperty()
    {
        return favoriteCharacter;
    }
    
    /**
     * Sets the favoritesCharacter to the String variable inFavoriteCharacter
     * @param inFavoriteCharacter 
     */
    public void setFavoriteCharacter(String inFavoriteCharacter)
    {
        favoriteCharacter.set(inFavoriteCharacter);
    }
    
    /**
     * 
     * @return whether the anime is ongoing to completed
     */
    public String ongoing(){
        String onGoingYes = "Ongoing.";
        String onGoingNo = "Completed.";        
        if (ongoing == true) {
            return onGoingYes;
        }
        return onGoingNo;
    }
    
    /**
     * 
     * @return a human readable sentence 
     */
    
    @Override
    public String toString(){
        return "Anime: " + nameOfAnime.getValue() + " Started: " + date.getValue()+ " Favorite Character: " + favoriteCharacter.getValue() + ".";
    }
    
    public String toStringSecond() {
        return nameOfAnime.getValue();
    }
    
    /**
     * 
     * @param object
     * @return if there is a object like this that already exists
     */
    @Override
    public boolean equals(Object object)
    {
        boolean areEqual;
        
        if (this == object)
        {
            areEqual = true;
        }
        else if (object == null)
        {
            areEqual = false;
        }
        else if (getClass() != object.getClass())
        {
            areEqual = false;
        }
        else
        {
            AnimeListingInfo other = (AnimeListingInfo) object;
            
            if (getNameOfAnime().equals(other.getNameOfAnime())
                    && getDate().equals(other.getDate())
                    && getFavoriteCharacter().equals(other.getFavoriteCharacter()))
            {
                areEqual = true;
            }
            else
            {
                areEqual = false;
            }
        }

        return areEqual;
    }
    
    /**
     * helps to save to the file
     */
    public static Callback<AnimeListingInfo, Observable[]> extractor = p -> new Observable[]
        {p.nameOfAnimeProperty(), p.dateProperty(), p.favoriteCharacterProperty()};
    }
