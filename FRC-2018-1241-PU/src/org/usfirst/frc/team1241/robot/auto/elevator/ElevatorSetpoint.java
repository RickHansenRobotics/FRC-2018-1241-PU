package org.usfirst.frc.team1241.robot.auto.elevator;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Kaveesha Siribaddana
 */
public class ElevatorSetpoint extends Command {

	private double setpoint;
	private int speed;
	private double timeToMax;
	private double timeOut;

	public ElevatorSetpoint(double setPoint, int speed, double timeToMax, double timeOut) {

		requires(Robot.elevator);
		this.setpoint = setPoint;
		this.speed = speed;
		this.timeToMax = timeToMax;
		this.timeOut = timeOut;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/*Robot.elevator.changeElevatorGains(NumberConstants.pElevator, NumberConstants.iElevator,
				NumberConstants.dElevator);*/
		setTimeout(timeOut);
		//Robot.elevator.magicMotionSetpoint(setpoint, speed, timeToMax);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.elevator.elevatorSetpoint(setpoint, speed, tolerance);
		Robot.elevator.magicMotionSetpoint(setpoint, speed, timeToMax);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();// Robot.elevator.elevatorPIDDone() || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
