/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

/**
 *
 * @author rob
 */
public class Util {
    
    private static Util util = new Util();
    
    public static Util getInstanceOf(){
        return util;
    }
    
    public String getOnlyName(String archivo){
        String e;
        int i=0;
        i = archivo.lastIndexOf('.');
        if(i>0 && i<archivo.length()){
            e = archivo.substring(0, i);
            return e;
        }
        else
          return archivo;
    }

}
