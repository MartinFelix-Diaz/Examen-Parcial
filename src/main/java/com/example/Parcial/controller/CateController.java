/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Parcial.controller;

import com.example.Parcial.entity.Categoria;
import com.example.Parcial.repository.CateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Martin
 */
@Controller
public class CateController {
    @Autowired
    private CateRepository CateRepository;
    @RequestMapping("/categorias")
    public String post(Model model){
        model.addAttribute("categorias",CateRepository.findAll());
        return "categoria";
    }
    @RequestMapping("/formCate")
    public String create(Model model) {
        return "addCate";
    }
    @RequestMapping("/addCate")
    public String guardar(@RequestParam String Nombre,Model model) {
        Categoria categoria = new Categoria();
        categoria.setNombre(Nombre);
        System.out.println("sout:"+categoria.getNombre());
        CateRepository.save(categoria);
        return "redirect:/categorias";
    }
    @RequestMapping("/delCate/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Categoria categoria = CateRepository.findById(id).orElse(null);
        CateRepository.delete(categoria);
        return "redirect:/categorias";
    }
    @RequestMapping("/editCate/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("categoria", CateRepository.findById(id).orElse(null));
        return "editCate";
    }
    @RequestMapping("/updateCate")
    public String update(@RequestParam Long id,@RequestParam String Nombre) {
        Categoria categoria = CateRepository.findById(id).orElse(null);
        categoria.setNombre(Nombre);
        CateRepository.save(categoria);
        return "redirect:/categorias";
}
}
