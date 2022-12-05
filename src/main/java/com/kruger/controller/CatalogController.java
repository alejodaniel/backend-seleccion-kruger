package com.kruger.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.model.Catalog;
import com.kruger.resp.BaseResp;
import com.kruger.service.ICatalogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.kruger.util.Constants.*;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private ICatalogService catalogService;

    private LocalDateTime localDateTime = LocalDateTime.now();
    private Timestamp date = Timestamp.valueOf(localDateTime);

    /* MÉTODO ---> Obtener el catálogo por el id padre */
    @Operation(summary = "Obtener un Catálogo por parentId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtener Catálogo", content = {
                    @Content(mediaType = TYPES_RESPONSE, schema = @Schema(implementation = Catalog.class)) }),

    })

    @PreAuthorize(AUTHORITY_ADMIN)
    @GetMapping(value = "/{parentId}", produces = TYPES_RESPONSE)
    public BaseResp getCatalogByParentId(@PathVariable("parentId") Integer parentId) {
        List<Catalog> catalog;
        BaseResp baseResp = new BaseResp();
        catalog = catalogService.getCatalogByParentId(parentId);
        baseResp.setStatusCode(CODE_OK);
        baseResp.setMessage(OK);
        baseResp.setData(catalog);
        return baseResp;
    }
    /* FIN_MÉTODO ---> Obtener el catálogo por el id padre */

    /* MÉTODO ---> Obtener todos los catálogos padres */
    @Operation(summary = "Obtener todos los catálogos padres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtener Catálogo", content = {
                    @Content(mediaType = TYPES_RESPONSE, schema = @Schema(implementation = Catalog.class)) }),

    })
    @PreAuthorize(AUTHORITY_ADMIN)
    @GetMapping(value = "/allParent", produces = TYPES_RESPONSE)
    public BaseResp getAllParent() {
        List<Catalog> catalog;
        BaseResp baseResp = new BaseResp();
        catalog = catalogService.getAllParent();
        baseResp.setStatusCode(CODE_OK);
        baseResp.setMessage(OK);
        baseResp.setData(catalog);
        return baseResp;
    }

    @Operation(summary = "Crear catálogos padres e hijos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Crear catálogo", content = {
                    @Content(mediaType = TYPES_RESPONSE, schema = @Schema(implementation = Catalog.class)) }),

    })
    @PreAuthorize(AUTHORITY_ADMIN)
    @PostMapping(produces = TYPES_RESPONSE, consumes = TYPES_RESPONSE)
    public BaseResp register(@RequestBody Catalog catalog) {
        BaseResp baseResp = new BaseResp();
        catalog.setCreatedAt(date);
        catalog.setCreatedBy("ADMIN");
        catalog.setIsDeleted(0);
        baseResp.setStatusCode(CODE_CREATED);
        baseResp.setMessage(OK_CREATED);
        baseResp.setData(catalog);
        catalogService.register(catalog);
        return baseResp;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/{id}")
    public BaseResp deleted(@PathVariable("id") Integer id) {
        System.out.println(id);
        BaseResp baseResp = new BaseResp();
        baseResp.setStatusCode(CODE_OK);
        baseResp.setMessage(OK_DELETED);
        baseResp.setData("catalog");
        catalogService.updateLogicalDelete(id);
        return baseResp;
    }

    @PreAuthorize(AUTHORITY_ADMIN)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(ID) Integer id) {
        catalogService.remove(id);
    }
}
