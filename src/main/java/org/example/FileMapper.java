package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileMapper {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<CustomKey, String> readStorage(String storageFilePath) {
        Map<CustomKey, String> storage = new HashMap<>();

        if (Files.exists(Paths.get(storageFilePath))) {
            System.out.println("Found file " + storageFilePath + ", trying to parse.");
            File diskStorage = new File(storageFilePath);

            if (diskStorage.length() != 0) {
                try {
                    storage = OBJECT_MAPPER.readValue(
                            diskStorage,
                            new TypeReference<Map<CustomKey, String>>() { });
                    System.out.println("Successfully parsed storage from disk.");
                } catch (IOException e) {
                    System.out.println("Error while parsing file, quitting.");
                    throw new RuntimeException(e);
                }
            }
        }

        return storage;
    }

    public static void writeStorage(Map<CustomKey, String> storage, String storageFilePath) {
        if (Files.exists(Paths.get(storageFilePath))) {
            System.out.println("Found file " + storageFilePath + ", trying to write.");
            try {
                OBJECT_MAPPER.writeValue(new File(storageFilePath), storage);
                System.out.println("Successfully saved storage to disk.");
            } catch (IOException e) {
                System.out.println("Error while writing file, quitting.");
                throw new RuntimeException(e);
            }
        }
    }
}

