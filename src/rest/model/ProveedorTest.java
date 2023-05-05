package rest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProveedorTest {

    @Test
    void getWeb() {
        Proveedor p = new Proveedor("String idPr", "String nombrePr", "String direccionPr", "String telefonoPr", "String emailPr", "String webPr", 10);
        assertEquals(p.getWeb(),"String webPr");
    }

    @Test
    void getDeuda() {
        Proveedor p = new Proveedor("String idPr", "String nombrePr", "String direccionPr", "String telefonoPr", "String emailPr", "String webPr", 10);
        assertEquals(p.getDeuda(),10);
    }

    @Test
    void sumaDeuda() {
        Proveedor p = new Proveedor("String idPr", "String nombrePr", "String direccionPr", "String telefonoPr", "String emailPr", "String webPr", 10);
        p.sumaDeuda(90);
        assertEquals(p.getDeuda(),100);
    }
}