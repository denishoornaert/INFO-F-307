/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ac.ulb.infof307.g01.common.model;

import static junit.framework.TestCase.assertEquals;
import org.junit.Test;

/**
 *
 * @author davidabraham
 */
public class ConfigurationModelTest {
    private static final String TEST_PATH = "/asset/file.txt";
    private static final String FILE_PREFIX = "file:";
    private static final String JAR_PREFIX = "jar:";
    
    
    
    @Test
    public void test_addFilePrefix_WithPathWithoutPrefix(){
        String baseString = TEST_PATH;
        String anotherString = ConfigurationModel.addFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
        
    }
    
    @Test
    public void test_addFilePrefix_WithPathWithtPrefix(){
        String baseString = FILE_PREFIX+TEST_PATH;
        String anotherString = ConfigurationModel.addFilePrefix(baseString);
        assertEquals(anotherString,baseString);
        
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithtPrefix(){
        String baseString = FILE_PREFIX+TEST_PATH;
        String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,JAR_PREFIX+baseString);
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithJarPrefix(){
        String baseString = JAR_PREFIX+TEST_PATH;
        String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,baseString);
    
    }
    
    @Test
    public void test_addJarPrefix_WithPathWithoutPrefix(){
        String baseString = TEST_PATH;
        String anotherString = ConfigurationModel.addJarPrefix(baseString);
        assertEquals(anotherString,baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithoutPrefix(){
        String baseString = TEST_PATH;
        String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithPrefix(){
        String baseString = FILE_PREFIX+TEST_PATH;
        String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,JAR_PREFIX+baseString);
    
    }
    
    @Test
    public void test_addJarOrFilePrefix_WithPathWithJarPrefix(){
        String baseString = JAR_PREFIX+TEST_PATH;
        String anotherString = ConfigurationModel.addJarOrFilePrefix(baseString);
        assertEquals(anotherString,FILE_PREFIX+baseString);
    
    }

    
}
