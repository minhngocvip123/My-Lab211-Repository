/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author ADMIN
 */
public class IncorrectCaptcha extends Exception{
    public IncorrectCaptcha (String errorMessage) {
        super(errorMessage);
    }
}
