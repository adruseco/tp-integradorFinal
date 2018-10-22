/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;

/**
 *
 * @author mdominguez
 */
@Stateless
public class AutorService {
    @PersistenceContext(unitName = "RECETAS_PU")
    private EntityManager em;
    
    public Autor guardar(Autor a){
        if(a.getId()!=null && a.getId()>0) {
            return em.merge(a);
        }
        em.persist(a);
        em.flush();
        em.refresh(a);
        return a;
    }  
    
    public List<Autor> listar(){
        return em.createQuery("SELECT a FROM Autor a").getResultList();
    }
    /**************************************************************************/
    public Autor porId(Integer id){
        return this.em.find(Autor.class,id);
    }
    
    public void borrar(Integer id){
        this.em.remove(this.em.find(Autor.class,id));
    } 
     
     public List<Autor> porNombre(String nombre){
        return this.em.createQuery("SELECT a FROM Autor a WHERE a.nombre like :P_NOMBRE").setParameter("P_NOMBRE", nombre).getResultList();
}

    
    
    
}
