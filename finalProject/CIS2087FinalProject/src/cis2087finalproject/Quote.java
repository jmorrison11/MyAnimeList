/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cis2087finalproject;

/**
 *
 * @author Jade
 */
public class Quote {
     // Instance variables are public so GSON can store the values
    // directly in them.
    public String _id;
    public int __v;
    public String text;
    public String updatedAt;
    public boolean deleted;
    public String source;
    public boolean used;
    
    public Quote()
    {
        _id = "0";
        __v = 0;
        text = "";
        updatedAt = "";
        deleted = false;
        source = "";
        used = true;
    }
    
    public String getText()
    {
        return text;
    }
}
