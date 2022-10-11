package com.exemple.algamoney.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.exemple.algamoney.model.Lancamento;
import com.exemple.algamoney.model.Pessoa;
import com.exemple.algamoney.repository.LancamentoRepository;
import com.exemple.algamoney.repository.PessoaRepository;
import com.exemple.algamoney.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		if(pessoa == null || pessoa.get().isInativo()) {
			 throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
	

	
	
	
	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento LancamentoSalvar = lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return LancamentoSalvar;
	}


	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento LancamentoSalvar = buscarLancamentoPeloCodigo(codigo);

		BeanUtils.copyProperties(lancamento, LancamentoSalvar, "codigo");
		return lancamentoRepository.save(LancamentoSalvar);
		
	}
}
