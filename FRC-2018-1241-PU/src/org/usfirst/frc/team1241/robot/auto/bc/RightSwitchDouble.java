package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.TurnCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchDouble extends CommandGroup {

    public RightSwitchDouble(int position) {
    	if (position == 0) {
			
			
		} else if (position == 1) {
			//Intake while bringing intake down and bringing elevator to switch position
        	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
    		addParallel(new IntakePistonCommand(true));
        	addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 3));
        	//Drive in parallel to the switch 
        	addSequential(new DriveCommand(121, 1, 27.5,2));
        	//Outake the cube and keep intake down
    		addParallel(new IntakePistonCommand(true));
            addSequential(new SetIntakeSpeedCommand(false, 0.7,1));
            addSequential (new DriveCommand (-40, 1, 27.5, 1));
            addParallel (new TurnCommand(-60, 1, 1));
            addSequential(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 1, 3));
            addParallel (new DriveCommand(30, 0.7, -60, 1.5));
            addSequential(new SetIntakeSpeedCommand(true, 1, 1.5));
            addSequential (new DriveCommand(-30, 0.7, -60, 1.5));
            addParallel (new TurnCommand(27.5, 1, 0.5));
            addSequential(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 3));
            addSequential (new DriveCommand (40, 1, 27.5, 1));

		} else if (position == 2) {
			
		}
    }
}
