package com.example.visma;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

public class userUri {
    private static userUri ur = new userUri();
    ArrayList<String> array = new ArrayList<>();

    //This is the class for testing the string received from terminal or from the android emulator
    //First were going to test that the scheme equals visma-identity
    //second were are determining if the action equals login/sign/confirm or is not valid
    //Third were testing that all the parameters are valid (not null or empty, or the UUID is actually a UUID format variable)
    //If the source parameter can vary, for instance login source parameter could be either severa, netvisor or vismasign
    //The program could be altered by only testing that source is a string and it equals one of the possible sources netvisor, vismasign or severa.
    //Also further implementation could be that the things this function is required to return back to main function
    //(action + parameters) are used to create an object and the function returns only the object to the main function
    //In addition, error in validification could be more specific (returning information where the URI failed the validification process what was wrong)
    //Currently this class returns only an array filled with action + other parameters depending if the URI passes the validation test
    //I would really want to know what would be the most optimal solution for this kind of program so in terms of learning
    //if you are able to post a sample solution I would be really happy to see it.

    public static userUri getInstance(){return ur;}
    public ArrayList userUri(String input){//taking the user input from textfield or from terminal
        Uri uri = Uri.parse(input); // parsing the URI received with android studio's own URI library
        try{ // incase of errors if errors occur returning empty array
        if(uri.getScheme().equals("visma-identity")){ // first testing if the URI scheme equals to visma-identity.
        // after visma-identity confirmed continuing to testing whether the action is login/confirm/sign

            //case login
            if(uri.getHost().equals("login") && uri.getQueryParameterNames().size() == 1){
                if(uri.getQueryParameter("source").equals("severa")){
                    array.add(uri.getHost()); //adding the action + parameters into a arraylist
                    array.add(uri.getQueryParameter("source"));//adding the action + parameters into a arraylist
                    return array;//return array filled with parameters + action
                }else{return array;}

            //case confirm
            }else if(uri.getHost().equals("confirm") && uri.getQueryParameterNames().size() == 2){// host = confirm
                if(uri.getQueryParameter("source").equals("netvisor") && !uri.getQueryParameter("paymentnumber").isEmpty() && uri.getQueryParameter("paymentnumber") != null){ // test that parameters are valid
                    array.add(uri.getHost()); //adding the action + parameters into a arraylist
                    array.add(uri.getQueryParameter("source"));//adding the action + parameters into a arraylist
                    array.add(uri.getQueryParameter("paymentnumber"));//adding the action + parameters into a arraylist
                    return array;//return array filled with parameters + action
                }else{return array;}

            // case sign
            }else if(uri.getHost().equals("sign") && uri.getQueryParameterNames().size() == 2){ // host = sign
                if(uri.getQueryParameter("source").equals("vismasign") && testUUID(uri.getQueryParameter("documentid")) == true){ // test that parameters are valid
                    array.add(uri.getHost());//adding the action + parameters into a arraylist
                    array.add(uri.getQueryParameter("source"));//adding the action + parameters into a arraylist
                    array.add(uri.getQueryParameter("documentid"));//adding the action + parameters into a arraylist
                    return array;//return array filled with parameters + action
                }else{return array;}
            }else{return array;}
        }else{
            return array;
        }}catch(Exception e){
            return array;
        }
    }

    Boolean testUUID(String stringid){ // test that UUID is actually type UUID
        try{
            UUID uuid = UUID.fromString(stringid);//if the UUID is type UUID returning true
            return true;
        }catch(IllegalArgumentException exception){//if not returns false
            return false;
        }
    }

}
