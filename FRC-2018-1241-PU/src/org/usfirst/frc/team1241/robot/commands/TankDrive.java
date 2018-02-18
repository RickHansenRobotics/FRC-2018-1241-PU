package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.auto.climber.HangSequence;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is used to set a default command for the Drivetrain subsystem.
 * This command allows the driver to control the robot using tank drive.
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/18
 */
public class TankDrive extends Command {
	//private HangSequence hangSequence;
	private boolean isPTOengaged = false;
	public TankDrive() {
		requires(Robot.drive);
		//hangSequence = new HangSequence();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getDriveRightBumper()) {
			Robot.drive.runLeftDrive(-0.5 * Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(0.5 * Robot.oi.getDriveRightY());
			
		} else {
			Robot.drive.runLeftDrive(-Robot.oi.getDriveLeftY());
			Robot.drive.runRightDrive(Robot.oi.getDriveRightY());
		}
		
		/*if (Robot.oi.getDriveAButton() && !isPTOengaged){
			hangSequence.start();
			isPTOengaged = true;
		}
		if (hangSequence.isCompleted() && Robot.oi.getDriveAButton()){
			Robot.drive.runLeftDrive(0.75);
			Robot.drive.runRightDrive(-0.75);
		}*/

		if (Robot.oi.getDriveStartButton()) {
			Robot.climber.extendPTOPiston();
			System.out.println("INSIDE Start");
		} else if (Robot.oi.getDriveBackButton()){
			Robot.climber.retractPTOPiston();
			System.out.println("INSIDE Back");
		}
		
		if(Robot.oi.getDriveYButton()){
			Robot.drive.magicMotionSetpoint(60, 2000, 1);
		}
		
		//System.out.println("Joystick: " + Robot.oi.getDriveLeftY() + " Velocity: " + Robot.drive.getLeftSpeed());
		//System.out.println("Joystick: " + Robot.oi.getDriveRightY() + " Velocity: " + Robot.drive.getRightSpeed());
		
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
