package com.exemple.algamoney.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.exemple.algamoney.model.Categoria_;
import com.exemple.algamoney.model.Lancamento;
import com.exemple.algamoney.model.Lancamento_;
import com.exemple.algamoney.model.Pessoa_;
import com.exemple.algamoney.repository.filter.LancamentoFilter;
import com.exemple.algamoney.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentorepositoryQuery {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = entityManager.createQuery(criteria);
		adicionarRestricoesPaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	
	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		
		criteria.select(builder.construct(ResumoLancamento.class
				, root.get(Lancamento_.codigo), root.get(Lancamento_.descricao)
				,root.get(Lancamento_.dataVencimento), root.get(Lancamento_.dataPagamento)
				,root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
				,root.get(Lancamento_.categoria).get((Categoria_.nome))
				,root.get(Lancamento_.pessoa).get((Pessoa_.nome))));
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoLancamento> query = entityManager.createQuery(criteria);
		adicionarRestricoesPaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}
	
	
	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!ObjectUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoDe()));
		}

		if (lancamentoFilter.getDataVencimentoAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRgistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRgistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRgistroPorPagina);
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return entityManager.createQuery(criteria).getSingleResult();
	}

	
}
