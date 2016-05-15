/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaquinaVirtual;

import java.util.Optional;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

public class SueloInfo extends Canvas {


  static Color colorAgente[];
  static String nombreAgente[];
  int numAge;

  public SueloInfo(int x, int y, int w, int h, int numAge){
      setTranslateX(x);
      setTranslateY(y);
      setWidth(w);
      setHeight(h);

  this.numAge = numAge;

  nombreAgente = new String [numAge];

  /*
   *  Definicin de los colores de los agentes
   */
  colorAgente = new Color[10];
  colorAgente[0]= Color.MAGENTA;
  colorAgente[1]= Color.RED;
  colorAgente[2]= Color.GREEN;
  colorAgente[3]= Color.PINK;
  colorAgente[4]= Color.CYAN;
  colorAgente[5]= Color.WHITE;
  colorAgente[6]= Color.ORANGE;
  colorAgente[7]= Color.GREY;
  colorAgente[8]= Color.YELLOW;
  colorAgente[9]= Color.BLUE;

  }


/*
 *  Sobrecarga del mtodo paint propio del Canvas
 */

public void paint(){
  GraphicsContext g2 = getGraphicsContext2D();
  int xx, yy, coc, res;
  for(int i=0; i<numAge; i++){
    coc = i / 2;
    res = i % 2;
    xx = 80 * res;
    yy = (coc + 1) * 15;
    g2.setFill(colorAgente[i]); // especifica el color del agente

    g2.fillPolygon(new double[]{xx, xx+5, xx+10}, 
        new double[]{yy+10, yy, yy+10}, 3);       
    g2.setFill(Color.BLACK); // especifica el color del agente
    g2.fillText(nombreAgente[i], xx + 20, yy + 10);
  }
}

/*
 *  Sobrecarga del mtodo update para eliminar el parpadeo
 */

/*public void update(Graphics g){
  paint(g);
}*/

public void setAgente(int i, String nombre){
  nombreAgente[i]=nombre;
  paint();
}

}
