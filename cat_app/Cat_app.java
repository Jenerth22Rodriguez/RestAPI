/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cat_app;
import javax.swing.JOptionPane;

/**
 *
 * @author jenerthrodriguez
 */
public class Cat_app{

    public static void main(String[] args) {
        
        int optionMenu = -1;
        String[] buttons = {"1. View a Cat", "2. Quit"};
        String option;
        do{
            
             option = (String) JOptionPane.showInputDialog(null, "Java Cat", "Main Menu", 
                                                          JOptionPane.INFORMATION_MESSAGE, null, 
                                                          buttons, buttons[0]);
             
             for (int i = 0; i < buttons.length; i++){
                 if(option.equals(buttons[i])){
                     optionMenu = i;
                 }
             }
             switch(optionMenu){
                 case 0:
                     Catserver.viewCats();
                     break;
                 default:
                         break;
             }
    }
        
    while(optionMenu != 1);
        
    }
}
