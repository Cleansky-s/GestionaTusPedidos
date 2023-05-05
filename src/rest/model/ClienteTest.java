package rest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testToString() {
        Cliente c = new Cliente("x","y","a","b","c");
        assertEquals(c.toString(),"Cliente [idCliente=x, nombre=y, telefono=b, direccion=a]");
    }

    @Test
    void getPoints() {
        Cliente c = new Cliente("x","y","a","b","c");
        assertEquals(c.getPoints(),0);

    }

    @Test
    void setPoints() {
        Cliente c = new Cliente("x","y","a","b","c");
        c.setPoints(100);
        assertEquals(c.getPoints(),100);
    }
}