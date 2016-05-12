package com.excilys.computerdatabase.service;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.ServiceException;

public class ServiceTest {

    // private static ServiceComputer mockedSComputer;
    private static Computer c1;
    private static Computer c2;
    //
    // @BeforeClass
    // public static void setUp() {
    // mockedSComputer = mock(ServiceComputer.class);
    // Whitebox.setInternalState(ServiceComputer.class, "INSTANCE",
    // mockedSComputer);
    //
    // // Create few instances of Book class.
    // c1 = new
    // Computer.ComputerBuilder().id(1000L).introduced(LocalDateTime.of(2015,
    // 10, 15, 0, 0, 0)).name("TEST")
    // .build();
    //
    // c2 = new
    // Computer.ComputerBuilder().id(1001L).introduced(LocalDateTime.of(2015,
    // 10, 15, 0, 0, 0))
    // .discontinued(LocalDateTime.of(2016, 10, 15, 0, 0,
    // 0)).name("TEST2").build();
    //
    // // Stubbing the methods of mocked BookDAL with mocked data.
    // when(mockedSComputer.findAll()).thenReturn(Arrays.asList(c1, c2));
    // when(mockedSComputer.find(1000L)).thenReturn(c1);
    // when(mockedSComputer.create(c1)).thenReturn(c1);
    // when(mockedSComputer.update(c1)).thenReturn(c1);
    // when(mockedSComputer.delete(c1)).thenReturn(true);
    // }
    //
    // @Test
    // public void testGetComputers() {
    // List<Computer> allPCs = mockedSComputer.findAll();
    // assertEquals(2, allPCs.size());
    // Computer computer = allPCs.get(0);
    // assertEquals(new Long(1000), computer.getId());
    // assertEquals("TEST", computer.getName());
    // assertEquals(LocalDateTime.of(2015, 10, 15, 0, 0, 0),
    // computer.getIntroduced());
    // assertEquals(null, computer.getDiscontinued());
    // }
    //
    // @Test
    // public void testUpdateComputers() {
    // Computer comp = mockedSComputer.update(c1);
    // assertNotNull(comp);
    // assertEquals(comp, c1);
    // }
    //
    // @Test
    // public void testRead() {
    // Computer comp = mockedSComputer.create(c1);
    // assertNotNull(comp);
    // assertEquals(comp, c1);
    // }
    //
    // @Test
    // public void testDelete() {
    // boolean res = mockedSComputer.delete(c1);
    // assertTrue(res);
    // }

    private static ServiceComputer sc;

    @BeforeClass
    public static void setUp() {
        sc = ServiceComputer.INSTANCE;

    }

    @Test(expected = ServiceException.class)
    public void createComputerErrorDate() {
        c2 = new Computer.ComputerBuilder().id(1001L).introduced(LocalDateTime.of(1960, 10, 15, 0, 0, 0))
                .discontinued(LocalDateTime.of(2016, 10, 15, 0, 0, 0)).name("TEST2").build();
        sc.create(c2);
    }

    @Test
    public void updateComputerErrorDate() {
        c2 = new Computer.ComputerBuilder().id(1001L).introduced(null).discontinued(null).name("TEST2").build();
        sc.update(c2);
        sc.delete(c2);
    }

    @Test
    public void findComputerByName() {
        sc.findByName("test");
    }

    @Test
    public void testCount() {
        assertNotNull(sc.count());
    }

    @Test
    public void createComputer() {
        // Create few instances of Computer class.
        c1 = new Computer.ComputerBuilder().id(1000L).introduced(LocalDateTime.of(2015, 10, 15, 0, 0, 0))
                .discontinued(LocalDateTime.of(2015, 10, 16, 0, 0, 0)).company(new Company(1L, "OKOK")).name("TEST")
                .build();

        Computer c3 = new Computer.ComputerBuilder().id(1001L).introduced(null).discontinued(null).name("TEST3")
                .company(null).build();

        sc.findAll();
        sc.find(1000L);
        sc.create(c1);
        sc.create(c3);
        sc.delete(c1);
        sc.delete(c3);

        // Stubbing the methods of mocked BookDAL with mocked data.
        // when(mockedSComputer.findAll()).thenReturn(Arrays.asList(c1, c2));
        // when(mockedSComputer.find(1000L)).thenReturn(c1);
        // when(mockedSComputer.create(c1)).thenReturn(c1);
        // when(mockedSComputer.update(c1)).thenReturn(c1);
        // when(mockedSComputer.delete(c1)).thenReturn(true);
    }

}
