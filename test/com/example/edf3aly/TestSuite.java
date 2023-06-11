package com.example.edf3aly;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        AccountTest.class,
        Pay_BillsTest.class,
        TransferTest.class,
        Buy_ItemTest.class,
        NotificationsTest.class,
        Statement_GeneratorTest.class

})
public class TestSuite {
    // Leave this class empty
}
