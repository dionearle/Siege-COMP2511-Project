package tests;

import org.junit.runner.RunWith;
import org.junit.BeforeClass;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// NOTE: Inventory is tested in all the sub entity tests, thus no need for a seperate stand alone one.

@RunWith(Suite.class)
@SuiteClasses({
    WallTests.class,
    PlayerTests.class,
    ExitTests.class,
    BoulderSwitchTests.class,
    KeyDoorTests.class,
    EnemySwordPotionsTests.class,
    TreasureTests.class
})
public class AllTests {
	
	@BeforeClass
	public static void setupClass() {
		System.out.println("AllTests setup");
	}

}
