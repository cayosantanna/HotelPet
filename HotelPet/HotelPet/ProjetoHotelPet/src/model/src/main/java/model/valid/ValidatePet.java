/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import model.exceptions.PetException;

public class ValidatePet {

    public static void validateNome(String nome) throws PetException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new PetException("Nome do pet não pode ser vazio.");
        }
    }

    public static void validateData(String data) throws PetException {
        if (data == null || !data.matches("\\d{2}/\\d{2}/\\d{4}") || data.length() != 10) {
            throw new PetException("Data Inválida");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            throw new PetException("Data Inválida");
        }
    }

}
