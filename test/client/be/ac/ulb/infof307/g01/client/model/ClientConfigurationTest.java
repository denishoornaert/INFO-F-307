package be.ac.ulb.infof307.g01.client.model;

import java.io.File;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

/**
 * Tests the ClientConfiguration class.
 */
public class ClientConfigurationTest {
    
    @Test
    public void test_getApplicationIconsPaths_pathsAreValidFiles() {
        for (String iconPath : ClientConfiguration.getInstance().getApplicationIconsPaths()) {
            File iconFile = new File(iconPath);
            assertTrue(iconFile.exists());
            assertTrue(iconFile.isFile());
        }
    }
}
