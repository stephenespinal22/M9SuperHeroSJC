/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sg.sjc.superhero.TestApplicationConfiguration;
import sg.sjc.superhero.dtos.Location;

/**
 *
 * @author stephenespinal
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class LocationDaoTest {

    @Autowired
    LocationDao locationDao;

    public LocationDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        List<Location> locationList = locationDao.readAllLocations();
        
        for (Location location: locationList)
        {
            locationDao.deleteLocation(location.getLocationId());
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createLocation method, of class LocationDao.
     */
    @Test
    public void testCreateLocation() {

        //arrange
        Location location = new Location();
        location.setLocationId(1);
        location.setName("test");
        location.setDescription("test");
        location.setAddress("test");
        location.setLatitude(21.000);
        location.setLongitude(21.000);
        
        //act 
        Location newLocation = locationDao.createLocation(location);
        
        //assert 
        assertTrue(location.equals(newLocation));
    }

    /**
     * Test of readAllLocations method, of class LocationDao.
     */
    @Test
    public void testReadAllLocations() {
    }

    /**
     * Test of readLocationById method, of class LocationDao.
     */
    @Test
    public void testReadLocationById() {
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
    }

}
