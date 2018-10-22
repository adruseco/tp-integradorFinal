/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;

/**
 *
 * @author Adru
 */
@FacesConverter("recetaConverter")
public class RecetaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String[] datos = string.split(";");
        Receta r = new Receta();
        r.setId(Integer.valueOf(datos[0]));
        r.setDescripcion(datos[1]);
        r.setTitulo(datos[2]);
        r.setPrecio(Double.valueOf(datos[3]));
        return r;
    
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return t.toString();

    
    }
    
}