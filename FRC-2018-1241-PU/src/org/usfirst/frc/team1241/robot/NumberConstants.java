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
	
	public static final double pDrive 									 = 0.009;
	public static final double iDrive 									 = 0.0;
	public static final double dDrive 									 = 0.06;
	
	public static final double fTalonDrive 							 	= 0.25575;
	public static final double pTalonDrive 							 	= 0.0;
	public static final double iTalonDrive 							 	= 0.0; 
	public static final double dTalonDrive 							 	= 0.0;
	
	public static final double Drive_Scale 								 = 0.0;
	
	public static final int maxDriveSpeed								 = 4000;
	
	public static final double nativeToInches							 = 201.66;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	public static final double pGyro 									 = 0.01;
	public static final double iGyro 									 = 0.0;
	public static final double dGyro 									 = 0.9;
	
	//**************************************************************************
    //************************ PID VALUES (ELEVATOR) ***************************
    //**************************************************************************
	
	public static final double fTalonElevator 							 = 0.4;//0.0058766;
	public static final double pTalonElevator 							 = 0.225;
	public static final double iTalonElevator 							 = 0.0; 
	public static final double dTalonElevator 							 = 0.00001;
	
	public static final double pElevator 								 = 0.0;
	public static final double iElevator 								 = 0.0; 
	public static final double dElevator 								 = 0.0;
	
	public static final double pLockElevator 							 = 0.0;
	public static final double iLockElevator 						     = 0.0; 
	public static final double dLockElevator 							 = 0.0;
	
	//**************************************************************************
    //*************************** Elevator Constants ***************************
    //**************************************************************************
	
	public static final int bottomHardStop 							 	  = 0;
	public static final int topHardStop 							      = 94;
	
	public static final double intakingPosition 						  = -1;
	public static final double exchangePosition 			     		  = 4.25;
	public static final double switchPosition   						  = 25;
	public static final double portalPosition 			    			  = 19;
	public static final double scaleLowPosition 						  = 60;
	public static final double scaleMidPosition 						  = 70;
	public static final double scaleHighPosition 						  = 77.5;
	public static final int maxElevatorSpeed                              = 2700;

	
	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	public static final double pARM 									 = 0.0;
	public static final double iARM 									 = 0.000000;
	public static final double dARM 									 = 0.0;	
	
}
