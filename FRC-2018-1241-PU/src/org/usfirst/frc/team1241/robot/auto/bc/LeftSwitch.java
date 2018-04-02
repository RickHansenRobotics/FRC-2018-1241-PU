package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.DrivePath;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.drive.TurnCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitch extends CommandGroup {

    public LeftSwitch(int position) {
        if(position == 0){
        	//Intake while bringing intake down and bringing elevator to switch position
        	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
    		addParallel(new IntakePistonCommand(true));
    		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 30,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 3)));
   		
    		
        	//addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 2));
        	//Drive in parallel to the switch 
        	addSequential(new DriveCommand(140, 1, 0,2, 90, 55, 1));
        	//Outake the cube and keep intake down
            addSequential(new SetIntakeSpeedCommand(false, 0.65, 1));
    		addParallel(new IntakePistonCommand(true));       	
        	
        } else if (position == 1){
        	//Intake while bringing intake down and bringing elevator to switch position
        	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
    		addParallel(new IntakePistonCommand(true));
    		
    		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 30,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 3)));
    		
        	//addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 3));
        	//Drive in parallel to the switch 
        	addSequential(new DriveCommand(121, 0.8, -27.5,2));
        	//Outake the cube and keep intake down
    		addParallel(new IntakePistonCommand(true));
            addSequential(new SetIntakeSpeedCommand(false, 0.7,1));
            
        } else if (position == 2){
        	///////////////NEED TO TEST ON PRACTICE FIELD (BC)
        	addParallel(new IntakePistonCommand(false));
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			//addSequential (new DriveCommand (70, 0.8, 20, 2.75, 60, 90, 0.8));
			addSequential (new DriveCommand (60, 1, 0, 2.75));
			addSequential(new TurnCommand(-90, 0.75, 2, 3));
			addSequential (new DriveCommand (175, 1, -90, 2.75));
			addSequential(new TurnCommand(0, 0.75, 2, 3));
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential (new DriveCommand (50, 1, 0, 2.75));
			addSequential(new SetIntakeSpeedCommand(false, 0.75, 1));
		}
    }
}

