package com.felipesousa.cupomminer.services;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DocValidator implements ConstraintValidator<ValidDoc, Long> {

    @Override
    public void initialize(ValidDoc constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long doc, ConstraintValidatorContext context) {
        if (doc == null) {
            return true;
        }

        String docStr = String.format("%014d", doc);

        if (docStr.matches("(\\d)\\1{13}")) {
            return false;
        }

        return validateCNPJ(docStr);
    }

    private boolean validateCNPJ(String cnpj) {
        try {
            int sum = 0;
            int[] weight = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            for (int i = 0; i < 12; i++) {
                int num = (int) (cnpj.charAt(i) - '0');
                sum += num * weight[i];
            }

            int remainder = (sum % 11);
            char digit13 = (remainder < 2) ? '0' : (char) ((11 - remainder) + '0');

            sum = 0;
            int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            for (int i = 0; i < 13; i++) {
                int num = (int) (cnpj.charAt(i) - '0');
                sum += num * weight2[i];
            }

            remainder = (sum % 11);
            char digit14 = (remainder < 2) ? '0' : (char) ((11 - remainder) + '0');

            return (digit13 == cnpj.charAt(12)) && (digit14 == cnpj.charAt(13));
        } catch (Exception e) {
            return false;
        }
    }
}
