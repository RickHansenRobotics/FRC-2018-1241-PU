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
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 70,3.25,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,0.5, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 265,3.25,
					new SetIntakeSpeedCommand(false, 1, 0.5, true)));
			
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
			
			addParallel(new ElevatorSetpoint(NumberConstants.scaleLowPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			
			addSequential(new DriveCommand(-67, 1, 164, 4, 4));
			addParallel(new SetIntakeSpeedCommand(true, 0.4, 0.5, true));
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.slowElevatorSpeed, 0.5, 2));
			//addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addSequential(new TurnCommand(25, 0.65, 1.3, 4));
			addSequential(new SetIntakeSpeedCommand(false, 0.4, 3));

			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			addSequential(new DriveCommand(-30, 1, 25, 4, 4));

			/*//Intake while drving towards the switch to intake a cube
			addParallel(new SetIntakeSpeedCommand(t111rue, 1, 2.4));
			addSequential(new DriveCommand(39, 1, 132, 2.4, 20, 132, 0.7));
			//Drive back from switch
			//addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2, true));
			addSequential(new DriveCommand(-41, 1, 132, 2));
			//Drive to towards scale
			addParallel(new SetIntakeSpeedCommand(true, 0.25, 2.5, true));
			addSequential(new leftNestedTwo());*/

			//Bring elevator to scale high position
			//addSequential(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.35, 2.5));
			//Outake Cube onto scale
			//addSequential(new SetIntakeSpeedCommand(false, 0.3, 3));
		} else if (position == 1) {

		} else if (position == 2) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to driving position after 100 inches 
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to the right scale
			addSequential(new DriveCommand(215, 1, 0, 2.5, 200, -90, 1));
			addSequential (new DriveCommand (224, 1, -90, 3.5, 209, 25, 1, 3));
			//Bring the elevator to scale high position while drving to the scale
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.4, 2));
			addSequential(new DriveCommand(45, 1, 25, 1.5));
			//Outake the cube onto the scale
			addSequential(new SetIntakeSpeedCommand(false, 0.65, 0.75));
			//////////////////////////////////NEW
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,0.35, 2));
			addSequential(new leftNested());
			/*addSequential(new TurnCommand(160, 0.6, 2.5, 3));
			
			addParallel(new DriveCommand(65, 1, 160, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));*/
			addParallel(new SetIntakeSpeedCommand(true, 0.2, 0.5, true));

			addParallel(new ElevatorSetpoint(NumberConstants.scaleMidPosition+3, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new leftScore());
			/*addSequential(new TurnCommand(10, 0.65, 1.3, 4));
			//addParallel(new ElevatorSetpoint(NumberConstants.scaleMidPosition+3, NumberConstants.slowElevatorSpeed, 0.5, 2));
			addSequential(new DriveCommand(62, 1, 10, 4, 4));*/
			//addParallel(new SetIntakeSpeedCommand(true, 0.5, 0.5, true));
			addSequential(new SetIntakeSpeedCommand(false, 0.45, 0.75,true));
			//addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			//addSequential(new DriveCommand(-50, 1, 10,2 ));
			//////////////////////////////////////NEW
			/*//Bring the elevator to intaking position
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back from the scale and turn to switch
			addSequential(new leftNested());
			//Intake while drving towards the switch to pick up cube
			addParallel(new SetIntakeSpeedCommand(true, 1, 3));
			addSequential(new DriveCommand(39, 1, 138, 2, 20, 138, 0.6));
			//Drive back from switch
			//addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addParallel(new SetIntakeSpeedCommand(true, 1, 1, true));
			addSequential(new DriveCommand(-41, 1, 138, 1.2));
			//Drive to towards scale
			addParallel(new SetIntakeSpeedCommand(true, 0.25, 1, true));
			addSequential(new rightScore());

			//Bring elevator to scale high position
			//addSequential(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.35, 2.5));
			//Outake Cube onto scale
			addSequential(new SetIntakeSpeedCommand(false, 0.30, 3));*/
		}
    }
    
    private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new TurnCommand(170, 0.7, 1.5, 4));	
			addParallel(new DriveCommand(67, 1, 170, 3, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
		}
	}
    private static class leftNestedTwo extends CommandGroup {
		public leftNestedTwo() {
			addSequential(new TurnCommand(164, 0.7, 2.5, 4));	
			addParallel(new DriveCommand(67, 1, 164, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
		}
	}
    
    private static class leftScore extends CommandGroup {
 		public leftScore() {
 			addSequential(new TurnCommand(10, 0.9, 0.8, 4));
			//addParallel(new ElevatorSetpoint(NumberConstants.scaleMidPosition+3, NumberConstants.slowElevatorSpeed, 0.5, 2));
			addSequential(new DriveCommand(62, 1, 10, 4, 4));
 		}
 	}
	
}
