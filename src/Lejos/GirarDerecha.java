package Lejos;

import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

public class GirarDerecha implements Behavior{

	TouchSensor sensorTactil;
	LightSensor sensorLuz;
	UltrasonicSensor sensorUltrasonico;
	private int tipoSensor;
	
	public GirarDerecha(int sensor){
		this.tipoSensor = sensor;
		sensorTactil = new TouchSensor(SensorPort.S1);
		sensorLuz = new LightSensor(SensorPort.S2);
		sensorUltrasonico = new UltrasonicSensor(SensorPort.S3);
	}
	
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		if(tipoSensor == 1)
			return sensorTactil.isPressed();
		if(tipoSensor == 2)
			return sensorLuz.isFloodlightOn();
		if(tipoSensor == 3)
			if(sensorUltrasonico.getDistance() == 10)
				return true;
			else
				return false;
		return false;
	}


	@Override
	public void action() {
		// TODO Auto-generated method stub
		Motor.A.forward();
		Motor.C.backward();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		Motor.A.stop();
		Motor.C.stop();
	}

}
