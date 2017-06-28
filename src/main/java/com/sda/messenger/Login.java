package com.sda.messenger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kornel on 28.06.17.
 */
public class Login {
    private Map<String, String> myMapOfIpsAndNames;

    public Login() {
        myMapOfIpsAndNames = new HashMap<>();
        myMapOfIpsAndNames.put("192.168.2.119", "Andrzej");
        myMapOfIpsAndNames.put("192.168.2.115", "Miłosz");
        myMapOfIpsAndNames.put("192.168.2.126", "Mateusz S.");
        myMapOfIpsAndNames.put("192.168.2.128", "Marcin");
        myMapOfIpsAndNames.put("127.0.0.1", "Kornel");
        myMapOfIpsAndNames.put("192.168.2.71", "Piotrek");
    }


    public String findUser(String ip){
        return myMapOfIpsAndNames.getOrDefault(ip, "Unknown");
    }
}
