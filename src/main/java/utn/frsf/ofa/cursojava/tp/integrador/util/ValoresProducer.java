/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.util;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


/**
 *
 * @author mdominguez
 */
@ApplicationScoped
public class ValoresProducer implements Serializable{
    
    private  Integer cantidadIngedientesMax = 5;

    private  Integer cantidadrRecetasMax = 3;
    
    @Produces @CantidadIngredientesMax    
    public Integer getCantidadIngedientesMax() {
        return cantidadIngedientesMax;
    }

    public void setCantidadIngedientesMax(Integer cantidadIngedientesMax) {
        this.cantidadIngedientesMax = cantidadIngedientesMax;
    }

    @Produces @CantidadMaximaRecetas
    public Integer getCantidadrRecetasMax() {
        return cantidadrRecetasMax;
    }

    public void setCantidadrRecetasMax(Integer cantidadrRecetasMax) {
        this.cantidadrRecetasMax = cantidadrRecetasMax;
    }
    
    
    

}

