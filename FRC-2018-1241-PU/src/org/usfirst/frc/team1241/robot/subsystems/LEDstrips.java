package org.usfirst.frc.team1241.robot.subsystems;



import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.commands.LedCommand;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDstrips extends Subsystem {
	//static DigitalOutput hopperLedPWM;
	static DigitalOutput byteOne;
	static DigitalOutput byteTwo;
	

	
	public LEDstrips() {
		//hopperLedPWM = new DigitalOutput(ElectricalConstants.HOPPER_PWM_PIN);
		byteOne = new DigitalOutput(ElectricalConstants.BYTE_ONE_PIN);
		byteTwo = new DigitalOutput(ElectricalConstants.BYTE_TWO_PIN);
	}
	
	public static void setState(boolean byteOneState, boolean byteTwoState) {
		//hopperLedPWM.set(state);
		byteOne.set(byteOneState);
		byteTwo.set(byteTwoState);
	}
	
	public static void disabled() {
		setState(false, false);
	}
	
	public static void solidGreen() {
		setState(false, true);
	}
	
	public static void gold() {
		setState(true, false);
	}
	
	public static void solidBlue(){
		setState(true,true);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new LedCommand());
    }
}

