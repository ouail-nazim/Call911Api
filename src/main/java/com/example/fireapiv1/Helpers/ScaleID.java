package com.example.fireapiv1.Helpers;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Fire;
import java.io.Serializable;

public class ScaleID implements Serializable {
    private Client client;
    private Fire fire;

    public ScaleID(Client client, Fire fire) {
        this.client = client;
        this.fire = fire;
    }
}
