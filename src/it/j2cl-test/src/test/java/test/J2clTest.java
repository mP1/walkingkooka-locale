package test;

import org.junit.Assert;
import org.junit.Test;

@com.google.j2cl.junit.apt.J2clTestInput(J2clTest.class)
public class J2clTest {
    @Test
    public void testAssert() {
        Assert.assertEquals(1, 3 - 2);
    }
}

