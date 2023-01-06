package com.projetmodule.gestiondestock.controller;

import com.projetmodule.gestiondestock.controller.Api.VentesApi;
import com.projetmodule.gestiondestock.dto.VentesDto;
import com.projetmodule.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VentesController implements VentesApi {

    private VentesService ventesService;
    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findById(Long id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Long id) {
       ventesService.delete(id);
    }
}
