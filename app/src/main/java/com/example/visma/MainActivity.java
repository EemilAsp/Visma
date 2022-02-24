package com.example.visma;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity<Arraylist> extends AppCompatActivity {
    //Sourcelink for Visma font
    //https://www.visma.com/brandbook/resources/
    //The program was tested with Nexus 5 API 30 emulator.

    userUri uri = userUri.getInstance();
    TextInputEditText uriString;
    Button goButton;
    ArrayList<String> action = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uriString = findViewById(R.id.user_input);
        goButton = findViewById(R.id.goButton);
    }


    public void getUriFromUserInput(View V) {//function is launched when go button is pressed.
        if (uriString.getText().toString() != null && !uriString.getText().toString().isEmpty()) { //testing that input is not null and its not empty either.
            action = uri.userUri(uriString.getText().toString()); // pass string to the class
            //note that the first element of the array is always the action
            //second and third elements will be the other parameter values.
            if (!action.isEmpty()) {
                //here we can implement code what we want to do with the action and parameter values
                //used one of my premade errorpopups just to show the end user if URI was correct or something went wrong

                if(action.size() == 3) {
                    ErrorPopUp error = new ErrorPopUp("URI was correct", "Action: " + action.get(0) + "\nParameter 1: " + action.get(1) + "\nParameter 2: " + action.get(2));
                    error.show(getSupportFragmentManager(), "correct");
                }else{
                    ErrorPopUp error = new ErrorPopUp("URI was correct", "Action: " + action.get(0) + "\nParameter 1: " + action.get(1));
                    error.show(getSupportFragmentManager(), "correct");
                }
            }else{
                //if the array is empty something was wrong and displaying an error message to the user
                ErrorPopUp error = new ErrorPopUp("Something went wrong", "Please check that the URI is correct");
                error.show(getSupportFragmentManager(), "error");
            }
            action.clear();
            //after the program is finished using the action and parameters, were clearing the array
            //that the next URI validated can be put into the list.
        }
    }}