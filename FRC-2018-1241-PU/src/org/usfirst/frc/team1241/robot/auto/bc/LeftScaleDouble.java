package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.drive.TurnCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleDouble extends CommandGroup {

    public LeftScaleDouble(int position) {
    	if (position == 0) {
    		//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			addSequential(new DriveCommand(270, 1, 0, 4, 195, 25, 0.8));
			//Outake the cube 
			addSequential(new SetIntakeSpeedCommand(false, 0.60, 0.5));
			//Bring the elevator to intaking position 
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back and turn to switch
			addSequential(new leftNested());
			//Intake while drving towards the switch to intake a cube
			addParallel(new SetIntakeSpeedCommand(true, 1, 2.75));
			addSequential(new DriveCommand(41, 1, 137, 3, 20, 137, 0.7));
			//Drive back from switch
			addSequential(new DriveCommand(-41, 1, 137, 2.5));
			//Drive to towards scale
			addParallel(new leftNestedTwo());
			//Bring elevator to scale high position
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.35, 2));
			//Outake Cube onto scale
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		} else if (position == 1) {

		} else if (position == 2) {
			
		}
    }
    
    private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new DriveCommand(-42, 1, 26, 1.5));
			addSequential(new TurnCommand(137, 1, 1));
		}
	}
    private static class leftNestedTwo extends CommandGroup {
		public leftNestedTwo() {
			addSequential(new TurnCommand(25, 1, 1));
			addSequential(new DriveCommand(42, 1, 25, 1.5));
		}
	}
	
}
