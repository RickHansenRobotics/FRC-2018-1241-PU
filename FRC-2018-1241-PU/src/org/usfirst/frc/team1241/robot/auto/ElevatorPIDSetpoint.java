package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Kaveesha Siribaddana
 */
public class ElevatorPIDSetpoint extends Command {

	private double setpoint;
	private double speed;
	private double timeOut;
	private double tolerance;

	public ElevatorPIDSetpoint(double setPoint, double speed, double timeOut, double tolerance) {

		requires(Robot.elevator);
		this.setpoint = setPoint;
		this.speed = speed;
		this.timeOut = timeOut;
		this.tolerance = tolerance;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/*Robot.elevator.changeElevatorGains(NumberConstants.pElevator, NumberConstants.iElevator,
				NumberConstants.dElevator);*/
		Robot.elevator.resetEncoders();
		setTimeout(timeOut);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.elevator.elevatorSetpoint(setpoint, speed, tolerance);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();// Robot.elevator.elevatorPIDDone() || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.runElevator(0);
		Robot.elevator.resetPID();
		Robot.elevator.voltageMode();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.runElevator(0);
		Robot.elevator.resetPID();
		Robot.elevator.voltageMode();
	}
}
