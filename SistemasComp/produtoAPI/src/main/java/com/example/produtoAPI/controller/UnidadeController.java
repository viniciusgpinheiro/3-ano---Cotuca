package com.example.produtoAPI.controller;

import com.example.produtoAPI.module.Produto;
import com.example.produtoAPI.module.Unidade;
import com.example.produtoAPI.repository.ProdutoRepository;
import com.example.produtoAPI.repository.UnidadeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("unidade")
public class UnidadeController {

    private UnidadeRepository unidadeRepository;

    public UnidadeController(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @PostMapping
    public Unidade salvar(@RequestBody Unidade unidade) {
        System.out.println("Produtc data received: " + unidade);
        var idUnidade = UUID.randomUUID().toString();
        long idLong = Long.parseLong(idUnidade.substring(0, 8), 16);
        unidade.setUnitId((int) idLong);
        unidadeRepository.save(unidade);
        return unidade;
    }

    @GetMapping
    public List<Unidade> selecionarUnidades() {
        return unidadeRepository.findAll();
    }

    @GetMapping("/id")
    public Unidade selecionarUnidadeID(@RequestParam("id") Integer id) {
        return unidadeRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{idURL}")
    public void deletarUnidade(@PathVariable("idURL") Integer id) {
        unidadeRepository.deleteById(id);
    }

    @PutMapping("/{idURL}")
    public void atualizarUnidadeID(@PathVariable("idURL") Integer id, @RequestBody Unidade unidade) {
        unidade.setUnitId(id);
        unidadeRepository.save(unidade);
    }
}