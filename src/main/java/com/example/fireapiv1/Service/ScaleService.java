package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Fire;
import com.example.fireapiv1.Model.Scale;
import com.example.fireapiv1.Repository.FireRepository;
import com.example.fireapiv1.Repository.ScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaleService {

    private final ScaleRepository scaleRepository;

    @Autowired
    public ScaleService(ScaleRepository scaleRepository) {
        this.scaleRepository = scaleRepository;
    }

    public List<Scale> getAllScales(){
        return scaleRepository.findAll();
    }
     public Scale saveScale(Scale scale){
        return scaleRepository.save(scale);
     }
}
