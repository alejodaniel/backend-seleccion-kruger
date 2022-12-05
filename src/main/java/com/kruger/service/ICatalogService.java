package com.kruger.service;

import java.util.List;

import com.kruger.model.Catalog;

public interface ICatalogService extends ICRUD<Catalog> {
    List<Catalog> getCatalogByParentId(Integer parentId);

    void updateLogicalDelete(Integer catalogId);

    List<Catalog> getAllParent();

    Catalog register(Catalog catalog);

}
