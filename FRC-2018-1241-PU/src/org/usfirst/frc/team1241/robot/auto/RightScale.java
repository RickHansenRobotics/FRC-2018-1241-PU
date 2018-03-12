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
public class RightScale extends CommandGroup {

	public RightScale(int position) {
		if (position == 0) {
			/*addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			 * addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
			 * 380,1.5, new ElevatorSetpoint(NumberConstants.scaleHighPosition,
			 * NumberConstants.maxElevatorSpeed,1, 2.5)));
			 
			addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(-38, 134), new Point(41, 132),
					new Point(-67, 242), new Point(97, 201), new Point(272, 210), 20, 0.05, 6, 1));
			addParallel(
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(0, 1, 2));
			addSequential(new DriveCommand(28, 1, 0, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));*/
			
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			
			addSequential (new DriveCommand (220, 1, 0, 2.75));
			addSequential(new TurnCommand(90, 1, 1));
			addSequential (new DriveCommand (220, 1, 90, 3));
			
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(-10, 0.75, 1.3));
			addSequential(new DriveCommand(48, 1, -10, 2));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));

		} else if (position == 1) {

		} else if (position == 2) {
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));

			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34, 1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2.5)));
			addSequential(new DriveCommand(263, 1, 0, 4, 195, -25, 0.7));
			//addSequential(new QuinticBezierDrivePath(new Point(0, 0), new Point(15, 84), new Point(27, 130),
				//	new Point(41, 182), new Point(23, 230), new Point(-14, 274), 20, 0.05, 3.5, 1));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));

		}
	}
}
