package com.kruger.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    public boolean validateIdentEc(String cedEc) {
        boolean cedCorrect = false;
        try {

            if (cedEc.length() == 10) {
                int thirdDigid = Integer.parseInt(cedEc.substring(2, 3));
                if (thirdDigid < 6) {

                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verify = Integer.parseInt(cedEc.substring(9, 10));
                    int sum = 0;
                    int digit = 0;
                    for (int i = 0; i < (cedEc.length() - 1); i++) {
                        digit = Integer.parseInt(cedEc.substring(i, i + 1)) * coefValCedula[i];
                        sum += ((digit % 10) + (digit / 10));
                    }

                    if ((sum % 10 == 0) && (sum % 10 == verify)) {
                        cedCorrect = true;
                    } else if ((10 - (sum % 10)) == verify) {
                        cedCorrect = true;
                    } else {
                        cedCorrect = false;
                    }
                } else {
                    cedCorrect = false;
                }
            } else {
                cedCorrect = false;
            }
        } catch (NumberFormatException nfe) {
            cedCorrect = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedCorrect = false;
        }

        if (!cedCorrect) {
            System.out.println("La Cédula ingresada es Incorrecta");
        }
        return cedCorrect;
    }

    public boolean isAlpha(String s) {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c == ' ')) {
                return false;
            }
        }
        return true;
    }

    public String obtenerFechaFormateada(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(fecha);
    }

    public boolean validateCreatinEmploye(Integer identificationNumber, String names, String lastNames, String email) {

        if (names.isEmpty()) {

            return false;
        }
        if (identificationNumber.toString().isEmpty()) {

            return false;
        }
        if (lastNames.isEmpty()) {

            return false;
        }
        if (email.isEmpty()) {

            return false;
        }
        return true;
    }

    public boolean validateEmail(String email) {
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDate(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
