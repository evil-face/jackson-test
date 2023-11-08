package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CustomKey key1 = new CustomKey("1", "cat1");
        CustomKey key2 = new CustomKey("2", "cat2");
        CustomKey key3 = new CustomKey("3", "cat3");

        Map<CustomKey, String> map = new HashMap<>();
        map.put(key1, "test1");
        map.put(key2, "test2");
        map.put(key3, "test3");

        FileMapper.writeStorage(map, "src/main/resources/file.txt");

        Map<CustomKey, String> result = FileMapper.readStorage("src/main/resources/file.txt");
    }
}