/*
 * La clase actions decide 
 */
package Lejos;
import lejos.robotics.subsumption.*;
public class GeneraObjetoLejos{
	
	//variables para el robot lego
	int velocidad = 200; //velociad Motor
	Behavior avanza; //= new Avanzar();
	Behavior giraIzquierda;// = new GirarIzquierda();
	Behavior giraDerecha; //= new GirarDerecha();
	Behavior retrocede; //= new Retroceder();
	Behavior [] bArray = {avanza, giraIzquierda,giraDerecha,retrocede};
	Arbitrator oraculo = new Arbitrator(bArray);
	
	
	void accion(int num){
		if(num == 2)
			avanza.action();
		if(num == 3)
			retrocede.action();
		if(num == 4)
			giraIzquierda.action();
		if(num == 5)
			giraDerecha.action();
	}
	
	public void iniciar(){
		oraculo.start();
	}
}
