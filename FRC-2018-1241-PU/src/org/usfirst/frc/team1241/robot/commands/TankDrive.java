package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is used to set a default command for the Drivetrain subsystem.
 * This command allows the driver to control the robot using tank drive.
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 */
public class TankDrive extends Command {

	public TankDrive() {
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.oi.getDriveRightBumper()) {
			Robot.drive.runLeftDrive(1 * Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(-1 * Robot.oi.getDriveRightY());
		} else if (Robot.oi.getDriveRightTrigger()) {
			Robot.drive.runLeftDrive(0.25 * (Robot.oi.getDriveLeftY()));
			Robot.drive.runRightDrive(-0.25 * (Robot.oi.getDriveRightY()));
		} else {
			Robot.drive.runLeftDrive(0.5 * Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(-0.5 * Robot.oi.getDriveRightY());
		}
		/*
		 * if(Robot.oi.getDriveLeftY()<0 && Robot.oi.getDriveRightY()>0){
		 * Robot.drive.setLeftRampRate(160); Robot.drive.setRightRampRate(160);
		 * } else if(Robot.oi.getDriveLeftY()>0 && Robot.oi.getDriveRightY()<0){
		 * Robot.drive.setLeftRampRate(160); Robot.drive.setRightRampRate(160);
		 * } else { Robot.drive.setLeftRampRate(70);
		 * Robot.drive.setRightRampRate(70); }
		 */

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
