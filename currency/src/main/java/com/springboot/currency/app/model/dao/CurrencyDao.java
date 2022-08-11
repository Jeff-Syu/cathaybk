package com.springboot.currency.app.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.currency.app.model.entity.CurrencyEntity;

@Repository
public interface CurrencyDao extends JpaRepository<CurrencyEntity, String> {
	List<CurrencyEntity> findAll();
	
	CurrencyEntity findByCurrencyCode(String currencyCode);
}
