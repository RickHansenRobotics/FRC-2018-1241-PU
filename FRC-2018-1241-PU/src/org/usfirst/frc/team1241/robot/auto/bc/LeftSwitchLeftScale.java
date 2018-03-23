package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.drive.QuinticBezierDrivePath;
import org.usfirst.frc.team1241.robot.auto.drive.TurnCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.auto.intake.WaitUntilDetected;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeftScale extends CommandGroup {
	
	public LeftSwitchLeftScale(int position) {
		if (position == 0) {
			//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 60,1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			addSequential(new DriveCommand(270, 1, 0, 4, 195, 25, 0.8));
			//Outake the cube 
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 0.5));
			//Bring the elevator to intaking position 
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back and turn to switch
			addSequential(new leftNested());
			//Intake while drving towards the switch to intake a cube
			//addParallel(new WaitUntilDetected(1.5));
			addSequential(new intakingNested());
			//Bring elevator to switch position and druive forward to score
			addSequential(new DriveCommand(-14, 1, 135, 0.7));

			
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 0.75));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 0.75, new DriveCommand(31, 1, 135, 0.75)));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
			
		} else if (position == 1) {

		} else if (position == 2) {
			//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to driving height after 100 inches 
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive towatrds left scale
			addSequential(new DriveCommand(220, 1, 0, 2.5, 215, -90, 0.8));
			addSequential (new DriveCommand (218, 1, -90, 2.7, 212, 25, 0.8));
			//Bring elevator to scale high position while drive turning to scale
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.4, 2));
			addSequential(new DriveCommand(45, 1, 25, 2));
			//Outake the cube onto the scale
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 0.5));
			//Bring the elevator to intaking position 
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back from scale and turn to switch
			addSequential(new rightNestedTwo());
			//Intake the cube while driving forwards
			addParallel(new SetIntakeSpeedCommand(true, 1, 3));
			addSequential(new DriveCommand(34, 1, 135, 1.5, 20, 135, 0.6));
			//Bring the elevator to switch postion and drive forwards
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, 135, 1)));
			//Outake the cube into switch
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		}
	}

	private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new DriveCommand(-42, 1, 26, 1.5));
			addSequential(new TurnCommand(135, 1, 1));
		}
	}
	
	private static class rightNestedTwo extends CommandGroup {
    	public rightNestedTwo(){
			addSequential(new DriveCommand(-40, 1, 25, 1.5));
			addSequential(new TurnCommand(135, 1, 1.5));
		};
    }
	
	private static class rightNestedOne extends CommandGroup {
    	public rightNestedOne(){
    		addSequential(new TurnCommand(10, 0.75, 1.3));
			addSequential(new DriveCommand(48, 1, 10, 2));
		};
    }
	
	private static class intakingNested extends CommandGroup {
    	public intakingNested(){
    		addParallel(new SetIntakeSpeedCommand(true, 1, 2.75));
			addSequential(new DriveCommand(39, 1, 135, 3, 20, 135, 0.7));
		};
    }
}
