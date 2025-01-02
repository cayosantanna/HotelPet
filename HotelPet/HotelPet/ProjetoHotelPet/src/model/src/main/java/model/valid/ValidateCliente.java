/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.valid;

import model.exceptions.ClienteException;

public class ValidateCliente {

    public static void validateCPF(String cpf) throws ClienteException {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new ClienteException("CPF inválido. Deve conter 11 dígitos.");
        }
        // Implementar validação de CPF conforme algoritmo
        if (!isValidCPF(cpf)) {
            throw new ClienteException("CPF inválido.");
        }
    }

    private static boolean isValidCPF(String cpf) {
        //vamos verificar o tamanho primeiro        
        if (cpf.length() != 11 && cpf.length() != 14) {
            return false;
        }

        //Retira os caracteres deixando apenas digitos
        if (cpf.length() == 14) {
            cpf = cpf.replaceAll("\\.", "");
            cpf = cpf.replaceAll("-", "");
        }

        //se tem tamanho 11 e so possui digitos
        if (cpf.length() == 11 && cpf.matches("[0-9]*")) {
            String[] vet = cpf.split("");

            int digito1 = Integer.parseInt(vet[9]);
            int digito2 = Integer.parseInt(vet[10]);

            //vamos calcular o primeiro verificador
            int soma1 = 0;
            int fator1 = 10;
            for (int i = 0; i <= 8; i++) {
                int val = Integer.parseInt(vet[i]);
                soma1 = soma1 + (fator1 * val);
                fator1--;
            }

            int resultado1 = (soma1 * 10) % 11;
            if (resultado1 == 10) {
                resultado1 = 0;
            }

            if (resultado1 != digito1) {
                return false;
            }

            //vamos calcular o segundo verificador
            int soma2 = 0;
            int fator2 = 11;
            for (int i = 0; i <= 9; i++) {
                int val = Integer.parseInt(vet[i]);
                soma2 = soma2 + (fator2 * val);
                fator2--;
            }

            int resultado2 = (soma2 * 10) % 11;
            if (resultado2 == 10) {
                resultado2 = 0;
            }

            if (resultado2 != digito2) {
                return false;
            }

            //agora so basta verificar se todos são iguais
            for (int i = 0; i <= 9; i++) {
                int val = Integer.parseInt(vet[i]);
                int valProx = Integer.parseInt(vet[i + 1]);
                if (val != valProx) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void validateEmail(String email) throws ClienteException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new ClienteException("Email inválido!");
        }
    }

    public static void validateCEP(String cep) throws ClienteException {
        if (cep == null || cep.length() < 8 || !cep.matches("\\d+")) {
            throw new ClienteException("CEP inválido!");

        }
    }
    
    public static void validateTelefone(String telefone) throws ClienteException{
        if(telefone == null || telefone.length() < 10 || telefone.length() > 11 || !telefone.matches("\\d+")){
            throw new ClienteException("Telefone inválido!");
        }
    }

    public static void validateSenha(String senha) throws IllegalArgumentException {
    if (senha == null || senha.isEmpty()) {
        throw new IllegalArgumentException("A senha não pode ser vazia.");
    }

    if (senha.length() < 8) {
        throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
    }

    if (!senha.matches(".*[A-Z].*")) {
        throw new IllegalArgumentException("A senha deve conter pelo menos uma letra maiúscula.");
    }

    if (!senha.matches(".*[a-z].*")) {
        throw new IllegalArgumentException("A senha deve conter pelo menos uma letra minúscula.");
    }

    if (!senha.matches(".*\\d.*")) {
        throw new IllegalArgumentException("A senha deve conter pelo menos um número.");
    }

    if (!senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
        throw new IllegalArgumentException("A senha deve conter pelo menos um caractere especial (!@#$%^&* etc.).");
    }
}

    public static void validateFuncionario(String cpf, String email, String telefone, String senha) throws IllegalArgumentException {
        validateCPF(cpf);
        validateEmail(email);
        validateTelefone(telefone);
        validateSenha(senha);
    }
}
