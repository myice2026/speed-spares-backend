package com.speedspares.service;

import com.speedspares.model.Taller;
import com.speedspares.repository.TallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TallerService {
    @Autowired
    private TallerRepository repository;

    public List<Taller> listarTalleres() {
        return repository.findAll();
    }
}