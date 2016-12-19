/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.websocket;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
/**
 *
 * @author guan97005
 */
public class Calculator {

    
            public static JsonObject calc(String calculatorn, String message) {
        try {
            
            String[] splitToken;
            double tal1;
            double tal2;
            String[] splitHash;
            calculatorn = "Calculator";

            double svar;
            if (message.equals("#calc")) {
                message = (" Here are my commands\n"
                        + "#value+value\n"
                        + "#value-value\n"
                        + "#value*value\n"
                        + "#value/value\n");
            }
            if (message.equals("#")) {
                message = (" Here are my commands\n"
                        + "#value+value\n"
                        + "#value-value\n"
                        + "#value*value\n"
                        + "#value/value\n");
            }
            if (message.contains("+")) {
                splitHash = (message).split("\\#");
                System.out.println("splitHash" + splitHash[1]);
                splitToken = (splitHash[1].toString()).split("\\+");
                tal1 = Integer.parseInt(splitToken[0]);
                tal2 = Integer.parseInt(splitToken[1]);
                svar = tal1 + tal2;
                message = String.valueOf(svar);
                System.out.println("tal1 " + tal1);
            }

            if (message.contains("-")) {
                splitHash = (message).split("\\#");
                splitToken = (splitHash[1].toString()).split("\\-");
                tal1 = Integer.parseInt(splitToken[0]);
                tal2 = Integer.parseInt(splitToken[1]);
                svar = tal1 - tal2;
                message = String.valueOf(svar);
                System.out.println("tal1 " + tal1);
            }

            if (message.contains("/")) {
                splitHash = (message).split("\\#");
                splitToken = (splitHash[1].toString()).split("\\/");
                tal1 = Integer.parseInt(splitToken[0]);
                tal2 = Integer.parseInt(splitToken[1]);
                svar = tal1 / tal2;
                message = String.valueOf(svar);
                System.out.println("tal1 " + tal1);
            }

            if (message.contains("*")) {
                splitHash = (message).split("\\#");
                splitToken = (splitHash[1].toString()).split("\\*");
                tal1 = Integer.parseInt(splitToken[0]);
                tal2 = Integer.parseInt(splitToken[1]);
                svar = tal1 * tal2;
                message = String.valueOf(svar);
                System.out.println("tal1 " + tal1);
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        JsonObject object = Json.createObjectBuilder().add("username", calculatorn).add("message", message).build();
        return object;
    }
}
