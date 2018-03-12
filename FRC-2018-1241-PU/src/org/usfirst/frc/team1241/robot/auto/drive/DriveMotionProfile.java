package org.usfirst.frc.team1241.robot.auto.drive;

import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.utilities.MotionProfileExample;

import com.ctre.phoenix.motion.SetValueMotionProfile;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMotionProfile extends Command {
	
	private boolean hasStarted = false;
	
	MotionProfileExample leftDrive;
	MotionProfileExample rightDrive;

    public DriveMotionProfile(double [][] leftPoints, double [][] rightPoints, int numPoints) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drive);
        
        leftDrive = new MotionProfileExample(Robot.drive.getLeftMaster(), leftPoints, numPoints);
        rightDrive = new MotionProfileExample(Robot.drive.getRightMaster(), rightPoints, numPoints);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	leftDrive.reset();
    	rightDrive.reset();
    	Robot.drive.motionProfileMode();
    	Robot.drive.resetEncoders();
    	setTimeout(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftDrive.control();
    	rightDrive.control();
    	
    	Robot.drive.runLeftDrive(leftDrive.getSetValue().value);
    	Robot.drive.runRightDrive(rightDrive.getSetValue().value);
    	System.out.println("Motion profile value: " + rightDrive.getSetValue().value);
    	if(!hasStarted){
    		leftDrive.startMotionProfile();
    		rightDrive.startMotionProfile();
    		hasStarted = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftDrive.reset();
    	rightDrive.reset();
    	Robot.drive.voltageMode();
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	leftDrive.reset();
    	rightDrive.reset();
    	Robot.drive.voltageMode();
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }
}
