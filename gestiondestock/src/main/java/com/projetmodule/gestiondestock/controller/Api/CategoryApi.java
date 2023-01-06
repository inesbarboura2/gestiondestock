package com.projetmodule.gestiondestock.controller.Api;

import com.projetmodule.gestiondestock.dto.ArticleDto;
import com.projetmodule.gestiondestock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projetmodule.gestiondestock.utils.Constants.APP_ROOT;

public interface CategoryApi {
    @PostMapping(value =APP_ROOT+ "/categories/create" ,
            consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT+"/categories/{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Long id);

//    @GetMapping(value = APP_ROOT+"/categories/{codeCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
//    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);


    @GetMapping(value = APP_ROOT+"/categories/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();


    @DeleteMapping(value= APP_ROOT+"/categories/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Long id);
}
