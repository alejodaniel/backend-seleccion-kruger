package com.kruger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kruger.dao.ICatalogDAO;

import com.kruger.model.Catalog;

import com.kruger.service.ICatalogService;

@Service
public class CatalogServiceImpl implements ICatalogService {

	@Autowired
	private ICatalogDAO dao;

	@Override
	public List<Catalog> getCatalogByParentId(Integer parentId) {
		return dao.getCatalogByParentId(parentId);
	}

	@Override
	public List<Catalog> getAllParent() {
		return dao.getAllParent();
	}

	@Override
	public Catalog register(Catalog catalog) {
		return dao.save(catalog);
	}

	@Override
	public void updateLogicalDelete(Integer catalogId) {
		dao.updateLogicalDelete(catalogId);
	}

	@Override
	public Catalog update(Catalog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		dao.deleteById(id);

	}

	@Override
	public Catalog getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Catalog> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
