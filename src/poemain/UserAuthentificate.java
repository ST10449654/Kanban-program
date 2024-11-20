/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poemain;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class UserAuthentificate {
    
    private String firstname;
    private String lastname;
  
    //method that greets user once they have logged in
    public void LoginUser(String firstname , String lastname){
    
    this.firstname = firstname;
    this.lastname = lastname;
    
    //return this statement if login is successful
      System.out.println("Welcome " + this.firstname  +  
              this.lastname + " it is great to see you.");
 }
    
   
    
    
    
    //create methods to check the username and password
    
    public boolean CheckUserName(String username)
    {
        boolean right = false;
        
        //then check if username contains requirements
      if  (username != null && username.length()>=5 && username.contains("_")){
          //return statements if true
          System.out.println("Username succesfully captured");
          
          return true;
          
      }
      
      else{
          //return statement if false
           
          System.out.println("The username is incorrectly formatted, please ensure that the username contains "
                  + "an underscore and is no more than 5 characters in length.");
          return right;
          
      }
      
        }
    
    
    
    
    
    public  boolean CheckPasswordComplexity(String password){
        
       // String passwordPattern = "(^.=? [A-z]) [@#$&+#] [0123456789]$";
      //  Pattern pat = Pattern.compile(password);
        Pattern checking = Pattern.compile("[QWERTYUIOPASDFGHJKLZXCVBNM]");
        Pattern check_special = Pattern.compile("[~@#$%^&*+=:?]");
        Pattern check_digits = Pattern.compile("[0123456789]");
        
       
        
        //check password length
        boolean knew = false;
        if (password== null && password.length()<=10){
            
            //return statement if false   
         System.out.println("Password is not correctly formatted, please ensure that the password contains "
                    + "an uppercase, a digit and special character. ");
        return knew ;
        }
        
        //check if password has a capital letter
        if(!checking.matcher(password).find()){
            
            //return statement if false
            System.out.println("Password is not correctly formatted, please ensure that the password contains "
                    + "an uppercase, a digit and special character. ");
        return knew ;
        }
        
        //check if password has a number
         if(!check_digits.matcher(password).find()){
            
             //return statement if false
            System.out.println("Password is not correctly formatted, please ensure that the password contains "
                    + "an uppercase, a digit and special character. ");
        return knew ;
        }
        
         //check if password has a special character
         if(!check_special.matcher(password).find()){
            
             //return statement if false
            System.out.println("Password is not correctly formatted, please ensure that the password contains "
                    + "an uppercase, a digit and special character. ");
        return knew ;
        }else{
            //return true statement if all the complexities are met
            System.out.println("Password successfully captured");
            return true;
            
       
        
       
     
          
    
    
      }
    }
     
    
    
 }
    

