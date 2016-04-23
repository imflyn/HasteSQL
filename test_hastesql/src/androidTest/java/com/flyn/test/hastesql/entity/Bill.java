package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Bill implements HasteModel
{

    @PrimaryKey
    public String orderId;

    public ArrayList<Message> messageList;

    public User[] users;

    public People people;

    public static class Message
    {
        public String text;

        public Message(String text)
        {
            this.text = text;
        }

        @Override
        public String toString()
        {
            return "Message{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    public static class User
    {

        public String name;

        public User(String name)
        {
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString()
    {
        return "Bill{" +
                "orderId='" + orderId + '\'' +
                ", messageList=" + messageList +
                ", users=" + Arrays.toString(users) +
                ", people=" + people +
                '}';
    }
}
