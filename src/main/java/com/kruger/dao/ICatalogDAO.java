package com.kruger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static com.kruger.util.Constants.*;
import com.kruger.model.Catalog;

@Repository
@EnableTransactionManagement
public interface ICatalogDAO extends JpaRepository<Catalog, Integer> {

        @Query(value = SELECT_QUERY
                        + " catalog where parent_id is null and is_deleted = 0", nativeQuery = true)
        List<Catalog> getAllParent();

        @Query(value = SELECT_QUERY
                        + " catalog cat where cat.parent_id = :parentId and is_deleted = 0 order by cat.name asc", nativeQuery = true)
        List<Catalog> getCatalogByParentId(@Param("parentId") Integer parentId);

        @Modifying()
        @Transactional
        @Query(value = "UPDATE catalog SET is_deleted = 1 WHERE id_catalog = :idCatalog", nativeQuery = true)
        public void updateLogicalDelete(@Param("idCatalog") Integer idCatalog);
}
