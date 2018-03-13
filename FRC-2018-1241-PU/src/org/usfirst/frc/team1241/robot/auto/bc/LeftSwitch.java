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
        	addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 2));
        	//Drive in parallel to the switch 
        	addSequential(new DriveCommand(140, 1, 0,2, 90, 55, 1));
        	//Outake the cube and keep intake down
            addSequential(new SetIntakeSpeedCommand(false, 0.65, 1));
    		addParallel(new IntakePistonCommand(true));       	
        	
        } else if (position == 1){
        	//Intake while bringing intake down and bringing elevator to switch position
        	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
    		addParallel(new IntakePistonCommand(true));
        	addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 3));
        	//Drive in parallel to the switch 
        	addSequential(new DriveCommand(121, 1, -27.5,2));
        	//Outake the cube and keep intake down
    		addParallel(new IntakePistonCommand(true));
            addSequential(new SetIntakeSpeedCommand(false, 0.7,1));
            
        } else if (position == 2){
        	///////////////NEED TO TEST ON PRACTICE FIELD (BC)
        	//Bring intake down and intake
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			//Bring the elevator to driving position after 100 inches
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to the left switch from right side
			addSequential (new DriveCommand (220, 1, 0, 2.75));
			addSequential(new TurnCommand(-90, 1, 1));
			addSequential (new DriveCommand (190, 1, -90, 3));
			//Bring elevator to switch position while turning to -115 degrees 
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(-115, 0.75, 1.3));
			//Drve to switch and outtake
			addSequential(new DriveCommand(48, 1, -115, 2));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));
		}
    }
}

