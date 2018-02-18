package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.commands.ElevatorCommand;
import org.usfirst.frc.team1241.robot.pid.PIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	// Elevator Cim TalonSRX speed Controller
	private WPI_TalonSRX elevator;
	// String for changing between Talon control modes
	private String controlMode;
	private boolean encoderConnected = false;

	// Elevator PID Controller
	public PIDController elevatorPID;
	public PIDController elevatorLockPID;
	public static double pidOutput = 0.0;

	// Elevator Optical Hardstop
	private DigitalInput opticalHardstop;

	public Elevator() {

		// Elevator
		elevator = new WPI_TalonSRX(ElectricalConstants.ELEVATOR_CIM);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		elevator.setInverted(false);
		elevator.setSensorPhase(true);
		elevator.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		//elevator.configOpenloopRamp(0.2, 0); // Takes 2 seconds to ramp to full
												// throttle0.2

		/*
		 * elevator.configForwardSoftLimitEnable(true, 0);
		 * elevator.configForwardSoftLimitThreshold(NumberConstants.topHardStop,
		 * 0);
		 * 
		 * elevator.configReverseSoftLimitEnable(true, 0);
		 * elevator.configReverseSoftLimitThreshold(NumberConstants.
		 * bottomHardStop, 0);
		 */

		voltageMode();
		resetEncoders();

		elevator.selectProfileSlot(0, 0);

		
		 // Method in order to set a default Motion Magic Velocity and Acceleration 
		   elevator.configMotionAcceleration(2500, 0);
		   elevator.configMotionCruiseVelocity(2500, 0);
		 

		elevator.config_kF(0, NumberConstants.fTalonElevator, 10);
		elevator.config_kP(0, NumberConstants.pTalonElevator, 0);
		elevator.config_kI(0, NumberConstants.iTalonElevator, 0);
		elevator.config_kD(0, NumberConstants.dTalonElevator, 0);

		elevatorPID = new PIDController(NumberConstants.pElevator, NumberConstants.iElevator,
				NumberConstants.dElevator);
		elevatorLockPID = new PIDController(NumberConstants.pLockElevator, NumberConstants.iLockElevator,
				NumberConstants.dLockElevator);

		opticalHardstop = new DigitalInput(ElectricalConstants.OPTICAL_BOTTOM);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorCommand());
	}

	public void motionProfileMode() {
		controlMode = "MotionProfile";
	}

	public void motionMagicMode() {
		controlMode = "MotionMagic";
	}

	public void voltageMode() {
		controlMode = "PercentOutput";
	}

	public void velocityMode() {
		controlMode = "Velocity";
	}

	public double getClosedLoopErrors() {
		return elevator.getClosedLoopError(0);
	}

	public void runElevatorMotionMagic(double setpoint) {
		elevator.set(ControlMode.MotionMagic, setpoint);
	}

	public double getMotionMagicError() {
		return elevator.getClosedLoopError(0);
	}

	public void runElevator(double power) {
		elevator.set(ControlMode.PercentOutput, power);
	}

	public void elevatorSetpoint(double setPoint, double speed, double tolerance) {
		elevatorPID.changePIDGains(Robot.pElevator, Robot.iElevator, Robot.dElevator);
		double output = elevatorPID.calcPID(setPoint, getElevatorEncoder(), tolerance);

		runElevator(output * speed);
	}

	public void elevatorHoldPosition(double speed) {
		double holdPosition = getElevatorEncoder();
		double output = elevatorLockPID.calcPID(holdPosition, getElevatorEncoder(), 0.1);

		runElevator(output * speed);
	}

	public void magicMotionSetpoint(double setpoint, int cruiseVelocity, double secsToMaxSpeed) {
		
		elevator.configMotionCruiseVelocity(cruiseVelocity, 0);
		elevator.configMotionAcceleration((int)(NumberConstants.maxElevatorSpeed/secsToMaxSpeed), 0);

		runElevatorMotionMagic(setpoint*-ElectricalConstants.elevatorRotatationsToInches);
	}

	// ************************Mag Encoder Functions************************
	public boolean isEncoderConnected() {
		return encoderConnected;
	}

	public double getElevatorEncoder() {
		return elevator.getSelectedSensorPosition(0) / - ElectricalConstants.elevatorRotatationsToInches;
	}

	public double getElevatorRotations() {
		return elevator.getSelectedSensorPosition(0);
	}

	public double getElevatorSpeed() {
		return elevator.getSelectedSensorVelocity(0);
	}

	public void resetEncoders() {
		elevator.setSelectedSensorPosition(0, 0, 0);
	}

	// ************************** PID Functions ******************************
	public void changeElevatorGains(double p, double i, double d) {
		elevatorPID.changePIDGains(p, i, d);
	}

	public void resetPID() {
		elevatorPID.resetPID();
	}

	// ************************** Optical Functions
	// ******************************
	public boolean bottomHardstop() {
		return opticalHardstop.get();
	}
}
