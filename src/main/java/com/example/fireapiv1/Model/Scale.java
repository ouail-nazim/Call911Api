package com.example.fireapiv1.Model;

import com.example.fireapiv1.Helpers.ScaleID;
import com.example.fireapiv1.Helpers.State;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "scale")
//@IdClass(ScaleID.class)
public class Scale {
    @Id
    @SequenceGenerator(name = "scale_sequence", sequenceName = "scale_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "scale_sequence")
    Long id;

    public Scale() {}
    public Scale(Client client, Fire fire, State state) {
        this.client = client;
        this.fire = fire;
        this.state = state;
        this.ConfirmedAt = LocalDateTime.now();
    }
    //@Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    //@Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fire_id", referencedColumnName = "id")
    private Fire fire;

    @Column(name = "state", nullable = false)
    private State state;
    @Column(name = "confirmed_at", nullable = false)
    private LocalDateTime ConfirmedAt;


}
