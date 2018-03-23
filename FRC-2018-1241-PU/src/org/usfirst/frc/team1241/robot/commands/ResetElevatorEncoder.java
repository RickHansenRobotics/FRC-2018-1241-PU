package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetElevatorEncoder extends Command {

    public ResetElevatorEncoder() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called once when the command executes
    protected void initialize() {
    	
    }
    @Override
    protected void execute(){
    	Robot.elevator.resetEncoders();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
