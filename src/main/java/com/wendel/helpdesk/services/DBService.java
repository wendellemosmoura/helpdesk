package com.wendel.helpdesk.services;

import com.wendel.helpdesk.domain.Chamado;
import com.wendel.helpdesk.domain.Cliente;
import com.wendel.helpdesk.domain.Tecnico;
import com.wendel.helpdesk.domain.enums.Perfil;
import com.wendel.helpdesk.domain.enums.Prioridade;
import com.wendel.helpdesk.domain.enums.Status;
import com.wendel.helpdesk.repositories.ChamadoRepository;
import com.wendel.helpdesk.repositories.ClienteRepository;
import com.wendel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Wendel Lemos", "02536925814", "wendel@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "32165498700", "linus@mail.com", "321");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
