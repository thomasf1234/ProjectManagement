package com.abstractx1.projectmanagement;

import android.content.Context;
import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.abstractx1.projectmanagement.db.SQLiteSession;
import com.abstractx1.projectmanagement.models.Project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Before
    public void setUp() throws Exception {
        SQLiteSession.getInstance().clear();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.abstractx1.projectmanagement", appContext.getPackageName());
    }

    @Test
    public void testInsert() {
        int id = SQLiteSession.getInstance().insert("INSERT INTO projects (name) VALUES ('MyOther''s Project')");
        Cursor cursor = SQLiteSession.getInstance().query("SELECT COUNT(*) FROM projects");
        assertEquals(1, cursor.getInt(0));
        cursor.close();
        cursor = SQLiteSession.getInstance().query(String.format("SELECT * FROM projects WHERE id = %d", id));
        assertNotNull(cursor);
        assertEquals("MyOther's Project", cursor.getString(cursor.getColumnIndex("name")));
        cursor.close();
    }
}
