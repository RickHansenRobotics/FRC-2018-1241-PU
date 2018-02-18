package org.usfirst.frc.team1241.robot.auto.drive;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Kaveesha Siribaddana
 */
public class TurnCommand extends Command {

	// Variables to hold parameter information
	private double angle;
	private double speed;
	private double timeOut;
	private double tolerance;

	/**
	 * Instantiates a new turn command.
	 *
	 * @param angle
	 *            Angle the robot will turn to (-180 <-> 180)
	 * @param speed
	 *            The speed the robot will turn at (0.0 - 1.0)
	 * @param timeOut
	 *            The time out in seconds
	 * @param tolerance
	 * 			  How close to target is considered "reached"
	 */
	public TurnCommand(double angle, double speed, double timeOut, double tolerance) {
		this.angle = angle;
		this.speed = speed;
		this.timeOut = timeOut;
		this.tolerance = tolerance;
		requires(Robot.drive);
	}
	
	public void changeAngle(double angle){
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.changeGyroGains(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
		setTimeout(timeOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.turnDrive(angle, speed, tolerance);
	}

	// Command is finished when timed out
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true, once done will stop robot from
	// moving.
	protected void end() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run, once done will stop robot from moving.
	protected void interrupted() {
		Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
	}
}
