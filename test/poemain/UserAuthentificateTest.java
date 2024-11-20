/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poemain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author RC_Student_lab
 */
public class UserAuthentificateTest {
     
    UserAuthentificate mycreate_user = new UserAuthentificate();
    public UserAuthentificateTest() {
    }
@BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of UserGreet method, of class UsersAuthentification.
     */
    @Test
    public void testLoginUserGreet() {
       String username = "user_";
       String password = "P@ssw0rdsta$99";
       
       assertTrue("Login is successful",
       mycreate_user.CheckUserName(username));
       
       assertTrue("Login is succesful",
       mycreate_user.CheckPasswordComplexity(password));
      
    
}
   @Test
    public void testLoginUserGreeet() {
       String username = "uuuuuuuuuuuuuuuu";
       String password = "PP";
       
       assertFalse("Login failed",
       mycreate_user.CheckUserName(username));
       
       assertFalse("Login failed",
       mycreate_user.CheckPasswordComplexity(password));
      
    
} 
  
    @Test 
    public void testUsernameLength(){
        assertFalse("Username should be no more than 5 characters long",
                mycreate_user.CheckUserName("username"));
    }
    
    @Test 
    public void testUsernameSpecialChar(){
        assertFalse("Username must have an underscore",
                mycreate_user.CheckUserName("username"));
        
    } 
    
    
    @Test 
    
    public void testPasswordLength(){
      assertFalse("Password should be atleast 10 characters long",
        mycreate_user.CheckPasswordComplexity("password"));   
    }
    
      @Test 
      
       public void testPasswordCapital() {
        assertFalse("Password should have a capital letter",
        mycreate_user.CheckPasswordComplexity("password")); 
    
}
    @Test 
      
       public void testPasswordDigit() {
        assertFalse("Password should have a digit ",
        mycreate_user.CheckPasswordComplexity("password"));   

       } 
       
       @Test 
      
       public void testPasswordSpecialChar() {
        assertFalse("Password should have a special charcter",
        mycreate_user.CheckPasswordComplexity("password"));
}
}
    
    