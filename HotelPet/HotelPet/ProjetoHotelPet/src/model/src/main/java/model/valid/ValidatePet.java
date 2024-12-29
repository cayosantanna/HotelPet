/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import model.exceptions.ReservaException;

public class ValidatePet {

    public static void validateNome(String nome) throws ReservaException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ReservaException("Nome do pet não pode ser vazio.");
        }
    }
}

