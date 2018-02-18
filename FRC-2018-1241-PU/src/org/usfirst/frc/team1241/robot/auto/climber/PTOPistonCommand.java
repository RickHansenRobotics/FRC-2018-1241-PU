package org.usfirst.frc.team1241.robot.auto.climber;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PTOPistonCommand extends Command {

	private boolean ptoState;
	
    public PTOPistonCommand (boolean ptoState) {
    	this.ptoState = ptoState;
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(ptoState)
    		Robot.climber.extendPTOPiston();
    	else
    		Robot.climber.retractPTOPiston();
    }    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
