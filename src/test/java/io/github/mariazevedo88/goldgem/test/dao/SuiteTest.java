package io.github.mariazevedo88.goldgem.test.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatabaseConnectionTest.class, //test case 1
        AuthorDAOTest.class     //test case 2
})
public class SuiteTest {
	//normally, this is an empty class
}
