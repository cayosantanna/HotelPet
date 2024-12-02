/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import model.exceptions.ReservaException;

public class ValidateCliente {
    public static void validateCPF(String cpf) throws ReservaException {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new ReservaException("CPF inválido!");
        }
    }

    public static void validateEmail(String email) throws ReservaException {
        if (email == null || !email.contains("@")) {
            throw new ReservaException("Email inválido!");
        }
    }
}
