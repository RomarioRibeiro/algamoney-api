package com.exemple.algamoney.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.algamoney.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	List<Pessoa> findByNome(String nome);
	public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);	
}
