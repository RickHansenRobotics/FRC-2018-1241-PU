package org.usfirst.frc.team1241.robot;


public class ElectricalConstants {
	// **************************************************************************
	// *****************************DRIVE MOTORS*********************************
	// **************************************************************************

	public static final int RIGHT_DRIVE_FRONT = 3;
	public static final int RIGHT_DRIVE_MIDDLE = 4;
	public static final int RIGHT_DRIVE_BACK = 5;

	public static final int LEFT_DRIVE_FRONT = 0;
	public static final int LEFT_DRIVE_MIDDLE = 1;
	public static final int LEFT_DRIVE_BACK = 2;

	// **************************************************************************
	// **************************** INTAKE MOTORS********************************
	// **************************************************************************

	public static final int RIGHT_INTAKE_MOTOR = 6;
	public static final int LEFT_INTAKE_MOTOR = 7;

	// **************************************************************************
	// ************************* DRIVE ENCODER CONSTANTS ************************
	// **************************************************************************
	private static final double encoderGearRatio = 42 / 36;
	private static final double wheelRadius = 3;
	public static final double ROTATIONS_TO_INCHES = 2*Math.PI*wheelRadius*encoderGearRatio;

	// **************************************************************************
	// **************************** LED PWM CONSTANTS ***************************
	// **************************************************************************

	public static final int BYTE_ONE_PIN = 8; // connect this to third from top
	public static final int BYTE_TWO_PIN = 9; // connect this to last from top

}
