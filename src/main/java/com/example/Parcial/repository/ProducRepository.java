/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Parcial.repository;

import com.example.Parcial.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Martin
 */
public interface ProducRepository extends JpaRepository <Producto, Long>{
    
}
