package com.example.produtoAPI.controller;

import com.example.produtoAPI.ProdutoApiApplication;
import com.example.produtoAPI.module.Produto;
import com.example.produtoAPI.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{idURL}")
    public Produto selecionarProdutoID(@PathVariable("idURL") String id){
        int idInt = Integer.parseInt(id);
        return  produtoRepository.findById(idInt).orElse(null);
    }

    @GetMapping
    public List<Produto> pesquisarDadosProduto(@RequestParam("nome") String nome) {
        return produtoRepository.findByName(nome);
    }

    @GetMapping("/preco")
    public List<Produto> pesquisarDadosPreco(@RequestParam("preco") String preco) {
        return produtoRepository.findByPrice(preco);
    }

    @GetMapping("/peso")
    public List<Produto> pesquisarDadosPeso(@RequestParam("peso") String peso) {
        return produtoRepository.findByWeight(peso);
    }

    @DeleteMapping("/{idURL}")
    public void apagarProdutoID(@PathVariable("idURL") Integer id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{idURL}")
    public void atualizarProdutoID(@PathVariable("idURL") Integer id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

}
