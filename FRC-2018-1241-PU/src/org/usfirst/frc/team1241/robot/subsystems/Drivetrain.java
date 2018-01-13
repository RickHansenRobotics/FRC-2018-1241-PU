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
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 *
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftSlave;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightSlave;

	/** Encoders on the drive */
	private boolean leftEncoderConnected = false;
	private boolean rightEncoderConnected = false;

	/** Gyro on the drive *//*
							 * private SerialPort serialPort; private Nav6 gyro;
							 */
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
		/*
		 * try { serialPort = new SerialPort(57600, SerialPort.Port.kOnboard);
		 * 
		 * // You can add a second parameter to modify the // update rate (in
		 * hz) from 4 to 100. The default is 100. // If you need to minimize CPU
		 * load, you can set it to a // lower value, as shown here, depending
		 * upon your needs.
		 * 
		 * // You can also use the IMUAdvanced class for advanced // features.
		 * 
		 * byte update_rate_hz = 50; gyro = new Nav6(serialPort,
		 * update_rate_hz);
		 * 
		 * if (!gyro.isCalibrating()) { Timer.delay(0.3); gyro.zeroYaw(); } }
		 * catch (Exception e) { gyro = null; }
		 */

		try {
			gyro = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("ERROR navX: " + ex.getMessage(), true);
		}

		// Initialize Talons
		leftMaster = new WPI_TalonSRX(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMaster.setInverted(true);
		// leftMaster.setVoltageRampRate(30);

		leftSlave = new WPI_TalonSRX(ElectricalConstants.LEFT_DRIVE_BACK);
		leftSlave.set(ControlMode.Follower, 0);
		leftSlave.set(ElectricalConstants.LEFT_DRIVE_FRONT);

		rightMaster = new WPI_TalonSRX(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rightMaster.setInverted(false);
		// rightMaster.setVoltageRampRate(30);

		rightSlave = new WPI_TalonSRX(ElectricalConstants.RIGHT_DRIVE_BACK);
		rightSlave.set(ControlMode.Follower, 2);
		rightSlave.set(ElectricalConstants.RIGHT_DRIVE_FRONT);

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

//		rightMaster.selectProfileSlot(0, 0);
//		rightMaster.setPID(0.01, 0, 0);
//		rightMaster.setF(0.2670508);
//
//		rightMaster.config_kP(val, timeoutMs);
//		rightMaster.config_kI(val, timeoutMs);
//		rightMaster.config_kD(val, timeoutMs);
//		rightMaster.config_kF(val, timeoutMs);
//
//		leftMaster.setProfile(0);
//		leftMaster.setPID(0.01, 0, 0);
//		leftMaster.setF(0.2670508);

		controlMode = "PercentOutput";
		resetEncoders();
		resetGyro();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void runLeftDrive(double input) {
		String controlMode = this.controlMode;
		if(controlMode.equalsIgnoreCase("PercentOutput"))
			leftMaster.set(ControlMode.PercentOutput, input);
		else if(controlMode.equalsIgnoreCase("Velocity"))
			leftMaster.set(ControlMode.Velocity, input);
		else if(controlMode.equalsIgnoreCase("MotionProfile"))
			leftMaster.set(ControlMode.MotionProfile, input);
		else 
			leftMaster.set(ControlMode.PercentOutput, input);
	}
	
	public void runRightDrive(double input) {
		String controlMode = this.controlMode;
		if(controlMode.equalsIgnoreCase("PercentOutput"))
			rightMaster.set(ControlMode.PercentOutput, input);
		else if(controlMode.equalsIgnoreCase("Velocity"))
			rightMaster.set(ControlMode.Velocity, input);
		else if(controlMode.equalsIgnoreCase("MotionProfile"))
			rightMaster.set(ControlMode.MotionProfile, input);
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
	
	public void velocityMode(){
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

	/**
	 * Converts the pixel offset from the center of the image to degrees, which
	 * is then used for turning the turret
	 *
	 * @param pixel
	 *            The x coordinate pixel from the image. Ranging from 0 to 640
	 *            on a 480x640 image
	 * @return the double
	 */
	public double pixelToDegree(double pixel) {
		return Math.toDegrees(Math.atan(((pixel - 320) * Math.tan(Math.toRadians(31.81))) / 320));
		/*
		 * double y = 4*Math.pow(10, -13)*Math.pow(pixel,6) - 4*Math.pow(10,
		 * -10)*Math.pow(pixel,5) + Math.pow(10, -7)*Math.pow(pixel,4)
		 * -Math.pow(10, -5)*Math.pow(pixel,3) - 0.0011*Math.pow(pixel, 2) +
		 * 0.0977*pixel + 11.537; return y;
		 */
	}

	public double getOffset(double y) {
		return 0.158593 * y - 1.375;
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

	/*
	 * public double getYaw() { return gyro.getYaw(); }
	 */

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
