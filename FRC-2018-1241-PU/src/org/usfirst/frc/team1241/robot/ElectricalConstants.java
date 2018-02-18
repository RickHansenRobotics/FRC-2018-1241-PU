package org.usfirst.frc.team1241.robot;


public class ElectricalConstants {
	// **************************************************************************
	// *****************************DRIVE MOTORS*********************************
	// **************************************************************************

	public static final int LEFT_DRIVE_FRONT = 1;
	public static final int LEFT_DRIVE_MIDDLE = 2;
	public static final int LEFT_DRIVE_BACK = 3;
	
	public static final int RIGHT_DRIVE_FRONT = 10;
	public static final int RIGHT_DRIVE_MIDDLE = 9;
	public static final int RIGHT_DRIVE_BACK = 8;	
	
	// **************************************************************************
	// ************************* DRIVE ENCODER CONSTANTS ************************
	// **************************************************************************
	
	private static final double encoderGearRatio = 1;
	private static final double wheelRadius = 3.125;
	public static final double ROTATIONS_TO_INCHES = 2*Math.PI*wheelRadius*encoderGearRatio;

	// **************************************************************************
	// ************************** ELEVATOR MOTORS *******************************
	// **************************************************************************

	public static final int ELEVATOR_CIM = 6;
		
	
	// **************************************************************************
	// ********************** ELEVATOR SENSOR CONSTANTS *************************
	// **************************************************************************
	
	public static final double elevatorRotatationsToInches = 447.368421;
	public static final int OPTICAL_BOTTOM = 3;
	

	
	// **************************************************************************
	// *************************** CLIMBER MOTORS *******************************
	// **************************************************************************

	public static final int CLIMBER_ARM_MOTOR = 7;
	
	// **************************************************************************
	// ************************** CLIMBER PISTONS *******************************
	// **************************************************************************
	
	public static final int PTO_PISTON_A = 0;
	public static final int PTO_PISTON_B = 1;	
	
	public static final int HANGER_PISTON_A = 2;
	public static final int HANGER_PISTON_B = 3;	
	

	
	public static final int LEFT_INTAKE_PISTON_A = 4;
	public static final int LEFT_INTAKE_PISTON_B = 5;
	public static final int RIGHT_INTAKE_PISTON_A = 6;
	public static final int RIGHT_INTAKE_PISTON_B = 7;
	// *************************************************************************
	// ************************** CLIMBER SENSORS ******************************
	// *************************************************************************
	
	public static final int ARM_POTENTIOMETER = 0;
	
	// *************************************************************************
	// *************************** INTAKE MOTORS *******************************
	// *************************************************************************
		
	public static final int LEFT_INTAKE_MOTOR = 4;
	public static final int RIGHT_INTAKE_MOTOR = 5;

	// **************************************************************************
	// **************************** LED PWM CONSTANTS ***************************
	// **************************************************************************

	public static final int BYTE_ONE_PIN = 8; // connect this to third from top
	public static final int BYTE_TWO_PIN = 9; // connect this to last from top

}
