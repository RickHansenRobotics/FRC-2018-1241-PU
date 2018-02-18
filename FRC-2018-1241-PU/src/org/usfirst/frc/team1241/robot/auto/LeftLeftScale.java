package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftLeftScale extends CommandGroup {

	public LeftLeftScale() {
		addSequential(new DriveCommand(180, 1, 0, 2));
	//	addSequential(new ContinousMotion(0.8, 0, 180, 2));
		addParallel(new DriveCommand(71, 0.7, 22, 2));
		addParallel(new IntakePistonCommand(false));
		addSequential(new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed,0.4, 3));
		addSequential(new SetIntakeSpeedCommand(false, 0.45, 3));
	}
}
