package com.example.luko9.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.luko9.myapplication.DB.DBHelper;
import com.example.luko9.myapplication.Entity.TvShows;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.luko9.myapplication", appContext.getPackageName());
    }

    @Test
    public void testDBWithShows() throws Exception {
        Context appCtxt = InstrumentationRegistry.getTargetContext();

        DBHelper db = new DBHelper(appCtxt);

        ArrayList<TvShows> listOfShows = db.getAllShows();
        System.out.println(listOfShows.get(3).getSummary());
        TvShows show = listOfShows.get(3);

        assertEquals(show, listOfShows.get(3));
    }
}
