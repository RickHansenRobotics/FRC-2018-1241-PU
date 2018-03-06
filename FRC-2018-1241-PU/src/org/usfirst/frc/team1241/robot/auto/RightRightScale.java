package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.drive.QuinticBezierDrivePath;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightRightScale extends CommandGroup {

    public RightRightScale() {

		addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
		addParallel(new IntakePistonCommand(true));

		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
				new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
		
		addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (15,84), new Point (27,130), new Point (41,182), new Point (23, 230), new Point(-14,274), 20, 0.05, 3.5, 1 ));
		addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
    }
}
