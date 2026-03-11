package com.example.produtoAPI.repository;

import com.example.produtoAPI.module.Produto;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByName (String nome);
    List<Produto> findByPrice (String nome);
    List<Produto> findByWeight(String nome);
}
