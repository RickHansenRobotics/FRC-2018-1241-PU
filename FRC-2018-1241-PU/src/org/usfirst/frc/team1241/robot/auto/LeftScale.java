package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.ContinousMotion;
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
public class LeftScale extends CommandGroup {

	public LeftScale(int position) {
		if (position == 0) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			/*
			 * addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
			 * 34,1.5, new ElevatorSetpoint(NumberConstants.scaleMidPosition,
			 * NumberConstants.maxElevatorSpeed,1, 2.5), new
			 * ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, 60, 1.5, new
			 * IntakePistonCommand(false))));
			 */

			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34, 1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2.5)));
			addSequential(new DriveCommand(263, 1, 0, 4, 195, 25, 0.7));

			/*addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(0, 87), new Point(0, 141),
					new Point(0, 174), new Point(-16, 242), new Point(34, 268), 20, 0.05, 3.5, 1));*/
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
			addParallel(new IntakePistonCommand(true));

		} else if (position == 1) {

		} else if (position == 2) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			/*
			 * addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
			 * 380,1.5, new ElevatorSetpoint(NumberConstants.scaleHighPosition,
			 * NumberConstants.maxElevatorSpeed,1, 2.5)));
			 */
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			
			addSequential (new DriveCommand (220, 1, 0, 2.75));
			addSequential(new TurnCommand(-90, 1, 1));
			addSequential (new DriveCommand (220, 1, -90, 3));
			
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(10, 0.75, 1.3));
			addSequential(new DriveCommand(48, 1, 10, 2));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));
			
			//addSequential(new DriveCommand(460, 0.5, 0, 10, 150, -90, 0.5));
			
			/*addSequential(new ContinousMotion(175, 1, 0, 2));
			addSequential(new TurnCommand(-90, 1, 1));*/
			
			//addSequential(new DriveCommand(200, 0.75, -90, 2));
		/*	addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(38, 124), new Point(-41, 122),
					new Point(67, 232), new Point(-97, 191), new Point(-255, 200), 10, 0.1, 6, 1));*/
			//addParallel(
				//	new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			//addSequential(new TurnCommand(0, 1, 2));
			//addSequential(new DriveCommand(35, 1, 0, 1));
			//addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));

		}
	}
}
