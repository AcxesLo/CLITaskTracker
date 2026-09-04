package com.acxes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class JsonFile {

    public static void writeJsonFile() {
        String user = System.getProperty("user.name");
        File file = new File("C:\\Users\\" + user + "\\Desktop\\tasks.json");

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File "
                        + "'"
                        + file.getName()
                        + "'"
                        + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        try (FileWriter writer = new FileWriter(file, false)) {
            gson.toJson(TaskLogic.getTaskList(), writer);
            System.out.println("Data written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
