/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.sjc.superhero.daos;

import java.util.ArrayList;
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

        for (Location location : locationList) {
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
        //arrange
        List<Location> locationList = new ArrayList<Location>();

        Location location = new Location();
        location.setLocationId(1);
        location.setName("test");
        location.setDescription("test");
        location.setAddress("test");
        location.setLatitude(21.000);
        location.setLongitude(21.000);

        Location location2 = new Location();
        location2.setLocationId(2);
        location2.setName("test");
        location2.setDescription("test");
        location2.setAddress("test");
        location2.setLatitude(21.000);
        location2.setLongitude(21.000);

        locationList.add(location);
        locationList.add(location2);

        //act
        locationDao.createLocation(location);
        locationDao.createLocation(location2);

        List<Location> locationListToTest = locationDao.readAllLocations();

        //assert
        assertEquals(locationListToTest, locationList);
    }

    /**
     * Test of readLocationById method, of class LocationDao.
     */
    @Test
    public void testReadLocationById() {
        //arrange
        Location location = new Location();
        location.setLocationId(1);
        location.setName("test");
        location.setDescription("test");
        location.setAddress("test");
        location.setLatitude(21.000);
        location.setLongitude(21.000);
        locationDao.createLocation(location);

        //act
        Location locationToTest = locationDao.readLocationById(location.getLocationId());

        //assert
        assertTrue(locationToTest.equals(location));
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        //arrange
        Location location = new Location();
        location.setLocationId(1);
        location.setName("test");
        location.setDescription("test");
        location.setAddress("test");
        location.setLatitude(21.000);
        location.setLongitude(21.000);
        locationDao.createLocation(location);

        location = locationDao.readLocationById(location.getLocationId());

        location.setDescription("Did it Work?");

        //act
        locationDao.updateLocation(location);

        Location locationToTest = locationDao.readLocationById(location.getLocationId());

        //assert
        assertEquals("Did it Work?", locationToTest.getDescription());
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        //arrange
        Location location = new Location();
        location.setLocationId(1);
        location.setName("test");
        location.setDescription("test");
        location.setAddress("test");
        location.setLatitude(21.000);
        location.setLongitude(21.000);
        locationDao.createLocation(location);

        List<Location> locationList = locationDao.readAllLocations();
        int listSize = locationList.size();

        //act
        locationDao.deleteLocation(location.getLocationId());
        List<Location> locationListToTest = locationDao.readAllLocations();
        int listSizeToTest = locationListToTest.size();

        //assert
        assertTrue( (listSize-1) == listSizeToTest);
    }

}
