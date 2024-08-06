/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author ADMIN
 */
//extends the current exception class with a new custom exception
public class NumberNotInRangeException extends Exception{
    public NumberNotInRangeException(String errorMsg){
        //passing the error message to the superclass Exception
        super(errorMsg);
    }
}
