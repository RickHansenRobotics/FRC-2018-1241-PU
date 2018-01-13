package org.usfirst.frc.team1241.robot;


public class ElectricalConstants {
	// **************************************************************************
	// *****************************DRIVE MOTORS*********************************
	// **************************************************************************

	public static final int RIGHT_DRIVE_FRONT = 2;
	public static final int RIGHT_DRIVE_BACK = 3;

	public static final int LEFT_DRIVE_FRONT = 1;
	public static final int LEFT_DRIVE_BACK = 4;

	// **************************************************************************
	// **************************** INTAKE MOTORS********************************
	// **************************************************************************

	public static final int RIGHT_INTAKE_MOTOR = 5;
	public static final int LEFT_INTAKE_MOTOR = 6;

	// **************************************************************************
	// ************************* DRIVE ENCODER CONSTANTS ************************
	// **************************************************************************
	private static final double encoderGearRatio = 42 / 36;
	private static final double wheelRadius = 3;
	public static final double ROTATIONS_TO_INCHES = 2*Math.PI*wheelRadius*encoderGearRatio;

	// **************************************************************************
	// **************************** LED PWM CONSTANTS
	// ***************************
	// **************************************************************************

	public static final int BYTE_ONE_PIN = 8; // connect this to third from top
	public static final int BYTE_TWO_PIN = 9; // connect this to last from top

}
