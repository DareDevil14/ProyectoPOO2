/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compimac.constants;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author rob
 */
public class CompiConstants {

    public static String APP_NAME = "InAge2000ver2";

    // constants for menu bar
    public static String FILE_MENU = "FILE_MENU";
    public static String EDIT_MENU = "EDIT_MENU";
    public static String SEE_MENU = "SEE_MENU";
    public static String COMPILAR_MENU = "COMPILAR_MENU";
    public static String VIRTUAL_MENU = "VIRTUAL_MENU";
    public static String HELP_MENU = "HELP_MENU";

    // file menu item
    public static String new_menuItem = "new_menuItem";
    public static String open_menuItem = "open_menuItem";
    public static String close_menuItem = "close_menuItem";
    public static String closeAll_menuItem = "closeAll_menuItem";
    public static String save_menuItem = "save_menuItem";
    public static String saveAll_menuItem = "saveAll_menuItem";
    public static String saveAs_menuItem = "saveAs_menuItem";
    public static String printer_menuItem = "printer_menuItem";
    public static String closeApp_menuItem = "closeApp_menuItem";
    // edit menu item
    public static String undo_menuItem = "undo_menuItem";
    public static String redo_menuItem = "redo_menuItem";
    public static String cut_menuItem = "cut_menuItem";
    public static String copy_menuItem = "copy_menuItem";
    public static String paste_menuItem = "paste_menuItem";
    public static String remove_menuItem = "remove_menuItem";
    public static String selectAll_menuItem = "selectAll_menuItem";

    // see menu item
    public static String errors_menuItem = "errors_menuItem";

    //compilar menu item
    public static String executor_menuItem = "executor_menuItem";        
    public static String TRANSLATE_LEJO_MENU_TEXT = "Translate to Lego NXT";

    // virtual machine menu item
    public static String newWorld_menuItem = "newWorld_menuItem";
    public static String openWorld_menuItem = "openWorld_menuItem";
    public static String saveWorld_menuItem = "saveWorld_menuItem";
    public static String saveAllWorld_menuItem = "saveAllWorld_menuItem";
    public static String startSimulation_menuItem = "startSimulation_menuItem";
    public static String stopSimulation_menuItem = "stopSimulation_menuItem";
    public static String closeSimulation_menuItem = "closeSimulation_menuItem";

    // help menu item
    public static String help_menuItem = "help_menuItem";
    public static String about_menuItem = "about_menuItem";

    // tooltip constants
    public static String TOOLTIP_NEW_FILE = "TOOLTIP_NEW_FILE";
    public static String TOOLTIP_OPEN_FILE = "TOOLTIP_OPEN_FILE";
    public static String TOOLTIP_CLOSE_FILE = "TOOLTIP_CLOSE_FILE";
    public static String TOOLTIP_SAVE_FILE = "TOOLTIP_SAVE_FILE";
    public static String TOOLTIP_SAVEALL_FILE = "TOOLTIP_SAVEALL_FILE";
    public static String TOOLTIP_PRINT_FILE = "TOOLTIP_PRINT_FILE";

    public static String TOOLTIP_UNDO = "TOOLTIP_UNDO";
    public static String TOOLTIP_REDO = "TOOLTIP_REDO";
    public static String TOOLTIP_COPY = "TOOLTIP_COPY";
    public static String TOOLTIP_PASTE = "TOOLTIP_PASTE";
    public static String TOOLTIP_CUT = "TOOLTIP_CUT";
    
    public static String TOOLTIP_EXECUTE = "TOOLTIP_EXECUTE";
    public static String TOOLTIP_TRANSLATE_LEJOS = "Translate to Lego NXT";
    public static String TOOLTIP_START = "TOOLTIP_START";
    public static String TOOLTIP_STOP = "TOOLTIP_STOP";
    
    public static String TOOLTIP_WALL = "TOOLTIP_WALL";
    public static String TOOLTIP_OBSTACLE = "TOOLTIP_OBSTACLE";
    public static String TOOLTIP_LIGHT = "TOOLTIP_LIGHT";
    public static String TOOLTIP_TEMPERATURE= "TOOLTIP_TEMPERATURE";
    
    
    public static String TOOLTIP_UNDO_GRAPHIC = "TOOLTIP_UNDO_GRAPHIC";
    public static String TOOLTIP_REDO_GRAPHIC = "TOOLTIP_REDO_GRAPHIC";
    public static String TOOLTIP_CLEAN_CELL = "TOOLTIP_CLEAN_CELL";
    public static String TOOLTIP_ERASE_WALL = "TOOLTIP_ERASE_WALL";
    public static String TOOLTIP_CLEAN_FLOOR= "TOOLTIP_CLEAN_FLOOR";
    
    public static String TOOLTIP_HELP= "TOOLTIP_HELP";
    
    public static String COMPIILATION_SUCCESS = "COMPIILATION_SUCCESS";
    public static String LEJOS_TRANSLATION_SUCCESS = "LEJOS_TRANSLATION_SUCCESS";
     
    //language
    public static String LANGUAGE = "LANGUAGE";
    public static String LANGUAGE_ENG = "LANGUAGE_ENG";
    public static String LANGUAGE_MX = "LANGUAGE_MX";
    
    
    // INTERNATIONALIZATION
    public static String laguageEnglish = "English";
    public static String languageSpanish = "Spanish";
    public static String defaultLanguage = laguageEnglish;
            
    private static ResourceBundle englisgbBundle = ResourceBundle.getBundle("message_FX_US");
    private static ResourceBundle spanishBundle = ResourceBundle.getBundle("message_FX_MX");
    
    public static String getValue(String language, String attKey){
        if(language.equalsIgnoreCase("English")){
            return englisgbBundle.getString(attKey);
        }
        if(language.equalsIgnoreCase("Spanish")){
            return spanishBundle.getString(attKey);
        }
        
        return "";
    }
    

}
