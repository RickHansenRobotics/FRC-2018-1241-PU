package org.usfirst.frc.team1241.robot.auto.drive;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/** 
 * 
 * @author Bryan Kristiono
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class DriveCommand extends Command {
	private double distance;
	private double speed;
	private double angle;
	private double timeOut;
	private double turnDistance;
	private double angleTwo;
	private double speedTwo;
	private double tolerance;

	
	Timer timer;
	private boolean timerStarted = false;
	public DriveCommand(double setPoint, double speed, double angle, double timeOut) {
		this(setPoint, speed, angle, timeOut, setPoint, angle, speed, 2.5);
	}
	
	public DriveCommand(double setPoint, double speed, double angle, double timeOut, double tolerance) {
		this(setPoint, speed, angle, timeOut, setPoint, angle, speed, tolerance);
	}
	public DriveCommand(double setPoint, double speed, double angle, double timeOut, double turnDistance, double angleTwo, double speedTwo) {
		this(setPoint, speed, angle, timeOut, turnDistance, angleTwo, speedTwo, 2.5);
	}
	

    public DriveCommand(double setPoint, double speed, double angle, double timeOut, double turnDistance, double angleTwo, double speedTwo, double tolerance) {
    	this.distance = setPoint;
    	this.speed = speed;
    	this.angle = angle;
    	this.timeOut = timeOut;
    	this.turnDistance = turnDistance;
    	this.angleTwo = angleTwo;
    	this.speedTwo = speedTwo;
    	this.tolerance = tolerance;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.changeDriveGains(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
    	Robot.drive.changeGyroGains(NumberConstants.pDriveGyro, NumberConstants.iDriveGyro, NumberConstants.dDriveGyro);
    	//Robot.drive.changeDriveGains(Robot.pDrive, Robot.iDrive, Robot.dDrive);
    	//Robot.drive.changeGyroGains(Robot.pGyro, Robot.iGyro, 0);
    	Robot.drive.resetEncoders();
    	timer = new Timer();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drive.driveSetpoint(distance, speed, angle, tolerance);
    	if(Math.abs(Robot.drive.getAverageDistance()) > Math.abs(turnDistance)){
    		Robot.drive.driveSetpoint(distance, speedTwo, angleTwo, 1);
    		//Robot.logger.logd("New Angle", ""+angleTwo);
    	}
    	else
    		Robot.drive.driveSetpoint(distance, speed, angle, 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(distance - Robot.drive.getAverageDistance()) <= tolerance){
    		if(!timerStarted){
    			timer.start();
    			timerStarted = true;
    		}
    		//System.out.println("Timer: " + timer.get());
    		if(timer.get() > 0.25){
    			//System.out.println("GOT TO SETPOINT");
    			Robot.logger.logd("DriveCommand", "Got to setpoint: " + distance);
    			return true;
    		}
    	}else{
			timer.stop();
			timer.reset();
			timerStarted = false;
		}
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.logger.logd("DriveCommand", "Current: " + Robot.drive.getAverageDistance());
    	//System.out.println("Setpoint: " + distance + " Current: " + Robot.drive.getAverageDistance());
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
