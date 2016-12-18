/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.websocket;

/**
 *
 * @author guan97005
 */
public class Calculator {

    public static String calc(String message) {
        double svar;
        int tal1 = Integer.parseInt(message.substring(1));
        int tal2 = Integer.parseInt(message.substring(3));

        if (message == "#calc") {
            message = ("Commands for calculator:\n"
                    + "#value+value\n"
                    + "#value-value\n"
                    + "#value*value\n"
                    + "#value/value\n"
                    + "#calc for this ofc");
        } else if (message.contains("+")) {
            svar = tal1 + tal2;
            message = String.valueOf(svar);
        } else if (message.contains("-")) {
            svar = tal1 - tal2;
            message = String.valueOf(svar);
        } else if (message.contains("/")) {
            svar = tal1 / tal2;
            message = String.valueOf(svar);
        } else if (message.contains("*")) {
            svar = tal1 * tal2;
            message = String.valueOf(svar);
        }
        return message;
    }

}
