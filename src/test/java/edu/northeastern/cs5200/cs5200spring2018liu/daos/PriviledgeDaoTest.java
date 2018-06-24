package edu.northeastern.cs5200.cs5200spring2018liu.daos;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PriviledgeDaoTest {

    private PriviledgeDao priviledgeDao = PriviledgeDao.getInstance();

    @Test
    public void S1_assignWebsitePriviledge() throws Exception {
        int res = priviledgeDao.assignWebsitePriviledge(34, 123, 2);
        assertEquals(1, res);
    }

    @Test
    public void S2_assignPagePriviledge() throws Exception {
        int res = priviledgeDao.assignPagePriviledge(34, 123, 4);
        assertEquals(1, res);
    }

    @Test
    public void S3_deleteWebsitePriviledge() throws Exception {
        int res = priviledgeDao.deleteWebsitePriviledge(34, 123, 2);
        assertEquals(1, res);
    }

    @Test
    public void S4_deletePagePriviledge() throws Exception {
        int res = priviledgeDao.deletePagePriviledge(34, 123, 4);
        assertEquals(1, res);
    }

}