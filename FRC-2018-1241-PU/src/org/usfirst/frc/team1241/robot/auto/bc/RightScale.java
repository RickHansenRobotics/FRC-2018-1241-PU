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
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

	public RightScale(int position) {
		if (position == 0) {
			//Intake the cube while bringing the intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			//Bring the elevator to driving position after 100 inches
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to right scale
			addSequential (new DriveCommand (220, 1, 0, 2.75));
			addSequential(new TurnCommand(90, 1, 1));
			addSequential (new DriveCommand (220, 1, 90, 3));
			//Bring elevator to scale high position turning to scale
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
			addSequential(new TurnCommand(-10, 0.75, 1.3));
			//Drive to scale and outake the cube
			addSequential(new DriveCommand(48, 1, -10, 2));
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));

		} else if (position == 1) {

		} else if (position == 2) {
			//Intake the cube while bringing the intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring the elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34, 1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2.5)));
			//Drive to scale, turning to - 25 degrees after 195 inches 
			addSequential(new DriveCommand(263, 1, 0, 4, 195, -25, 0.7));
			//Outake the cube onto the scale
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));

		}
	}
}
