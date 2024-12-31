package model.exceptions;

/**
 * Exceção personalizada para erros relacionados às reservas.
 */
public class ReservaException extends RuntimeException {

    // Construtor com uma mensagem de erro
    public ReservaException(String msg) {
        super(msg);
    }

    // Construtor com uma mensagem de erro e a causa (outra exceção)
    public ReservaException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // Construtor com apenas a causa (útil para encadear exceções)
    public ReservaException(Throwable cause) {
        super(cause);
    }
}
