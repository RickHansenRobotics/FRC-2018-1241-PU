package org.usfirst.frc.team1241.robot;

/**
 * This class is used to save constant values that will be changed every once in awhile. 
 * This can include PID tuned values or autonomous distances, etc...
 *
 *@author Kaveesha Siribaddana
 *@since 11/01/18
 */


public class NumberConstants {
	
	//**************************************************************************
    //*************************** PID VALUES (DRIVE) ***************************
    //**************************************************************************
	
	public static final double pDrive 									 = 0.02;
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.1;
	
	public static final double Drive_Scale 								 = 0.00;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	public static final double pGyro 									 = 0.015;
	public static final double iGyro 									 = 0.0000003;
	public static final double dGyro 									 = 0.05;
	
	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	public static final double pARM 									 = 0.015;
	public static final double iARM 									 = 0.0000003;
	public static final double dARM 									 = 0.05;	
	
}
