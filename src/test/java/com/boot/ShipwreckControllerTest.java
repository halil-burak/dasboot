package com.boot;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ShipwreckControllerTest {

    @InjectMocks
    ShipwreckController sc;

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGetOne() {
        Shipwreck sw = new Shipwreck();
        sw.setId(1l);
        when(shipwreckRepository.getOne(1l)).thenReturn(sw);

        Shipwreck wreck = sc.get(1l);
        verify(shipwreckRepository).getOne(1l);
        assertEquals(1l, wreck.getId().longValue());
    }

    @Test
    public void testShipwreckGetAll() {
        Shipwreck sw1 = new Shipwreck();
        sw1.setId(2l);
        Shipwreck sw2 = new Shipwreck();
        sw1.setId(3l);
        List<Shipwreck> shipwreckList = new ArrayList<Shipwreck>();
        shipwreckList.add(sw1);
        shipwreckList.add(sw2);
        when(shipwreckRepository.findAll()).thenReturn(shipwreckList);

        List<Shipwreck> wrecks = sc.list();
        verify(shipwreckRepository).findAll();
        assertEquals(2, wrecks.size());
    }

    @Test
    public void testShipwreckCreate() {
        Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(4l);
        when(shipwreckRepository.saveAndFlush(shipwreck)).thenReturn(shipwreck);
        sc.create(shipwreck);
        verify(shipwreckRepository).saveAndFlush(shipwreck);
        assertEquals(4l, shipwreck.getId().longValue());
    }
}
