package com.hackerrank.stocktrade;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hackerrank.stocktrade.requests.ResourcesControllerTest;
import com.hackerrank.stocktrade.requests.TradesControllerTest;
import com.hackerrank.test.utility.TestWatchman;

@RunWith(Suite.class)
@Suite.SuiteClasses({ResourcesControllerTest.class,
    TradesControllerTest.class,
   // NoResourcesTradesControllerTest.class
})
public class TestSuite {

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(TestSuite.class);
    }
}
