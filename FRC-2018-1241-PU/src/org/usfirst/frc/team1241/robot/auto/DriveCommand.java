package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/** 
 * @author Bryan Kristiono
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class DriveCommand extends Command {
	private double distance;
	private double speed;
	private double angle;
	private double timeOut;
	private double tolerance;
	private boolean velocity;

	public DriveCommand(double setPoint, double speed, double angle, double timeOut) {
		this(setPoint, speed, angle, timeOut, 1, false);
	}
	
	public DriveCommand(double setPoint, double speed, double angle, double timeOut, boolean velocity) {
		this(setPoint, speed, angle, timeOut, 1, velocity);
	}

    public DriveCommand(double setPoint, double speed, double angle, double timeOut, double tolerance, boolean velocity) {
    	this.distance = setPoint;
    	this.speed = speed;
    	this.angle = angle;
    	this.timeOut = timeOut;
    	this.tolerance = tolerance;
    	this.velocity = velocity;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.changeDriveGains(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
    	Robot.drive.changeGyroGains(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
    	Robot.drive.resetEncoders();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drive.driveSetpoint(distance, speed, angle, tolerance);
    	if(velocity){
	    	// Speed must be in RPM
	    	Robot.drive.driveVelocitySetpoint(distance, speed, angle, tolerance);
    	}
    	else
    		Robot.drive.driveSetpoint(distance, speed, angle, tolerance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();//Robot.drive.drivePIDDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    	Robot.drive.resetPID();
    	Robot.drive.voltageMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
    	Robot.drive.voltageMode();
    }
}
