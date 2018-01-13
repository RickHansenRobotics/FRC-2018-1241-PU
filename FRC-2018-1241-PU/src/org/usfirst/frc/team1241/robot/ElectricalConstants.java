/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1241.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class ElectricalConstants {
	//**************************************************************************
	//*****************************DRIVE MOTORS*********************************
	//**************************************************************************        
		
	public static final int RIGHT_DRIVE_FRONT                               = 2; 
	public static final int RIGHT_DRIVE_BACK                                = 3;
		
	public static final int LEFT_DRIVE_FRONT                                = 1;
	public static final int LEFT_DRIVE_BACK                                 = 4;
		
	//**************************************************************************
	//**************************** INTAKE MOTORS********************************
	//**************************************************************************        
			
	public static final int RIGHT_INTAKE_MOTOR                               = 5; 
	public static final int LEFT_INTAKE_MOTOR                                = 6;
	
	//**************************************************************************
    //************************* DRIVE ENCODER CONSTANTS ************************
    //**************************************************************************
	private static final double encoderGearRatio 					         = 42/36;
	private static final double wheelRadius							    	 = 2;
	public static final double ROTATIONS_TO_INCHES 		= 14.46;//2*Math.PI*wheelRadius*encoderGearRatio;
	
	//**************************************************************************
    //**************************** LED PWM CONSTANTS ***************************
    //**************************************************************************
	
	public static final int BYTE_ONE_PIN 		        					= 8;	// connect this to third from top
	public static final int BYTE_TWO_PIN				         			= 9;	// connect this to last from top
		
}
