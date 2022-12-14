package com.alura.acessible.controller;

import com.alura.acessible.model.Estado;
import com.alura.acessible.services.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping
    public Page<Estado> index(@PageableDefault(size=50) Pageable paginacao){
        return service.listAll(paginacao);
    }

    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody @Valid Estado classe){
        service.save(classe);
        return ResponseEntity.status(HttpStatus.CREATED).body(classe);
    }

    @GetMapping("{id}")
    public ResponseEntity<Estado> show(@PathVariable Long id){
        return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Estado> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody @Valid Estado newEstado){
        // carregar a tarefa do banco
        Optional<Estado> optional = service.get(id);

        // verificar se existe a tarefa com esse id
        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // atualizar os dados
        Estado classe = optional.get();
        BeanUtils.copyProperties(newEstado, classe);
        classe.setIdEstado(id);

        // salvar a tarefa
        service.save(classe);

        return ResponseEntity.ok(classe);

    }

}