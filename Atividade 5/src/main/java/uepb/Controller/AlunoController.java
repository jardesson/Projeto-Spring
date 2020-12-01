package uepb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import uepb.Model.Aluno;
import uepb.Repository.AlunoRepository;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/alunos")
@Api(value = "Alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;

    @ApiOperation(value = "Busca a lista de todos os alunos")
    @GetMapping
    public Iterable<Aluno> getAlunos() {
        return repository.findAll();
    }

    @ApiOperation(value = "Busca um aluno pelo seu id")
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> getAlunoById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(repository.findById(Long.parseLong(id)));
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Cadastra um novo aluno")
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> criarAluno(@RequestBody Aluno obj) {
        try {
            return ResponseEntity.ok(repository.save(obj));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Atualiza as informações de um aluno")
    @PutMapping("{id}")
    public ResponseEntity<?> alterarAluno(@PathVariable String id, @RequestBody Aluno obj) {
        try {
            return ResponseEntity.ok(repository.save(obj));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Deleta um aluno pelo seu id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarAluno(@PathVariable Long id){
        try {
            repository.delete(repository.findById(id).get());
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}