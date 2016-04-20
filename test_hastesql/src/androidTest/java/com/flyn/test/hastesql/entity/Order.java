package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.List;

public class Order implements HasteModel
{

    @PrimaryKey
    public String orderId;

    public List<Message> messageList;

    public User[] users;

    public static class Message
    {
        public Message(String text)
        {
            this.text = text;
        }

        public String text;

    }

    public static class User
    {

        public String name;

        public User(String name)
        {
            this.name = name;
        }
    }

}
