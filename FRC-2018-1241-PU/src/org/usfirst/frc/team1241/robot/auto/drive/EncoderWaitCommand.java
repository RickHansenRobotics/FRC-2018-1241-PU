package org.usfirst.frc.team1241.robot.auto.drive;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncoderWaitCommand extends Command {

	private double setpoint;
	private double timeout;
	private int subsystem;
	public static final int DRIVE = 0;
	public static final int ELEVATOR = 1;
	
    public EncoderWaitCommand(double setpoint, double timeout, int subsystem) {
        this.timeout = timeout;
        this.setpoint = setpoint;
        this.subsystem = subsystem;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("hello " + Robot.drive.getAverageDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (subsystem == DRIVE){
    		return isTimedOut() || Robot.drive.getAverageDistance() >= setpoint;
    	}
    	else if (subsystem == ELEVATOR){
    		return isTimedOut() || Robot.elevator.getElevatorEncoder() >= setpoint;
    	}
    	else 
    		return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
