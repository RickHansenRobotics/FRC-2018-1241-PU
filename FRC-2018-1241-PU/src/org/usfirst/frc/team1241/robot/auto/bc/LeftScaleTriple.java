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
public class LeftScaleTriple extends CommandGroup {

    public LeftScaleTriple(int position) {
    	if (position == 0) {
    		//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 70,3.25,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,0.5, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 265,3.25,
					new SetIntakeSpeedCommand(false, 0.65, 0.75, true)));
			
			addSequential(new DriveCommand(270, 0.865, 0, 4.5, 195, 25, 0.65, 3));
			//Outake the cube 
			//addSequential(new SetIntakeSpeedCommand(false, 0.7, 0.5));
			//Bring the elevator to intaking position 
			//Drive back and turn to switch
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,0.35, 2));
			addSequential(new leftNestedTwo());
			/*addSequential(new TurnCommand(160, 0.6, 2.5, 3));
			
			addParallel(new DriveCommand(65, 1, 160, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));*/
			
			addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new leftScore());
			
			//addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.slowElevatorSpeed, 0.5, 2));
			//addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addSequential(new SetIntakeSpeedCommand(false, 0.65, 0.75));
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,1, 2));
			addSequential(new leftNestedThree());
    		/*//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 70,3.25,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,0.5, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 265,3.25,
					new SetIntakeSpeedCommand(false, 1, 0.4, true)));
			
			addSequential(new DriveCommand(270, 0.865, 0, 4.5, 195, 25, 0.65, 3));
			//Outake the cube 
			//addSequential(new SetIntakeSpeedCommand(false, 0.7, 0.5));
			//Bring the elevator to intaking position 
			//Drive back and turn to switch
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,0.35, 2));
			addSequential(new leftNestedTwo());
			addSequential(new TurnCommand(160, 0.6, 2.5, 3));
			
			addParallel(new DriveCommand(65, 1, 160, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
			
			addParallel(new ElevatorSetpoint(NumberConstants.scaleLowPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			
			addSequential(new DriveCommand(-67, 1, 164, 4, 4));
			addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.slowElevatorSpeed, 0.5, 2));
			//addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addSequential(new TurnCommand(25, 0.65, 1.3, 4));
			addSequential(new SetIntakeSpeedCommand(false, 0.4, 1));
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,0.35, 2));
			addSequential(new leftNestedThree());
			*/
		} else if (position == 1) {

		} else if (position == 2) {
			
		}
    }
    
    private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new DriveCommand(-42, 1, 26, 1.5));
			addSequential(new TurnCommand(132, 1, 1));
		}
	}
    private static class leftNestedTwo extends CommandGroup {
  		public leftNestedTwo() {
  			addSequential(new TurnCommand(164, 0.7, 1.5, 4));	
			addParallel(new DriveCommand(61, 1, 164, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
  		}
  	}
    private static class leftNestedThree extends CommandGroup {
  		public leftNestedThree() {
  			addSequential(new TurnCommand(155, 0.7, 2.5, 4));	
  			addParallel(new DriveCommand(78, 1, 155, 3));
  			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
  		}
  	}
    
    private static class rightScore extends CommandGroup {
 		public rightScore() {
 			addParallel(new TurnCommand(35, 1, 1));
 			addSequential(new ElevatorSetpoint(NumberConstants.scaleMidPosition+3, NumberConstants.maxElevatorSpeed, 0.35, 2.5));
			addSequential(new DriveCommand(45, 1, 35, 1.5));
 		}
 	}
    private static class leftScore extends CommandGroup {
 		public leftScore() {
 			addSequential(new TurnCommand(10, 0.9, 0.8, 4));
			addSequential(new DriveCommand(50, 1, 10, 4, 4));
 		}
 	}
	
}
