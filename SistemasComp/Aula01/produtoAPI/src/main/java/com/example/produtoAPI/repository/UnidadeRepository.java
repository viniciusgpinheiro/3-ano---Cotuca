package com.example.produtoAPI.repository;

import com.example.produtoAPI.module.Produto;
import com.example.produtoAPI.module.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {

}
