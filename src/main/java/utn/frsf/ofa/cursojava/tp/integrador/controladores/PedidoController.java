/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Pedido;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.PedidoService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/**
 *
 * @author Adru
 */
@SessionScoped
@Named("pedidoController")
public class PedidoController implements Serializable{
    
   @Inject  private PedidoService pedidoService;
   @Inject  private RecetaService recetaService;
   
    private Pedido pedidoSeleccionado;
    private DualListModel<Receta> recetasDisponibles;
    private List<Pedido> listaPedidos;
    private Receta recetaSeleccionada;
    
    @PostConstruct
    public void init(){
        this.pedidoSeleccionado = null;
        this.listaPedidos = pedidoService.listar();
        List<Receta> origen = recetaService.listar();
        List<Receta> destino = new ArrayList<Receta>();
        this.recetasDisponibles = new DualListModel<>(origen, destino);   
        
    
   }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public DualListModel<Receta> getRecetasDisponibles() {
        return recetasDisponibles;
    }

    public void setRecetasDisponibles(DualListModel<Receta> recetasDisponibles) {
        this.recetasDisponibles = recetasDisponibles;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Receta getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(Receta recetaSeleccionada) {
        this.recetaSeleccionada = recetaSeleccionada;
    }

     public String guardar() {
         
         pedidoSeleccionado.setRecetasPedidas(this.recetasDisponibles.getTarget());
         Pedido p = this.pedidoService.guardar(pedidoSeleccionado);
         this.listaPedidos.add(p);
         this.pedidoSeleccionado = null;
         return null;              
     
    }
    
    public String nuevo() {
        this.pedidoSeleccionado = new Pedido();
        this.pedidoSeleccionado.setRecetasPedidas(new ArrayList<>());
        this.recetasDisponibles.setTarget(new ArrayList<Receta>());
        return null;
    }

   

 
    
}
