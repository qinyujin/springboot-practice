package com.aimer.component.json;

import com.aimer.component.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2020-10-03 16:50:00
 */
public class TransferJson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"小明","123456",1245));
        userList.add(new User(2,"小红","6542",54321));

        try {
            String userL = objectMapper.writeValueAsString(userList);
            System.out.println(userL);
            List list = objectMapper.readValue(userL, List.class);
            System.out.println(list);
            System.out.println(list.get(0));
            for (Object o : list) {
                String json = o.toString();
                json = json.replace("=", ":");
                json = json.replace("{", "{\"");
                json = json.replace(":", "\":\"");
                json = json.replace(",", "\",\"");
                json = json.replace("}", "\"}");
                json = json.replace(" ", "");
                User user = objectMapper.readValue(json, User.class);
                System.out.println(json);
                System.out.println(user.getName());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
