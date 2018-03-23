/*package org.usfirst.frc.team1241.robot.auto;

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

*//**
 *
 *//*
public class LeftSwitchLeftScale extends CommandGroup {

	public LeftSwitchLeftScale(int position) {
		if (position == 0) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));

			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34, 1.5,
					new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed, 1, 2.5)));

			addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(-15, 84), new Point(-27, 130),
					new Point(-41, 182), new Point(-23, 230), new Point(24, 284), 20, 0.05, 3.5, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.60, 0.5));

			addParallel(
					new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new leftNested());
			addParallel(new SetIntakeSpeedCommand(true, 0.865, 3));
			addSequential(new DriveCommand(59, 1, 134, 1.75));

			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition + 5, NumberConstants.maxElevatorSpeed, 0.35,
					2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition - 5, 1.1,
					new DriveCommand(23, 1, 145, 1)));
			addSequential(new SetIntakeSpeedCommand(false, 1, 3));
		} else if (position == 1) {

		} else if (position == 2) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			
			addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (38,134), new Point (-41,132), new Point (67,242), new Point (-97, 201), new Point(-292,210), 20, 0.05, 6, 1 ));

			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential (new TurnCommand (15, 1, 2));
			addSequential (new DriveCommand (28, 1, 15, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			addSequential(new rightNested());
			
			addParallel(new SetIntakeSpeedCommand(true, 0.865, 3));
			addSequential(new DriveCommand(20, 1, 134, 1.5));

			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, 145, 1)));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		}
	}

	private static class leftNested extends CommandGroup {
		public leftNested() {
			addSequential(new DriveCommand(-32, 1, 26, 1.5));
			addSequential(new TurnCommand(134, 1, 1));
		}
	}
	
	private static class rightNested extends CommandGroup {
    	public rightNested(){
			addSequential(new DriveCommand(-44, 1, 26, 1.5));
			addSequential(new TurnCommand(134, 1, 1));
		};
    }
}
*/