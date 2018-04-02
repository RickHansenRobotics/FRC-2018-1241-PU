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
	
	public static final double pDrive 									 = 0.007; //0.01
	public static final double iDrive 									 = 0.0;  //0
	public static final double dDrive 									 = 0.09; //0.15
	
	public static final double fTalonDrive 							 	= 0.25575;
	public static final double pTalonDrive 							 	= 0.0;
	public static final double iTalonDrive 							 	= 0.0; 
	public static final double dTalonDrive 							 	= 0.0;
	
	public static final double Drive_Scale 								 = 0.00;
	
	public static final int maxDriveSpeed								 = 4000;
	
	public static final double nativeToInches							 = 201.66;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	public static final double pTurnGyro 									 = 0.02; //0.02
	public static final double iTurnGyro 									 = 0.0; //0
	public static final double dTurnGyro 									 = 0.175; //0.135
	
	public static final double pDriveGyro 									 = 0.02;
	public static final double iDriveGyro 									 = 0.0;
	public static final double dDriveGyro 									 = 0.095;

	//**************************************************************************
    //************************ PID VALUES (ELEVATOR) ***************************
    //**************************************************************************
	
	public static final double fTalonElevator 							 = 0.4;//0.0058766;
	public static final double pTalonElevator 							 = 0.35; //0.225
	public static final double iTalonElevator 							 = 0.00; 
	public static final double dTalonElevator 							 = 0.0; //0.00001
	
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
	
	public static final double intakingPosition 						  = 0;
	public static final double exchangePosition 			     		  = 10.5; //2
	public static final double switchPosition   						  = 28; //25
	public static final double portalPosition 			    			  = 20;
	public static final double scaleLowPosition 						  = 58;
	public static final double scaleMidPosition 						  = 70;
	public static final double scaleHighPosition 						  = 79;
	public static final int maxElevatorSpeed                       =2750; //2700
	public static final int slowElevatorSpeed                          = 2000; //2000

	
	//**************************************************************************
    //**************************** PID VALUES (ARM) ****************************
    //**************************************************************************
	
	public static final double pARM 									 = 0.0;
	public static final double iARM 									 = 0.000000;
	public static final double dARM 									 = 0.0;	
	
}
