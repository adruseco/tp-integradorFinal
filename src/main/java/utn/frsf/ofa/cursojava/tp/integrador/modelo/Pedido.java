/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.modelo;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Adru
 */
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;
    private Date fechaPedido;
    private String direccionEnvio;
    @OneToMany(mappedBy="pedido")
    private List<Receta> recetasPedidas;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public List<Receta> getRecetasPedidas() {
        return recetasPedidas;
    }

    public void setRecetasPedidas(List<Receta> recetasPedidas) {
        this.recetasPedidas = recetasPedidas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idPedido);
        hash = 67 * hash + Objects.hashCode(this.fechaPedido);
        hash = 67 * hash + Objects.hashCode(this.direccionEnvio);
        hash = 67 * hash + Objects.hashCode(this.recetasPedidas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.direccionEnvio, other.direccionEnvio)) {
            return false;
        }
        if (!Objects.equals(this.idPedido, other.idPedido)) {
            return false;
        }
        if (!Objects.equals(this.fechaPedido, other.fechaPedido)) {
            return false;
        }
        if (!Objects.equals(this.recetasPedidas, other.recetasPedidas)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
