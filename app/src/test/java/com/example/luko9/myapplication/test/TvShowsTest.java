package com.example.luko9.myapplication.test;

import com.example.luko9.myapplication.Entity.TvShows;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TvShowsTest {
    @Test
    public void testGetId() {
        System.out.println("getId");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testWrongGetId() {
        System.out.println("getWrongId");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        int expResult = 2;
        int result = instance.getId();
        assertNotEquals(expResult, result);
    }


    /**
     * Test of getRating method, of class TvShows.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getRating();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongRating() {
        System.out.println("getWrongRating");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "1";
        String result = instance.getRating();
        assertNotEquals(expResult, result);
    }


    /**
     * Test of getWeight method, of class TvShows.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getWeight();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongWeight() {
        System.out.println("getWrongWeight");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "1";
        String result = instance.getWeight();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getName method, of class TvShows.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongName() {
        System.out.println("getWrongName");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "1";
        String result = instance.getName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getSummary method, of class TvShows.
     */
    @Test
    public void testGetSummary() {
        System.out.println("getSummary");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getSummary();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongSummary() {
        System.out.println("getWrongSummary");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "2";
        String result = instance.getSummary();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getImage method, of class TvShows.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getImage();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongImage() {
        System.out.println("getWrongImage");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "3";
        String result = instance.getImage();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getBigImage method, of class TvShows.
     */
    @Test
    public void testGetBigImage() {
        System.out.println("getBigImage");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getBigImage();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongBigImage() {
        System.out.println("getWrongBigImage");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "4";
        String result = instance.getBigImage();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getDays method, of class TvShows.
     */
    @Test
    public void testGetDays() {
        System.out.println("getDays");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getDays();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongDays() {
        System.out.println("getWrongDays");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "5";
        String result = instance.getDays();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getStatus method, of class TvShows.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getStatus();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongStatus() {
        System.out.println("getWrongStatus");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "1";
        String result = instance.getStatus();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getGenres method, of class TvShows.
     */
    @Test
    public void testGetGenres() {
        System.out.println("getGenres");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getGenres();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongGenres() {
        System.out.println("getWrongGenres");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "2";
        String result = instance.getGenres();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getRuntime method, of class TvShows.
     */
    @Test
    public void testGetRuntime() {
        System.out.println("getRuntime");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "0";
        String result = instance.getRuntime();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongRuntime() {
        System.out.println("getWrongRuntime");
        TvShows instance = new TvShows(1, "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0");
        String expResult = "4";
        String result = instance.getRuntime();
        assertNotEquals(expResult, result);
    }
}