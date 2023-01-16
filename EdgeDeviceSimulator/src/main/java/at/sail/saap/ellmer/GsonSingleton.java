package at.sail.saap.ellmer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSingleton {
    private static Gson instance;

    private GsonSingleton() {
    }

    public static Gson getInstance() {
        if (instance == null) {
            instance = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
        return instance;
    }
}
