package rest.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void getType() {
    }

    @Test
    void addPlato() {
    }

    @Test
    void removePlato() {
    }

    @Test
    void customPlato() {
    }

    @Test
    void getCuenta() {
        List<Plato> pl= new ArrayList<Plato>();
        //String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP
        Pedido p = new PedidoDomicilio("a","b",pl,1.00,"c","d","e");
        assertEquals(p.getCuenta(),1.00);
    }

    @Test
    void getId() {
        List<Plato> pl= new ArrayList<Plato>();
        //String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP
        Pedido p = new PedidoDomicilio("a","b",pl,1.00,"c","d","e");
        assertEquals(p.getId(),"a");
    }

    @Test
    void getIdCliente() {
        List<Plato> pl= new ArrayList<Plato>();
        //String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP
        Pedido p = new PedidoDomicilio("a","b",pl,1.00,"c","d","e");
        assertEquals(p.getIdCliente(),"b");
    }

    @Test
    void getIdMesa() {
    }

    @Test
    void getPlatos() {
    }

    @Test
    void getDireccion() {
        List<Plato> pl= new ArrayList<Plato>();
        //String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP
        Pedido p = new PedidoDomicilio("a","b",pl,1.00,"c","d","e");
        assertEquals(p.getDireccion(),"c");
    }

    @Test
    void getTelefono() {
        List<Plato> pl= new ArrayList<Plato>();
        //String id, String idCliente, List<Plato> platos, Double cuenta, String direccion, String telefono, String CP
        Pedido p = new PedidoDomicilio("a","b",pl,1.00,"c","d","e");
        assertEquals(p.getTelefono(),"d");
    }

    @Test
    void getCP() {
    }
}