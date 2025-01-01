package model.exceptions;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thais
 */
public class LoginException extends RuntimeException {

    // Construtor com uma mensagem de erro
    public LoginException(String msg) {
        super(msg);
    }

    // Construtor com uma mensagem de erro e a causa (outra exceção)
    public LoginException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // Construtor com apenas a causa (útil para encadear exceções)
    public LoginException(Throwable cause) {
        super(cause);
    }
}
