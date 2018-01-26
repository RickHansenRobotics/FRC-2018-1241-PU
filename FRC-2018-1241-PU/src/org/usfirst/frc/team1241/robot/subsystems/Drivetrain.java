package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.commands.TankDrive;
import org.usfirst.frc.team1241.robot.pid.PIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Kaveesha Siribaddana
 * @since 11/01/18
 *
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlave1;
	private WPI_TalonSRX leftSlave2;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlave1;
	private WPI_TalonSRX rightSlave2;

	/** Encoders on the drive */
	private boolean leftEncoderConnected = false;
	private boolean rightEncoderConnected = false;

	/** Gyro on the drive */
	AHRS gyro;

	/** The drive PID controller. */
	private PIDController drivePID;
	private PIDController leftPID;
	private PIDController rightPID;

	/** The gyro PID conteroller. */
	private PIDController gyroPID;
	private String controlMode;

	/**
	 * Instantiates a new drivetrain subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Drivetrain() {
		
		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("ERROR navX: " + ex.getMessage(), true);
		}

		// Initialize Talons
		leftMaster = new WPI_TalonSRX(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMaster.setInverted(true);

		leftSlave1 = new WPI_TalonSRX(ElectricalConstants.LEFT_DRIVE_MIDDLE);
		leftSlave1.set(ControlMode.Follower, ElectricalConstants.LEFT_DRIVE_FRONT);
		
		leftSlave2 = new WPI_TalonSRX(ElectricalConstants.LEFT_DRIVE_BACK);
		leftSlave2.set(ControlMode.Follower, ElectricalConstants.LEFT_DRIVE_FRONT);

		rightMaster = new WPI_TalonSRX(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.setInverted(false);

		rightSlave1 = new WPI_TalonSRX(ElectricalConstants.RIGHT_DRIVE_MIDDLE);
		rightSlave1.set(ControlMode.Follower, ElectricalConstants.RIGHT_DRIVE_FRONT);
		
		rightSlave2 = new WPI_TalonSRX(ElectricalConstants.RIGHT_DRIVE_BACK);
		rightSlave2.set(ControlMode.Follower, ElectricalConstants.RIGHT_DRIVE_FRONT);

		// FeedbackDeviceStatus leftStatus =
		// leftMaster.isSensorPresent(FeedbackDevice.CTRE_MagEncoder_Relative);
		// FeedbackDeviceStatus rightStatus =
		// rightMaster.isSensorPresent(FeedbackDevice.CTRE_MagEncoder_Relative);
		//
		// switch (leftStatus) {
		// case FeedbackStatusPresent:
		// leftEncoderConnected = true;
		// break;
		// case FeedbackStatusNotPresent:
		// break;
		// case FeedbackStatusUnknown:
		// break;
		// }
		//
		// switch (rightStatus) {
		// case FeedbackStatusPresent:
		// rightEncoderConnected = true;
		// break;
		// case FeedbackStatusNotPresent:
		// break;
		// case FeedbackStatusUnknown:
		// break;
		// }

		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);

		leftPID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		rightPID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);

		// rightMaster.selectProfileSlot(0, 0);
		// rightMaster.setPID(0.01, 0, 0);
		// rightMaster.setF(0.2670508);
		//
		// THIS MIGHT BE THE V5 CODE
		// rightMaster.config_kP(val, timeoutMs);
		// rightMaster.config_kI(val, timeoutMs);
		// rightMaster.config_kD(val, timeoutMs);
		// rightMaster.config_kF(val, timeoutMs);
		//
		// leftMaster.setProfile(0);
		// leftMaster.setPID(0.01, 0, 0);
		// leftMaster.setF(0.2670508);

		controlMode = "PercentOutput";
		resetEncoders();
		resetGyro();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void runLeftDrive(double input) {
		String controlMode = this.controlMode;
		if (controlMode.equalsIgnoreCase("PercentOutput"))
			leftMaster.set(ControlMode.PercentOutput, input);
		else if (controlMode.equalsIgnoreCase("Velocity"))
			leftMaster.set(ControlMode.Velocity, input);
		else
			leftMaster.set(ControlMode.PercentOutput, input);
	}

	public void runRightDrive(double input) {
		String controlMode = this.controlMode;
		if (controlMode.equalsIgnoreCase("PercentOutput"))
			rightMaster.set(ControlMode.PercentOutput, input);
		else if (controlMode.equalsIgnoreCase("Velocity"))
			rightMaster.set(ControlMode.Velocity, input);
		else
			rightMaster.set(ControlMode.PercentOutput, input);
	}

	public WPI_TalonSRX getRightMaster() {
		return rightMaster;
	}

	public WPI_TalonSRX getLeftMaster() {
		return leftMaster;
	}

	public void motionProfileMode() {
		controlMode = "MotionProfile";
	}

	public void voltageMode() {
		controlMode = "PercentOutput";
	}

	public void velocityMode() {
		controlMode = "Velocity";
	}

	public void setLeftRampRate(double rampRate) {
		leftMaster.set(ControlMode.Velocity, rampRate);
	}

	public void setRightRampRate(double rampRate) {
		rightMaster.set(ControlMode.Velocity, rampRate);
	}

	public void driveSetpoint(double setPoint, double speed, double setAngle, double tolerance) {
		double output = drivePID.calcPID(setPoint, getAverageDistance(), tolerance);
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);
		SmartDashboard.putNumber("PID OUTPUT", angle);
		runLeftDrive((-output - angle) * speed);
		runRightDrive((output - angle) * speed);
	}

	public void driveVelocitySetpoint(double setPoint, double speed, double setAngle, double tolerance) {
		velocityMode();
		double leftOutput = leftPID.calcPID(setPoint, getLeftDriveEncoder(), tolerance);
		double rightOutput = rightPID.calcPID(setPoint, getRightDriveEncoder(), tolerance);
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);

		runLeftDrive((-leftOutput - angle) * speed);
		runRightDrive((rightOutput - angle) * speed * 0.975);
	}

	public void turnDrive(double setAngle, double speed, double tolerance) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), tolerance);
		double min = 0.15;

		if (Math.abs(setAngle - getYaw()) < tolerance) {
			runLeftDrive(0);
			runRightDrive(0);
		} else if (angle > -min && angle < 0) {
			runLeftDrive(min);
			runRightDrive(min);
		} else if (angle < min && angle > 0) {
			runLeftDrive(-min);
			runRightDrive(-min);
		} else {
			runLeftDrive(-angle * speed);
			runRightDrive(-angle * speed);
		}
	}

	public void driveAngle(double setAngle, double speed) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), 1);

		runLeftDrive(-speed - angle);
		runRightDrive(speed - angle);
	}
	
	public boolean drivePIDDone() {
		return drivePID.isDone();
	}

	public boolean gyroPIDDone() {
		return gyroPID.isDone();
	}

	public void changeDriveGains(double p, double i, double d) {
		drivePID.changePIDGains(p, i, d);
	}

	public void changeGyroGains(double p, double i, double d) {
		gyroPID.changePIDGains(p, i, d);
	}

	public void resetPID() {
		drivePID.resetPID();
		gyroPID.resetPID();
	}

	// ENCODER FUNCTIONS

	public double getLeftDriveEncoder() {
		return leftMaster.getSelectedSensorPosition(0) * ElectricalConstants.ROTATIONS_TO_INCHES;
	}

	public double getRightDriveEncoder() {
		return rightMaster.getSelectedSensorPosition(0) * ElectricalConstants.ROTATIONS_TO_INCHES;
	}

	public double getLeftDriveRotations() {
		return leftMaster.getSelectedSensorPosition(0);
	}

	public double getRightDriveRotations() {
		return rightMaster.getSelectedSensorPosition(0);
	}

	public double getLeftSpeed() {
		return leftMaster.getSelectedSensorVelocity(0);
	}

	public double getRightSpeed() {
		return rightMaster.getSelectedSensorVelocity(0);
	}

	public double getAverageDistance() {
		return (getLeftDriveEncoder() + getRightDriveEncoder()) / 2;
	}

	public boolean isLeftEncoderConnected() {
		return leftEncoderConnected;
	}

	public boolean isRightEncoderConnected() {
		return rightEncoderConnected;
	}

	public void resetEncoders() {
		leftMaster.setSelectedSensorPosition(0, 0, 0);
		rightMaster.setSelectedSensorPosition(0, 0, 0);
	}

	// GYRO FUNCTIONS

	public boolean gyroConnected() {
		return gyro.isConnected();
	}

	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}

	public double getYaw() {
		return gyro.getAngle();
	}

	public double getPitch() {
		return gyro.getPitch();
	}

	public double getRoll() {
		return gyro.getRoll();
	}

	public double getCompassHeading() {
		return gyro.getCompassHeading();
	}

	public void resetGyro() {
		gyro.zeroYaw();
	}

	public void reset() {
		resetEncoders();
		resetGyro();
	}
}
