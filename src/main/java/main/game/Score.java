/*package main.game;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Score {
    private static final String dir = "src/main/resources/UserScore/";
    private static final int n_levels = 3;

    public static void writeScore(String name, int score, int level) {
        ArrayList<Integer> scores = new ArrayList();
        //for each level
        for (int i = 1; i <= 3; i++) {
            //Get the current score
            scores.add(getScore(name, i));
        }
        //Check if last saved score is bigger
        System.out.println(scores.get(level));
        System.out.println(score);
        if (scores.get(level) < score) {
            //Create the json object with old values
            JSONObject levels_json = new JSONObject();
            for (int i = 0; i < n_levels; i++) {
                levels_json.put(i + 1, scores.get(i));
            }
            //Replace the value we want
            System.out.println("Replacing");
            levels_json.replace(level, scores.get(level), score);
            //And we will recreate the json file
            try (FileWriter file = new FileWriter(dir + name + ".json")) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(levels_json.toJSONString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getScore(String name, int i) {
        int score = 0;//Score to be returned
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(dir + name + ".json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            //get current level
            JSONObject current_level = (JSONObject) obj;
            //get score for current level
            String s = (String) current_level.get(i);
            System.out.println(s);
        } catch (IOException | ParseException ignored) {

        }
        return score;
    }

    /**
     * Will check if user log in details is correct
     *
     * @param name     :Given name
     * @param password :Given password
     * @return true or false depend on details given

    public static boolean check(String name, String password) {
        boolean ok = false; //result
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(dir + "All.txt"));
            String line;
            boolean userfound = false;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(name)) {
                    if (values[1].equals(password)) {
                        ok = true;
                    } else {
                        //To be implemented with graphics
                        System.out.println("Wrong password");
                    }
                    break;
                }
            }
            if (!userfound) {
                System.out.println("User does not exists");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok;
    }

    /**
     * We will use already existing file to append user data
     * will be adding new line each time to prepare for next
     *
     * @param name
     * @param password

    public static void createUser(String name, String password) {
        try (FileWriter file = new FileWriter(dir + "All.txt", true)) {
            file.append(name).append(",").append(password).append("\n");//Save name and password
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Initialize the .json file with 0s
        try (FileWriter file = new FileWriter(dir + name + ".json")) {
            JSONObject levels_json = new JSONObject();
            for (int i = 1; i <= n_levels; i++) {
                levels_json.put(i, 0);
            }
            //We can write any JSONArray or JSONObject instance to the file
            file.write(levels_json.toJSONString());
        } catch (IOException e) {
            System.out.println("User file couldn't be crerated");
            e.printStackTrace();
        }
    }
}

 */

