package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class AssertTest {

    @Test
    public void test(){
        assertTrue(true);
        assertFalse(false);
        assertEquals(1, 1);
        assertEquals(Math.PI, 3.14, 0.01);

        int i = 5;
        Integer i2 = 5;
        assertEquals(i, i2.intValue());
        assertEquals(Integer.valueOf(i), i2);

        assertEquals("bola", "bola");
        assertTrue("bola".equalsIgnoreCase("Bola"));
        assertTrue("bola".startsWith("bola"));

        Usuario u1 = new Usuario("Usuário 1");
        Usuario u2 = new Usuario("Usuário 2");
        Usuario u3 = u2;
        Usuario u4 = null;
        assertEquals(u1, u2);

        //mesma instance
        assertSame(u2, u3);

        assertTrue(u3 == null);
        assertNull(u4);
    }
}
