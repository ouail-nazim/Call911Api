package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Fire;
import com.example.fireapiv1.Repository.FireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FireService {

    private final FireRepository fireRepository;

    @Autowired
    public FireService(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    public List<Fire> getAllFires() {
        return fireRepository.findAll();
    }
    public Optional<Fire> findFire(Long id) {
        return fireRepository.findById(id);
    }

    public Fire saveFire(Fire fire) {
        return fireRepository.save(fire);
    }
}
