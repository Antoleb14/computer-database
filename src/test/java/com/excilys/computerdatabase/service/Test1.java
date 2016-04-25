package com.excilys.computerdatabase.service;

import junit.framework.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.service.ServiceComputer;

public class Test1 {

    private static ServiceComputer sc;

    @BeforeClass
    public void beforeClass() {
        sc = ServiceComputer.getInstance();
    }

    @Test
    public void testCreateAndDelete() {

        Computer c = new Computer.ComputerBuilder().name("TEST").build();
        Computer n = sc.create(c);
        c.setId(n.getId());
        Assert.assertEquals(n, c);
        Assert.assertTrue(sc.delete(c));
    }

    @Test
    public void testUpdate() {
        Computer c = new Computer.ComputerBuilder().name("TEST").build();
        Computer n = sc.create(c);
        n.setName("BLOUGE");
        sc.update(n);

        Computer e = sc.find(n.getId());

        Assert.assertEquals(n.getName(), e.getName());
        Assert.assertTrue(sc.delete(n));
        Assert.assertTrue(sc.delete(e));
    }

    @Test
    public void testRead() {

    }

}
