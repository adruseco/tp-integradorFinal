package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.IngredienteService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mdominguez
 */
@SessionScoped
@Named("recetaController")
public class RecetaController implements Serializable {

    @Inject
     private RecetaService recetaSrv;

    @Inject  
    private IngredienteService ingredienteSrv;

    private Receta recetaSeleccionada;
    private Autor autorSeleccionado;
    private List<Receta> listaRecetas;
    private List<Receta> resultadoBusqueda;
    private DualListModel<Ingrediente> ingredientesDisponibles;
    private Ingrediente ingredienteSeleccionado;
    private Double precioMinimo;
    private Double precioMaximo;
    private Date fechaIni;
    private Date fechaFinal;
    
    
       
    public Receta getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(Receta recetaSeleccionada) {
        this.recetaSeleccionada = recetaSeleccionada;
        this.recetaSeleccionada .setIngredientes(recetaSrv.ingredientesPorIdReceta(recetaSeleccionada.getId()));
        this.ingredientesDisponibles.setTarget(recetaSeleccionada.getIngredientes());       
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @PostConstruct
    public void init() {
        this.recetaSeleccionada = null;
        this.listaRecetas = recetaSrv.listar();
        List<Ingrediente> origen = ingredienteSrv.listar();
        List<Ingrediente> destino = new ArrayList<Ingrediente>();
        this.ingredientesDisponibles = new DualListModel<>(origen, destino);        
    }

    public DualListModel<Ingrediente> getIngredientesDisponibles() {
        return ingredientesDisponibles;
    }

    public void setIngredientesDisponibles(DualListModel<Ingrediente> ingredientesDisponibles) {
        this.ingredientesDisponibles = ingredientesDisponibles;
    }

    public String guardar() {
        recetaSeleccionada.setIngredientes(this.ingredientesDisponibles.getTarget());
        recetaSeleccionada.setAutor(autorSeleccionado);
        Receta tmp = this.recetaSrv.guardar(recetaSeleccionada);
        this.listaRecetas.add(tmp);
        this.recetaSeleccionada = null;
        return null;
    }
    
    public String nuevo() {
        this.recetaSeleccionada = new Receta();
        this.recetaSeleccionada.setIngredientes(new ArrayList<>());
        this.ingredientesDisponibles.setTarget(new ArrayList<Ingrediente>());
        return null;
    }

    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }
        
    public void setAutorSeleccionado(Autor autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    public Ingrediente getIngredienteSeleccionado() {
        return ingredienteSeleccionado;
    }

    public void setIngredienteSeleccionado(Ingrediente ingredienteSeleccionado) {
        this.ingredienteSeleccionado = ingredienteSeleccionado;
    }

    public List<Receta> getResultadoBusqueda() {
        return resultadoBusqueda;
    }

    public void setResultadoBusqueda(List<Receta> resultadoBusqueda) {
        this.resultadoBusqueda = resultadoBusqueda;
    }

    public Double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(Double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public Double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(Double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String buscar(){
        this.resultadoBusqueda = recetaSrv.busquedaAvanzada(precioMinimo, precioMaximo, fechaIni, fechaFinal, autorSeleccionado, ingredienteSeleccionado );
        return null;
    
    }
}
