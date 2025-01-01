/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.exceptions;

/**
 *
 * @author thais
 */
public class PetException extends RuntimeException{
    // Construtor com uma mensagem de erro
    public PetException(String msg) {
        super(msg);
    }

    // Construtor com uma mensagem de erro e a causa (outra exceção)
    public PetException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // Construtor com apenas a causa (útil para encadear exceções)
    public PetException(Throwable cause) {
        super(cause);
    }
}
