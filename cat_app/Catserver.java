/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cat_app;

import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author jenerthrodriguez
 */

public class Catserver {
    public static void viewCats() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .get()
                .build();

        Response response = client.newCall(request).execute();

        String daJson = response.body().string();
        response.close();

        // Directly parse the JSON array
        Gson gson = new Gson();
        Cats[] cats = gson.fromJson(daJson, Cats[].class);

        if (cats.length > 0) {
            Cats cat = cats[0];

            Image image = null;
            try {
                URL url = new URL(cat.getUrl());
                image = ImageIO.read(url);

                ImageIcon groundCat = new ImageIcon(image);

                if (groundCat.getIconWidth() > 800) {
                    Image ground = groundCat.getImage();
                    Image result = ground.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                    groundCat = new ImageIcon(result);
                }

                String menu = "options: \n"
                        + "1. See another image\n"
                        + "2. Favorite\n"
                        + "3. Back\n";

                String[] buttons = {"View another image", "Favorite", "Back"};
                String id_cat = cat.getId();

                String option = (String) JOptionPane.showInputDialog(null, menu, id_cat, JOptionPane.INFORMATION_MESSAGE, groundCat, buttons, buttons[0]);
                int selection = -1;

                for (int i = 0; i < buttons.length; i++) {
                    if (option != null && option.equals(buttons[i])) {
                        selection = i;
                    }
                }
                switch (selection) {
                    case 0:
                        viewCats();
                        break;
                    case 1:
                        favoriteCats(cat);
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("No cat images found.");
        }
    }

    public static void favoriteCats(Cats cat) {
    }
}
