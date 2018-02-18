/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1241.robot;

import org.usfirst.frc.team1241.robot.auto.LeftLeftSwitch;
import org.usfirst.frc.team1241.robot.auto.NoAuto;
import org.usfirst.frc.team1241.robot.subsystems.Climber;
import org.usfirst.frc.team1241.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1241.robot.subsystems.Elevator;
import org.usfirst.frc.team1241.robot.subsystems.Intake;
import org.usfirst.frc.team1241.robot.subsystems.LEDstrips;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	public static OI oi;
	public static Drivetrain drive;
	public static Intake intake;
	public static Elevator elevator;
	public static Climber climber;
	public static LEDstrips ledstrips;

	Preferences pref;

	public static double pDrive;
	public static double iDrive;
	public static double dDrive;
	public static double pGyro;
	public static double iGyro;
	public static double dGyro;

	public static double fTalonElevator;
	public static double pTalonElevator;
	public static double iTalonElevator;
	public static double dTalonElevator;

	public static double pElevator;
	public static double iElevator;
	public static double dElevator;

	public static double pLockElevator;
	public static double iLockElevator;
	public static double dLockElevator;

	SendableChooser autoChooser;

	String gameData;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	// GET RID*********************************************************
	double maxElevatorSpeed = 0;
	double maxLeftDriveSpeed = 0;
	double maxRightDriveSpeed = 0;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		pref = Preferences.getInstance();

		oi = new OI();
		drive = new Drivetrain();
		elevator = new Elevator();
		intake = new Intake();
		climber = new Climber();
		ledstrips = new LEDstrips();

		autoChooser = new SendableChooser();

		autoChooser.addObject("No Auton", new NoAuto());

		updateSmartDashboard();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		LEDstrips.disabled();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		drive.reset();
		elevator.resetEncoders();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		m_autonomousCommand = new LeftLeftSwitch();//new DriveTest();// m_chooser.getSelected();
		LEDstrips.solidGold();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		updateSmartDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		pref = Preferences.getInstance();
		LEDstrips.solidGreen();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("Gyro Angle", drive.getYaw());
		SmartDashboard.putNumber("Right Encoder", drive.getRightDriveEncoder());
		SmartDashboard.putNumber("Left Encoder", drive.getLeftDriveEncoder());
		SmartDashboard.putNumber("Elevator Encoder", elevator.getElevatorEncoder());
		SmartDashboard.putNumber("Elevator Encoder RAW", elevator.getElevatorRotations());
		SmartDashboard.putNumber("Elevator RPM", elevator.getElevatorSpeed());
		SmartDashboard.putNumber("Left Drive Speed", drive.getLeftSpeed());
		SmartDashboard.putNumber("Right Drive Speed", drive.getRightSpeed());
		
		if (elevator.getElevatorSpeed() > maxElevatorSpeed) {
			maxElevatorSpeed = elevator.getElevatorSpeed();
			SmartDashboard.putNumber("MAX Elevator RPM", maxElevatorSpeed);
		}
		
		if (drive.getLeftSpeed() < maxLeftDriveSpeed) {
			maxLeftDriveSpeed = drive.getLeftSpeed();
			SmartDashboard.putNumber("Left Drive Max Speed", maxLeftDriveSpeed);
		}
		
		if (drive.getRightSpeed() < maxRightDriveSpeed) {
			maxRightDriveSpeed = drive.getRightSpeed();
			SmartDashboard.putNumber("Right Drive Max Speed", maxRightDriveSpeed);
		}

		pDrive = pref.getDouble("Drive pGain", 0.0);
		iDrive = pref.getDouble("Drive iGain", 0.0);
		dDrive = pref.getDouble("Drive dGain", 0.0);

		pGyro = pref.getDouble("Gyro pGain", 0.0);
		iGyro = pref.getDouble("Gyro iGain", 0.0);
		dGyro = pref.getDouble("Gyro dGain", 0.0);

		fTalonElevator = pref.getDouble("Elevator Profile fGain", 0.0);
		pTalonElevator = pref.getDouble("Elevator Profile pGain", 0.0);
		iTalonElevator = pref.getDouble("Elevator Profile iGain", 0.0);
		dTalonElevator = pref.getDouble("Elevator Profile dGain", 0.0);

		pElevator = pref.getDouble("Elevator pGain", 0.0);
		iElevator = pref.getDouble("Elevator iGain", 0.0);
		dElevator = pref.getDouble("Elevator dGain", 0.0);

		pLockElevator = pref.getDouble("Elevator Lock pGain", 0.0);
		iLockElevator = pref.getDouble("Elevator Lock ipGain", 0.0);
		dLockElevator = pref.getDouble("Elevator Lock dGain", 0.0);

	}
}
