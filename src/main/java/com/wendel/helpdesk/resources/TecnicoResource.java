package com.wendel.helpdesk.resources;

import com.wendel.helpdesk.domain.Tecnico;
import com.wendel.helpdesk.domain.dtos.TecnicoDTO;
import com.wendel.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico obj = this.tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDto) {
        Tecnico obj = tecnicoService.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
