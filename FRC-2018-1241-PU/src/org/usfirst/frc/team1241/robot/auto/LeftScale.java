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
					new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed, 1, 2.5)));
			addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(0, 87), new Point(0, 141),
					new Point(0, 174), new Point(-16, 242), new Point(34, 268), 20, 0.05, 3.5, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		} else if (position == 1) {

		} else if (position == 2) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			/*
			 * addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
			 * 380,1.5, new ElevatorSetpoint(NumberConstants.scaleHighPosition,
			 * NumberConstants.maxElevatorSpeed,1, 2.5)));
			 */
			addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(38, 134), new Point(-41, 132),
					new Point(67, 242), new Point(-97, 201), new Point(-255, 210), 20, 0.05, 6, 1));
			addParallel(
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(0, 1, 2));
			addSequential(new DriveCommand(35, 1, 0, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));

		}
	}
}
