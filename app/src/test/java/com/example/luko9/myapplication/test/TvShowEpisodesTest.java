package com.example.luko9.myapplication.test;

import com.example.luko9.myapplication.Entity.TvShowEpisodes;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TvShowEpisodesTest {

    @Test
    public void testGetEpisodeName() {
        System.out.println("getEpisodeName");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "test";
        String result = instance.getEpisodeName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongEpisodeName() {
        System.out.println("getWrongEpisodeName");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "tesst";
        String result = instance.getEpisodeName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getEpisodeNumber method, of class TvShowEpisodes.
     */
    @Test
    public void testGetEpisodeNumber() {
        System.out.println("getEpisodeNumber");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "4";
        String result = instance.getEpisodeNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongEpisodeNumber() {
        System.out.println("getEpisodeNumber");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "5";
        String result = instance.getEpisodeNumber();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getSeasonNumber method, of class TvShowEpisodes.
     */
    @Test
    public void testGetSeasonNumber() {
        System.out.println("getWrongSeasonNumber");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "4";
        String result = instance.getSeasonNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongSeasonNumber() {
        System.out.println("getWrongSeasonNumber");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "7";
        String result = instance.getSeasonNumber();
        assertNotEquals(expResult, result);
    }
    /**
     * Test of getSummary method, of class TvShowEpisodes.
     */
    @Test
    public void testGetSummary() {
        System.out.println("getSummary");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "test";
        String result = instance.getSummary();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongSummary() {
        System.out.println("getWrongSummary");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "tesst";
        String result = instance.getSummary();
        assertNotEquals(expResult, result);
    }
    /**
     * Test of getImg method, of class TvShowEpisodes.
     */
    @Test
    public void testGetImg() {
        System.out.println("getImg");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "http://test.com";
        String result = instance.getImg();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongImg() {
        System.out.println("getWrongImg");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "http://tesaat.com";
        String result = instance.getImg();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class TvShowEpisodes.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "http://www.test.com";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongUrl() {
        System.out.println("getUrl");
        TvShowEpisodes instance = new TvShowEpisodes("test", "4", "4", "test", "http://test.com", "http://www.test.com");
        String expResult = "http://www.sssstest.com";
        String result = instance.getUrl();
        assertNotEquals(expResult, result);
    }

}