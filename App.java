package org.example;
import java.nio.file.Path;

import org.json.JSONObject;
import org.json.JSONException;
import java.nio.file.Files;
import java.util.Iterator;
import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        try {
            // JSON FILES ARE TEST1.JSON AND TEST2.JSON
            String content = Files.readString(Path.of("C:/Sanjay/JAVA_codes_DSA/COdes/CallingfromJSON/src/main/resources/test2.json"));


            // Parsing JSON file
            JSONObject json = new JSONObject(content);

            BigInteger C = BigInteger.ONE; 

            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();

                // Skipping non-numeric keys 
                if (!key.matches("\\d+")) continue;

                try {
                    JSONObject obj = json.getJSONObject(key);
                    String valueStr = obj.getString("value");
                    int base = Integer.parseInt(obj.getString("base"));

                    BigInteger decoded = new BigInteger(valueStr, base);
                    System.out.println("Decoded root (key " + key + "): " + decoded);

                    C = C.multiply(decoded);
                } catch (JSONException e) {
                    System.out.println("Skipping invalid entry for key " + key);
                }
            }

            System.out.println("\nConstant C = " + C);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
