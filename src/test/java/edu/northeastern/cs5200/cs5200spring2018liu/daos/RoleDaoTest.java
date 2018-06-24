package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleDaoTest {

    private RoleDao roleDao = RoleDao.getInstance();

    @Test
    public void S1_assignWebsiteRole() throws Exception {
        int res = roleDao.assignWebsiteRole(34, 123, 1);
        assertEquals(1, res);
    }

    @Test
    public void S2_assignPageRole() throws Exception {
        int res = roleDao.assignPageRole(34, 123, 1);
        assertEquals(1, res);
    }

    @Test
    public void S3_deleteWebsiteRole() throws Exception {
        int res = roleDao.deleteWebsiteRole(34, 123, 1);
        assertEquals(1, res);
    }

    @Test
    public void S4_deletePageRole() throws Exception {
        int res = roleDao.deletePageRole(34, 123, 1);
        assertEquals(1, res);
    }

}