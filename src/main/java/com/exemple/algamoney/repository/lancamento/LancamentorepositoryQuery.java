package com.exemple.algamoney.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exemple.algamoney.model.Lancamento;
import com.exemple.algamoney.repository.filter.LancamentoFilter;
import com.exemple.algamoney.repository.projection.ResumoLancamento;

public interface LancamentorepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
