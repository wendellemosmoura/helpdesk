package com.wendel.helpdesk.services;

import com.wendel.helpdesk.domain.Pessoa;
import com.wendel.helpdesk.domain.Tecnico;
import com.wendel.helpdesk.domain.dtos.TecnicoDTO;
import com.wendel.helpdesk.repositories.PessoaRepository;
import com.wendel.helpdesk.repositories.TecnicoRepository;
import com.wendel.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.wendel.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDto);
        oldObj = new Tecnico(objDto);
        return tecnicoRepository.save(oldObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
        }
    }
}
