package Utils;

import TestDataTypes.User;
import com.google.gson.Gson;

import java.io.*;

public class TestDataUtils {

    private final String userFilePath = "src/main/resources/testDataResources/user.json";
    private User user;

    public User getUserData() {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(userFilePath));
            user = gson.fromJson(bufferReader, User.class);
            return user;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + userFilePath);
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
            } catch (IOException ignore) {
            }
        }
    }
}
