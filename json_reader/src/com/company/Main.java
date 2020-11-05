package com.company;
import org.json.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try{
            InputStream stream = new FileInputStream("Book_info.json");
            byte[] b = new byte[stream.available()];
            stream.read(b);
            String file = new String(b);

            JSONObject obj = new JSONObject(file);
            JSONArray arr = obj.getJSONArray("root");
            for (int i = 0; i < arr.length(); i++)
            {
                String post_id = arr.getJSONObject(i).getString("Book_name");
                System.out.println(post_id);
            }

        }
        catch(JSONException jsonException) {
            System.out.println(jsonException.getMessage());
        }

        catch (IOException exception) {
            exception.printStackTrace();
        }


    }
}
