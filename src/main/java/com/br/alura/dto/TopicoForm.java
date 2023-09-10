package com.br.alura.dto;

import com.br.alura.modelo.Curso;
import com.br.alura.modelo.Topico;
import com.br.alura.repository.CursoRepository;
import com.br.alura.repository.TopicoRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

public class TopicoForm {

    @NotNull
    private String titulo;

    @NotNull
    private String menssagem;

    @NotNull
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getMenssagem() {
        return menssagem;
    }
    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, menssagem, curso);
    }

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.menssagem);

        return topico;
    }
}
