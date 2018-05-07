package com.game;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHundler {
    private static void replace(String old , String n)  {
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get("src/ressources/users.txt"), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(old)) {
                    fileContent.set(i, n);
                    break;
                }
            }

            Files.write(Paths.get("src/ressources/users.txt"), fileContent, StandardCharsets.UTF_8);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getUser() {
        try {
            Boolean found = false;
            File file = new File("src/ressources/users.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedLine =line.split("\\s+");
                if(parsedLine.length < 3){
                    found = true;
//                    br.close();
                    replace(line,line+"     used");
                    return line;
                }
            }
            br.close();
            return "not found";
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return "file not found";
        } catch (IOException e1) {
            e1.printStackTrace();
            return "io exception";
        }

    }
    public static void unset(String line){
        replace(line+"     used",line);
    }
    public static void addHistory(String line){
        try{
        String str = line;
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/ressources/history.txt",true));
            writer.write(str+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static List<String> getHistory(String name) {
        List<String> content = new ArrayList<>();
        try {
            File file = new File("src/ressources/history.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedLine =line.split("\\s+");
                if(parsedLine[0].equals(name) || parsedLine[1].equals(name)){
                    content.add(line);
                }
            }
            br.close();
            return content;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            content.add("file not found");
            return content;
        } catch (IOException e1) {
            e1.printStackTrace();
            content.add("io exception");

            return content;
        }

    }
}
