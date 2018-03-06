package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.subsystems.LEDstrips;
import org.usfirst.frc.team1241.robot.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Kaveesha Siribaddana
 * @since 10/01/2018
 */
public class IntakeCommand extends Command {

	ToggleBoolean toggle = new ToggleBoolean();
	
	public IntakeCommand() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.oi.getToolLeftBumper()) { //outtake
			Robot.intake.outtake(Robot.outtakeSpeed);
			/* if elevator height < exchange position
			 * 	outtake @ 1
			 * else if elevator height > exchange && < scale low position
			 * 	outtake @ 0.75
			 * else if elevator height > scale low && < scale high position
			 * 	outtake @ 0.5
			 */
		} else if (Robot.oi.getToolRightBumper()) { //intake
			Robot.intake.intake(Robot.intakeSpeed);
		} else {
			Robot.intake.stop();
		}

		toggle.set(Robot.oi.getToolRightTrigger());
		
		if(toggle.get()) {
			Robot.intake.retractIntakePistons();
		} else {
			Robot.intake.extendIntakePistons();
		} 
		
		if (Robot.intake.getOptic()) {
			LEDstrips.solidBlue();
		} else {
			LEDstrips.solidGreen();
		}

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
