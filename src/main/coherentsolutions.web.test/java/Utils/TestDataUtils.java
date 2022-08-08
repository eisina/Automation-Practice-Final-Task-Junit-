package Utils;

import TestDataTypes.User;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.*;

public class TestDataUtils {

    private final String userFilePath = "src/main/resources/testDataResources/user.json";
    private User user;

    public User getData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        user = objectMapper.readValue(new File(userFilePath), User.class);
        return user;
    }
}
