package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LedCommand extends Command {

    public LedCommand() {
       requires(Robot.ledstrips);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// LEDstrips.solid();	// sets solid blue
    	// LEDstrips.gear();	// flash green 3 times then becomes solod
    	
    	// run solid when opto sensor detects no gear
    	// run gear when opto sensor detects gear
    	// running of diabled is already done in disabledInit()
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
