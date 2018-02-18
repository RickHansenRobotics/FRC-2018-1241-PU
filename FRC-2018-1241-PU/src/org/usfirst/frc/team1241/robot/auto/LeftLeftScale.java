package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.ContinousMotion;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
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
public class LeftLeftScale extends CommandGroup {

	public LeftLeftScale() {
		addParallel(new SetIntakeSpeedCommand(true, 0.75, 1.25));
    	addParallel(new QuinticBezierDrivePath (new Point(0,0), new Point (8,87), new Point (6,129), new Point (10,159), new Point (8, 183), new Point(19,229), 50,.02, 3, 1 ));
		//addParallel(new DriveCommand(180, 1, 0, 2));
		//addParallel(new ContinousMotion(0.8, 0, 180, 2));
		addSequential(new ExecuteAfterDistance(40,1.5,new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
		//addParallel(new DriveCommand(40, 0.5, 22, 2));
		addSequential(new IntakePistonCommand(false));
		addSequential(new WaitCommand(1));
		addSequential(new SetIntakeSpeedCommand(false, 0.75, 3));
	}
}
