package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.drive.QuinticBezierDrivePath;
import org.usfirst.frc.team1241.robot.auto.drive.TurnCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchLeftScale extends CommandGroup {

	public LeftSwitchLeftScale(int position) {
		if (position == 0) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));

			/*addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(-15, 84), new Point(-27, 130),
					new Point(-41, 182), new Point(-23, 230), new Point(24, 284), 20, 0.05, 3.5, 1));
			*/
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
			
			addSequential(new DriveCommand(270, 1, 0, 4, 195, 25, 0.8));

			addSequential(new SetIntakeSpeedCommand(false, 0.60, 0.5));
			
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new leftNested());
			addParallel(new SetIntakeSpeedCommand(true, 1, 2.75));
			addSequential(new DriveCommand(41, 1, 137, 3, 20, 137, 0.7));
			//addSequential(new ContinousMotion(17, 0.5, -134, 1.5, true));

			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, 145, 1.3)));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
			
		} else if (position == 1) {

		} else if (position == 2) {
			/*	addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (38,134), new Point (-41,132), new Point (67,242), new Point (-97, 201), new Point(-292,210), 20, 0.05, 6, 1 ));

			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential (new TurnCommand (15, 1, 2));
			addSequential (new DriveCommand (28, 1, 15, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));*/
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			
			/*addSequential (new DriveCommand (220, 1, 0, 2.75));
			addSequential(new TurnCommand(-90, 1, 1));*/
			addSequential(new DriveCommand(220, 1, 0, 2.5, 215, -90, 0.8));
			
			//addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 200, 3, new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2)));
			addSequential (new DriveCommand (225, 1, -90, 2.7, 220, 10, 0.8));
			
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.4, 2));
			addSequential(new DriveCommand(45, 1, 10, 2));
			//addSequential(new rightNestedOne());
			addSequential(new SetIntakeSpeedCommand(false, 0.65, 0.5));
			
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new rightNestedTwo());
			
			addParallel(new SetIntakeSpeedCommand(true, 1, 3));
			addSequential(new DriveCommand(27, 1, 155, 1.5, 20, 155, 0.6));

			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, 155, 1)));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		}
	}

	private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new DriveCommand(-42, 1, 26, 1.5));
			addSequential(new TurnCommand(137, 1, 1));
		}
	}
	
	private static class rightNestedTwo extends CommandGroup {
    	public rightNestedTwo(){
			addSequential(new DriveCommand(-40, 1, 10, 1.5));
			addSequential(new TurnCommand(155, 1, 1.5));
		};
    }
	
	private static class rightNestedOne extends CommandGroup {
    	public rightNestedOne(){
    		addSequential(new TurnCommand(10, 0.75, 1.3));
			addSequential(new DriveCommand(48, 1, 10, 2));
		};
    }
}
