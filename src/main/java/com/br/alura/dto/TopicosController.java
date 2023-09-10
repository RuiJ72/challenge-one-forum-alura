package com.br.alura.dto;

import java.net.URI;
import java.util.List;

import com.br.alura.modelo.Topico;
import com.br.alura.repository.CursoRepository;
import com.br.alura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista() {
        List<Topico> topicos = topicoRepository.findAll();
        return TopicoDTO.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Validated TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @PostMapping("{id}")
    public TopicoDTO detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.getOne(id);
        return new TopicoDTO(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Validated TopicoForm form) {
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDTO(topico) );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
