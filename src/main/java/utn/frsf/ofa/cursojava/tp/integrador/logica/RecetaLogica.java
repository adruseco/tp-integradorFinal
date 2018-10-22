/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.logica;

import java.math.BigInteger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.util.CantidadIngredientesMax;
import utn.frsf.ofa.cursojava.tp.integrador.util.CantidadMaximaRecetas;

/**
 *
 * @author mdominguez
 */
@RequestScoped
public class RecetaLogica {
    
    @PersistenceContext(unitName = "RECETAS_PU")
    private EntityManager em;
    
    @CantidadMaximaRecetas @Inject 
    private Integer maxRecetas;
    
    @CantidadIngredientesMax @Inject 
    private Integer maxIngredientes;

    
    @Transactional
    public boolean autorPuedeCrearReceta(Receta r){
        Integer idAutor = r.getAutor().getId();
        Long resultado = (Long) em.createQuery("SELECT Count(1) FROM Receta r WHERE r.autor.id = :P_ID_AUTOR")
                .setParameter("P_ID_AUTOR", idAutor).getSingleResult();
        return resultado.intValue()<maxRecetas;
                
    }
    
    public boolean puedeAgregarIngredientes(Receta r){
        return r.getIngredientes().size()<maxIngredientes;                
    }

    public boolean costoIngredientesValido(Receta r){
        Double precio = 0.0;
        for(Ingrediente i : r.getIngredientes()){
            precio += i.getCosto();
            System.out.println("Costo "+i.getCosto());
            System.out.println("PRECIO ACTUAL "+precio);
            if(precio>r.getPrecio()) return false;
        }
        return true;
    }

    public Integer getMaxRecetas() {
        return maxRecetas;
    }

    public void setMaxRecetas(Integer maxRecetas) {
        this.maxRecetas = maxRecetas;
    }

    public Integer getMaxIngredientes() {
        return maxIngredientes;
    }

    public void setMaxIngredientes(Integer maxIngredientes) {
        this.maxIngredientes = maxIngredientes;
    }


    
}
