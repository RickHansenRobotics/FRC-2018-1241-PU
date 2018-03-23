package org.usfirst.frc.team1241.robot.auto.intake;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WaitUntilDetected extends Command {

	private double target;

	public WaitUntilDetected(double target) {
		this.target = target;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		SmartDashboard.putBoolean("Got cube", Robot.intake.getUltrasonicRange() < target);
		System.out.println("Hello, I am in here and I am " + Robot.intake.getUltrasonicRange());
		return Robot.intake.getUltrasonicRange() < target;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
