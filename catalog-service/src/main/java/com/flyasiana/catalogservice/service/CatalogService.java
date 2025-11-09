package com.flyasiana.catalogservice.service;

import com.flyasiana.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
}
