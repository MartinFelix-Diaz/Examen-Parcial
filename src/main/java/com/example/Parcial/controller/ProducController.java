/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Parcial.controller;

import com.example.Parcial.entity.Producto;
import com.example.Parcial.repository.ProducRepository;
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
public class ProducController {
    @Autowired
    private ProducRepository ProducRepository;
    @RequestMapping("/main")
    public String mensaje(Model model){
        model.addAttribute("mensaje", "Bienvenidos Thymeleaf");
        return "main";
    }
    @RequestMapping("/")
    public String mensa(Model model){
        model.addAttribute("mensa", "Bienvenidos Thymeleaf");
        return "index";
    }
    @RequestMapping("/productos")
    public String post(Model model){
        model.addAttribute("productos", ProducRepository.findAll());
        return "producto";
    }
    @RequestMapping("/formProduc")
    public String create(Model model) {
        return "addProduc";
    }
    @RequestMapping("/addProduc")
    public String guardar(@RequestParam String Nombre, @RequestParam double Precio,@RequestParam int Stock, Model model) {
        Producto producto = new Producto();
        producto.setNombre(Nombre);
        producto.setPrecio(Precio);
        producto.setStock(Stock);
        System.out.println("sout:"+producto.getNombre()+"/"+producto.getPrecio()+"/"+producto.getStock());
        ProducRepository.save(producto);
        return "redirect:/productos";
    }
    @RequestMapping("/delProduc/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Producto producto = ProducRepository.findById(id).orElse(null);
        ProducRepository.delete(producto);
        return "redirect:/productos";
    }
    @RequestMapping("/editProduc/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("producto", ProducRepository.findById(id).orElse(null));
        return "editProduc";
    }
    @RequestMapping("/updateProduc")
    public String update(@RequestParam Long id,@RequestParam String Nombre, @RequestParam double Precio,@RequestParam int Stock) {
        Producto producto = ProducRepository.findById(id).orElse(null);
        producto.setNombre(Nombre);
        producto.setPrecio(Precio);
        producto.setStock(Stock);
        ProducRepository.save(producto);
        return "redirect:/productos";
    }
}
