/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author ADMIN
 */
public class StringNotMatchRegexException extends Exception{
    public StringNotMatchRegexException(String errorMessage) {
        super(errorMessage);
    }
}