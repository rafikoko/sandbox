package com.microservices.rentaloffer;

import java.io.IOException;

public class NeedRafal {

    public static void main(String[] args) {
        String host = args[0];
        String busName = args[1];

        NeedRafal.publish(host, busName);
    }

    public static void publish(String host, String busName) {
        try (Connections connection = new Connections(host, busName)) {
            connection.publish(new NeedPacket("Rafal").toJson());
        } catch (Exception e) {
            throw new RuntimeException("Could not publish message:", e);
        }
    }
}
