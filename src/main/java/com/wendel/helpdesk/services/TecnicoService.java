package com.wendel.helpdesk.services;

import com.wendel.helpdesk.domain.Tecnico;
import com.wendel.helpdesk.repositories.TecnicoRepository;
import com.wendel.helpdesk.resources.TecnicoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElse(null);
    }
}
