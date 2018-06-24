package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import edu.northeastern.cs5200.cs5200spring2018liu.models.Developer;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeveloperDaoTest {

    private DeveloperDao developerDao;
    private Developer john = new Developer(
            57, "john", "smith", "john",
            "johnjohn", "john@john.com", null,
            null, null, "John Key", null);

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        developerDao = DeveloperDao.getInstance();
    }

    @Test
    public void stage1_createDeveloper() throws Exception {
        int res = developerDao.createDeveloper(john);
        System.out.println(res);
        assertEquals(1, res);
    }

    @Test
    public void stage2_findAllDevelopers() throws Exception {
        Collection<Developer> developers = developerDao.findAllDevelopers();
        System.out.println(developers);
        assertNotEquals(0, developers.size());
    }

    @Test
    public void stage3_findDeveloperById() throws Exception {
        Developer developer = developerDao.findDeveloperById(john.getId());
        assertNotNull(developer);
        assertEquals(john.getUsername(), developer.getUsername());
    }

    @Test
    public void stage4_findDeveloperByUsername() throws Exception {
        Developer developer = developerDao.findDeveloperByUsername(john.getUsername());
        assertNotNull(developer);
        assertEquals(john.getId(), developer.getId());
    }

    @Test
    public void stage4_findDeveloperByCredentials() throws Exception {
        Developer developer = developerDao.findDeveloperByCredentials(john.getUsername(), john.getPassword());
        assertNotNull(developer);
        assertEquals(john.getId(), developer.getId());
    }

    @Test
    public void stage5_updateDeveloper() throws Exception {
        john.setUsername("John");
        john.setDeveloperKey("new developer key");
        developerDao.updateDeveloper(john.getId(), john);

        Developer developer = developerDao.findDeveloperById(john.getId());
        assertNotNull(developer);
        assertEquals(john.getUsername(), developer.getUsername());
        assertEquals(john.getDeveloperKey(), developer.getDeveloperKey());
    }

    @Test
    public void stage6_deleteDeveloper() throws Exception {
        int res = developerDao.deleteDeveloper(john.getId());
        System.out.println("delete john: " + res);
        assertEquals(1, res);
    }

}