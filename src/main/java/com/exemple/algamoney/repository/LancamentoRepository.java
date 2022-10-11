package com.exemple.algamoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.algamoney.model.Lancamento;
import com.exemple.algamoney.repository.lancamento.LancamentorepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentorepositoryQuery{


}
