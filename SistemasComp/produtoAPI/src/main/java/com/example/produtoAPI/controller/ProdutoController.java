package com.example.produtoAPI.controller;

import com.example.produtoAPI.ProdutoApiApplication;
import com.example.produtoAPI.module.Produto;
import com.example.produtoAPI.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produtc data received: " + produto);
        var idProduto = UUID.randomUUID().toString();
        long idLong = Long.parseLong(idProduto.substring(0, 8), 16);
        produto.setId((int) idLong);
        produtoRepository.save(produto);
        return produto;
    }

}
