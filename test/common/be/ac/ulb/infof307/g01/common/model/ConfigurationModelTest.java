package be.ac.ulb.infof307.g01.common.model;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * 
 */
public class ConfigurationModelTest {
    private static final String TEST_PATH = "/asset/file.txt";
    private static final String FILE_PREFIX = "file:";
    private static final String JAR_PREFIX = "jar:";
    
    
    @Test
    public void test_addFilePrefix_WithPathWithoutPrefix(){
        final String baseString = TEST_PATH;
        final String anotherString = ConfigurationModel.addFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
    }
    
    @Test
    public void test_addFilePrefix_WithPathWithtPrefix(){
        final String baseString = FILE_PREFIX+TEST_PATH;
        final String anotherString = ConfigurationModel.addFilePrefix(baseString);
        assertEquals(anotherString,baseString);
        
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithtPrefix(){
        final String baseString = FILE_PREFIX+TEST_PATH;
        final String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,JAR_PREFIX+baseString);
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithJarPrefix(){
        final String baseString = JAR_PREFIX+TEST_PATH;
        final String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,baseString);
    
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithoutPrefix(){
        final String baseString = TEST_PATH;
        final String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithoutPrefix(){
        final String baseString = TEST_PATH;
        final String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithPrefix(){
        final String baseString = FILE_PREFIX+TEST_PATH;
        final String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,JAR_PREFIX+baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithJarPrefix(){
        final String baseString = JAR_PREFIX+TEST_PATH;
        final String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
    
    }

    
}
