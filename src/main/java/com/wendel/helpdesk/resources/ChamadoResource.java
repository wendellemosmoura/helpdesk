package com.wendel.helpdesk.resources;

import com.wendel.helpdesk.domain.Chamado;
import com.wendel.helpdesk.domain.dtos.ChamadoDTO;
import com.wendel.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }
}
