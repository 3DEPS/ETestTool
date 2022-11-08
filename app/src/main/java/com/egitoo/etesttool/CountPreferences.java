package com.egitoo.etesttool;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.Map;

public class CountPreferences implements Serializable {

    SharedPreferences mySharedPreferences;
    SharedPreferences.Editor editor;

    CountPreferences(SharedPreferences preferences){
        mySharedPreferences = preferences;
        editor = mySharedPreferences.edit();
    }

    public void addPlayer(String name, Integer count) throws Exception {
        if(!mySharedPreferences.contains(name)) {
            editor.putInt(name, count);
            editor.apply();
        } else {
            throw new Exception("Player is play!");
        }
    }

    public void updateCount(String name, Integer count) throws Exception {
        if(mySharedPreferences.contains(name)) {
            editor.putInt(name, count);
            editor.apply();
        } else {
            throw new Exception("Player not found!");
        }
    }

    public Integer getCount(String name) throws Exception {
        if(mySharedPreferences.contains(name)) {
            return mySharedPreferences.getInt(name, 0);
        } else {
            throw new Exception("Player not found!");
        }
    }

    public boolean isEmpty(){
        return mySharedPreferences.getAll().isEmpty();
    }

    public Map<String, ?> getAllPlayer ()
    {
        return mySharedPreferences.getAll();
    }

    public void deletePlayer(String name) throws Exception {
        if(mySharedPreferences.contains(name)) {
            editor.remove(name);
            editor.apply();
        } else {
            throw new Exception("Player not found!");
        }
    }

    public void renamePlayer(String oldName, String newName) throws Exception {
        if (mySharedPreferences.contains(oldName)) {
            addPlayer(newName, mySharedPreferences.getInt(oldName,0));
            editor.remove(oldName);
            editor.apply();
        } else {
            throw new Exception("Player not found!");
        }
    }
}
