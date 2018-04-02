package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Kaveesha Siribaddana
 * @since 10/01/2018
 */
public class IntakeCommand extends Command {

	ToggleBoolean toggle = new ToggleBoolean();
	Timer timer;
	private boolean started = false;


	public IntakeCommand() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!DriverStation.getInstance().isAutonomous()) {

			/*
			 * if (Robot.oi.getToolLeftBumper()) { Robot.intake.outtake(0.6); }
			 * else if (Robot.oi.getToolRightBumper()) {
			 * Robot.intake.intake(0.7); } else if (Robot.oi.getToolBButton())
			 * Robot.intake.spinCube(); else { Robot.intake.stop(); }
			 */

			/// From PracticeBot for BC
			if(!Robot.intake.getOptic() && started == false) {
	    		timer.start();
	    		started = true;
	    	}
			
			if(!Robot.intake.getOptic() && timer.get()>0.25) {
	    		timer.reset();
	    		timer.stop();
	    		Robot.intake.setContains(true);
	    		Robot.intake.stop();
	    	}
			if(Robot.intake.getOptic()) {
	    		started = false;
	    		timer.reset();
	    		timer.stop();
	    		Robot.intake.setContains(false);
	    	}
			
			
			if (Robot.oi.getToolLeftBumper()) { // outtake
				if (Robot.elevator.getElevatorEncoder() >= NumberConstants.scaleMidPosition - 3)
					Robot.intake.outtake(Robot.intake.highOuttake);
				else if (Robot.elevator.getElevatorEncoder() <= NumberConstants.exchangePosition + 4)
					Robot.intake.outtake(Robot.intake.regOuttake);
				else
					Robot.intake.outtake(Robot.intake.lowOuttake);
				//If yu want to be shortt, you can have the below as a && (B or C) Rference text document pn desktop if still there 
			} else if ((Robot.oi.getToolRightBumper() && !Robot.intake.getContains()) || (Robot.oi.getToolRightBumper() && !Robot.autoIntake)) { // intake
				Robot.intake.intake(Robot.intake.intakeSpeed);
				// Robot.intake.intakeCurrent(NumberConstants.maxIntakeCurrent *
				// Robot.intakeSpeed);
			} else if (Robot.oi.getToolXButton()) {
				Robot.intake.outtake(Robot.intake.slowOuttake);
			} else {
				Robot.intake.stop();
			}
			/// From PracticeBot for BC

			toggle.set(Robot.oi.getToolLeftTrigger());

			if (toggle.get()) {
				Robot.intake.retractIntakePistons();
			} else {
				Robot.intake.extendIntakePistons();
			}

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
