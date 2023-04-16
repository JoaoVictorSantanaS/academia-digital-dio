package me.dio.academia.digital.controller;

import lombok.extern.slf4j.Slf4j;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  private AlunoServiceImpl service;

  @PostMapping
  public Aluno create(@Valid @RequestBody AlunoForm form) {
    return service.create(form);
  }

  @GetMapping("/avaliacoes/{id}")
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) {
    return service.getAllAvaliacaoFisicaId(id);
  }
  @GetMapping("/{id}")
  public ResponseEntity<Object> getPorId(@PathVariable Long id) {
    try{
      return ResponseEntity.ok(service.get(id));
    } catch (EntityNotFoundException ex){
      log.error(ex.getMessage());
      return ResponseEntity
              .status(HttpStatus.NO_CONTENT)
              .body(ex.getMessage());
    } catch (Exception ex){
      log.error(ex.getMessage());
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body(ex.getMessage());
    }
  }
  @DeleteMapping("/{id}")
  public void deletePorId(@PathVariable Long id) {
    service.delete(id);
  }

  @GetMapping
  public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false)
                                  String dataDeNacimento){
    return service.getAll(dataDeNacimento);
  }


}
