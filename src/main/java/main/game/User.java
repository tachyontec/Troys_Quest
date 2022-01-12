package main.game;

import netscape.javascript.JSObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class User {
    private String name;
    private int score;
    private int level_passed;
    private static int count = 0;

    public User(String name, int score, int level_passed){
        this.name = name;
        this.score = score;
        this.level_passed = level_passed;
        count++;
    }



    public void createProfile()  {
        String path = "src/main/resources/User/"+getName();
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
            createProfileFile(path);
        }
    }
    private void createProfileFile(String path){
        File file = new File(path+"/Name"+".txt");

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPassedLevelFile() {
        if (getLevel_passed() > 0) {
            try {
                File file = new File("src/main/resources/User/" + getName() + "/" + "Level_" + getLevel_passed() + ".txt");
                DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));
                writer.write(getScore());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePassedLevelFile()  {
        File file = new File("src/main/resources/User/"+ getName()+"/" +  "Level_" + getLevel_passed() + ".txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Trouble loading file");
        }
        if(sc.nextInt()<score){
            DataOutputStream writer = null;
            try {
                writer = new DataOutputStream(new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                writer.write(getScore());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String[] getUsers(){
        String[] pathNames;
        File f = new File("src/main/resources/User");
        pathNames = f.list();
        return pathNames;
    }
    
    public int retrieveScoreFile(){
        File file = new File("src/main/resources/User/"+ getName()+"/" +  "Level_" + getLevel_passed() + ".txt");
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            return in.readInt();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return 0;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public void setLevel_passed(int level_passed){
        this.level_passed = level_passed;
    }
    public int getLevel_passed(){
        return this.level_passed;
    }
}