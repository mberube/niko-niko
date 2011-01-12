package com.pyxis.nikoniko.system;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.pyxis.nikoniko.system.web.DatabaseDriver;
import com.pyxis.nikoniko.system.web.NikonikoDriver;

//import test.support.com.pyxis.petstore.web.DatabaseDriver;
//import test.support.com.pyxis.petstore.web.PetStoreDriver;

//import com.pyxis.petstore.domain.product.Product;

public class EndToEndScenarios {

	NikonikoDriver nikoniko = new NikonikoDriver();
	DatabaseDriver database = new DatabaseDriver();

    @Before public void
    startApplication() throws Exception {
        database.start();
        nikoniko.start();
	}

    @After public void
    stopApplication() throws Exception {
    	nikoniko.stop();
        database.stop();
    }

    @Test public void
    scenario1() throws Exception {
    	String firstUser = "Jean-Marc Curé-Labelle";
//    	nikoniko.showCalendarIsEmpty();
//    	nikoniko.addUser(firstUser);
//    	nikoniko.showsUserInCalendar(firstUser);
    }
}
