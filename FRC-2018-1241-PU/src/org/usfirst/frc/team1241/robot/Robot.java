/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1241.robot;

import org.usfirst.frc.team1241.robot.auto.CrossBaseline;
import org.usfirst.frc.team1241.robot.auto.LeftScale;
import org.usfirst.frc.team1241.robot.auto.LeftSwitch;
import org.usfirst.frc.team1241.robot.auto.LeftSwitchLeftScale;
import org.usfirst.frc.team1241.robot.auto.NoAuto;
import org.usfirst.frc.team1241.robot.auto.RightScale;
import org.usfirst.frc.team1241.robot.auto.RightSwitch;
import org.usfirst.frc.team1241.robot.auto.RightSwitchRightScale;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.subsystems.Climber;
import org.usfirst.frc.team1241.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1241.robot.subsystems.Elevator;
import org.usfirst.frc.team1241.robot.subsystems.Intake;
import org.usfirst.frc.team1241.robot.subsystems.LEDstrips;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Sendable;
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

	public static double intakeSpeed, outtakeSpeed;

	PowerDistributionPanel pdp;

	Command m_autonomousCommand;

	SendableChooser<Integer> positionChooser;

	public static int autoLLNum;
	public static int autoLRNum;
	public static int autoRLNum;
	public static int autoRRNum;
	public static int positionNum;

	SendableChooser<Integer> autoLRChooser;
	SendableChooser<Integer> autoRLChooser;
	SendableChooser<Integer> autoLLChooser;
	SendableChooser<Integer> autoRRChooser;

	SendableChooser<Command> m_chooser = new SendableChooser<>();
	String gameData = "";
	public static double maxIntakeCurrent = -1;
	String clipboard = "Match Strategy";

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

		pdp = new PowerDistributionPanel(0);

		positionChooser = new SendableChooser<Integer>();
		autoLRChooser = new SendableChooser<Integer>();
		autoRLChooser = new SendableChooser<Integer>();
		autoLLChooser = new SendableChooser<Integer>();
		autoRRChooser = new SendableChooser<Integer>();

		positionChooser.setName("Position Chooser");
		positionChooser.addDefault("Left", 0);
		positionChooser.addObject("Center", 1);
		positionChooser.addObject("Right", 2);

		autoLLChooser.setName("Left Switch Left Scale");
		autoLLChooser.addDefault("BaseLine", 0);
		autoLLChooser.addObject("Left Switch", 1);
		autoLLChooser.addObject("Left Scale", 2);
		autoLLChooser.addObject("Left Switch, Left Scale", 3);
		autoLLChooser.addObject("No Auton", 4);

		autoLRChooser.setName("Left Switch Right Scale");
		autoLRChooser.addDefault("BaseLine", 0);
		autoLRChooser.addObject("Left Switch", 1);
		autoLRChooser.addObject("Right Scale", 2);
		autoLRChooser.addObject("No Auton", 3);

		autoRLChooser.setName("Right Switch Left Scale");
		autoRLChooser.addDefault("BaseLine", 0);
		autoRLChooser.addObject("Right Switch", 1);
		autoRLChooser.addObject("Left Scale", 2);
		autoRLChooser.addObject("No Auton", 3);

		autoRRChooser.setName("Right Switch Right Scale");
		autoRRChooser.addDefault("BaseLine", 0);
		autoRRChooser.addObject("Right Switch", 1);
		autoRRChooser.addObject("Right Scale", 2);
		autoRRChooser.addObject("Right Switch Right Scale", 3);
		autoRRChooser.addObject("No Auton", 4);

		pdp = new PowerDistributionPanel(11);

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
		positionNum = (int) positionChooser.getSelected();

		autoLLNum = (int) autoLLChooser.getSelected();
		autoLRNum = (int) autoLRChooser.getSelected();
		autoRLNum = (int) autoRLChooser.getSelected();
		autoRRNum = (int) autoRRChooser.getSelected();

		while (gameData.length() < 3) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
	
		}

		if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
			
			switch (autoLLNum) {
			case 0:
				m_autonomousCommand = (Command) new CrossBaseline();
				break;
			case 1:
				m_autonomousCommand = (Command) new LeftSwitch(positionNum);
				break;
			case 2:
				m_autonomousCommand = (Command) new LeftScale(positionNum);
				break;
			case 3:
				m_autonomousCommand = (Command) new LeftSwitchLeftScale(positionNum);
				break;
			case 4:
				m_autonomousCommand = (Command) new NoAuto();
				break;
			}
		} else if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
			
			switch (autoLRNum) {
			case 0:
				m_autonomousCommand = (Command) new CrossBaseline();
				break;
			case 1:
				m_autonomousCommand = (Command) new LeftSwitch(positionNum);
				break;
			case 2:
				m_autonomousCommand = (Command) new RightScale(positionNum);
				break;
			case 3:
				m_autonomousCommand = (Command) new NoAuto();
				break;
			}
		} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
			
			switch (autoRLNum) {
			case 0:
				m_autonomousCommand = (Command) new CrossBaseline();
				break;
			case 1:
				m_autonomousCommand = (Command) new RightSwitch(positionNum);
				break;
			case 2:
				m_autonomousCommand = (Command) new LeftScale(positionNum);
				break;
			case 3:
				m_autonomousCommand = (Command) new NoAuto();
				break;
			}
		} else if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
			
			switch (autoRRNum) {
			case 0:
				m_autonomousCommand = (Command) new CrossBaseline();
				break;
			case 1:
				m_autonomousCommand = (Command) new RightSwitch(positionNum);
				break;
			case 2:
				m_autonomousCommand = (Command) new RightScale(positionNum);
				break;
			case 3:
				m_autonomousCommand = (Command) new RightSwitchRightScale(positionNum);
				break;
			case 4:
				m_autonomousCommand = (Command) new NoAuto();
				break;
			}
		} 

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
		intake.extendIntakePistons();
		/*
		 * while (gameData.length() < 3) { gameData =
		 * DriverStation.getInstance().getGameSpecificMessage(); }
		 * 
		 * if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
		 * m_autonomousCommand = (Command) autoLRChooser.getSelected(); } else
		 * if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
		 * m_autonomousCommand = (Command) autoRLChooser.getSelected(); } else
		 * if (gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R') {
		 * m_autonomousCommand = (Command) autoRRChooser.getSelected(); } else
		 * if (gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L') {
		 * m_autonomousCommand = (Command) autoLLChooser.getSelected(); } else {
		 * m_autonomousCommand = (Command) new CrossBaseline(); }
		 */
		LEDstrips.solidGold();

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
		// AUTO
		SmartDashboard.putData(autoLLChooser);
		SmartDashboard.putData(autoLRChooser);
		SmartDashboard.putData(autoRLChooser);
		SmartDashboard.putData(autoRRChooser);
		SmartDashboard.putData(positionChooser);

		SmartDashboard.putNumber("Gyro Angle", drive.getYaw());
		SmartDashboard.putNumber("Right Encoder", drive.getRightDriveEncoder());
		SmartDashboard.putNumber("Left Encoder", drive.getLeftDriveEncoder());
		SmartDashboard.putNumber("Elevator Encoder", elevator.getElevatorEncoder());
		SmartDashboard.putNumber("Elevator RPM", elevator.getElevatorSpeed());
		SmartDashboard.putNumber("Left Drive Speed", drive.getLeftSpeed());
		SmartDashboard.putNumber("Right Drive Speed", drive.getRightSpeed());
		SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());

		SmartDashboard.putString("Game Message", DriverStation.getInstance().getGameSpecificMessage());
		SmartDashboard.putString("Match Strategy", clipboard);
		SmartDashboard.putBoolean("Cube In", intake.currentCubeIn());

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

		intakeSpeed = pref.getDouble("Intake Speed", 0.76);
		outtakeSpeed = pref.getDouble("Outtake Speed", 0.61);
		maxIntakeCurrent = pref.getDouble("MaxIntakeCurrent", 27);
		clipboard = pref.getString("Match Strategy", "Defeat the Boss!");

	}
}
