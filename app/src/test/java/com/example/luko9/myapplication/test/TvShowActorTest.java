package com.example.luko9.myapplication.test;

import com.example.luko9.myapplication.Entity.TvShowActor;

import org.junit.Test;

import static org.junit.Assert.*;

public class TvShowActorTest {

    /**
     * Test of getName method, of class TvShowActor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "James";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongName() {
        System.out.println("getWrongName");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "Jamess";
        String result = instance.getName();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getCharacter method, of class TvShowActor.
     */
    @Test
    public void testGetCharacter() {
        System.out.println("getCharacter");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "james";
        String result = instance.getCharacter();
        assertEquals(expResult, result);
    }
    @Test
    public void testGetWrongCharacter() {
        System.out.println("getWrongCharacter");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "jamses";
        String result = instance.getCharacter();
        assertNotEquals(expResult, result);
    }


    /**
     * Test of getImage method, of class TvShowActor.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "http://test.com";
        String result = instance.getImage();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongImage() {
        System.out.println("getWrongImage");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com"," http://jamesjames.com" );
        String expResult = "http://tessst.com";
        String result = instance.getImage();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of getUrl method, of class TvShowActor.
     */
    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com","http://jamesjames.com" );
        String expResult = "http://jamesjames.com";
        String result = instance.getUrl();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetWrongUrl() {
        System.out.println("getWrongUrl");
        TvShowActor instance = new TvShowActor("James", "james", "http://test.com","http://jamesjames.com" );
        String expResult = "http://jamesjamess.com";
        String result = instance.getUrl();
        assertNotEquals(expResult, result);
    }

}