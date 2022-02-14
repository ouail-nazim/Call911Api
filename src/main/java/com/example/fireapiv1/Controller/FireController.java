package com.example.fireapiv1.Controller;

import com.example.fireapiv1.Helpers.State;
import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Fire;
import com.example.fireapiv1.Model.Scale;
import com.example.fireapiv1.Repository.ClientRepository;
import com.example.fireapiv1.Service.FireService;
import com.example.fireapiv1.Service.ScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/fires")
public class FireController {

    private final FireService fireService;
    private final ScaleService scaleService;
    private final ClientRepository clientRepository;

    @Autowired
    public FireController(FireService fireService, ScaleService scaleService, ClientRepository clientRepository) {
        this.fireService = fireService;
        this.scaleService = scaleService;

        this.clientRepository = clientRepository;
    }

    /**
     * @return list of fires created
     */
    @GetMapping
    public List<Fire> getFirs(){
        return fireService.getAllFires();
    }

    /**
     * @param fire(latitude,longitude,street,city,zip,country)
     * @param uid(id of user)
     * @return new fire created and confirmed by client who have the id=uid
     */
    @PostMapping
    public Fire saveFire(
            @RequestBody Fire fire,
            @RequestParam("user_id") long uid
    ){
        Optional<Client> client = clientRepository.findById(uid);
        if (client.isPresent()){
            // create fire in bd
            Fire newfire= fireService.saveFire(client.get().createFire(fire));
            //confirm fire when created
            HashMap<String , Object> data = client.get().confirmFire(newfire, State.HIGH);
            fireService.saveFire((Fire) data.get("fire"));
            scaleService.saveScale((Scale) data.get("scale"));
            return newfire;
        }else {
            return null;
        }
    }

    @GetMapping("/confirm")
    public boolean confirmFire(
            @RequestParam("user_id") long uid,
            @RequestParam("fire_id") long fid
    ){
        Optional<Client> client = clientRepository.findById(uid);
        Optional<Fire> fire = fireService.findFire(fid);
        for (Object c : fire.get().getClientsConfirm().toArray()) {
            if (((Client) c).getId() ==client.get().getId()){
                return false;
            }
        }
        HashMap<String , Object> data = client.get().confirmFire(fire.get(), State.HIGH);
        fireService.saveFire((Fire) data.get("fire"));
        scaleService.saveScale((Scale) data.get("scale"));
        return true;
    }


}
