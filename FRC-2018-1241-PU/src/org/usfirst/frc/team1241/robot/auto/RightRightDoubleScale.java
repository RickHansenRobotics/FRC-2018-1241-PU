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
public class RightRightDoubleScale extends CommandGroup {

    public RightRightDoubleScale() {
    	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
		addParallel(new IntakePistonCommand(true));
		
		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
				new ElevatorSetpoint(NumberConstants.scaleMidPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
		addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (0,87), new Point (0,141), new Point (0,174), new Point (-16, 242), new Point(34,268), 20, 0.05, 3.5, 1 ));
		addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		
		addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
		addSequential(new nested());
		addParallel(new SetIntakeSpeedCommand(true, 0.865, 3));
		addSequential(new DriveCommand(59, 1, 134, 1.75));
		
		addSequential(new DriveCommand (-59, 1, 134, 1.25));
		
		addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
		addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-5, 1.1, new DriveCommand(23, 1, 145, 1)));
		addSequential(new SetIntakeSpeedCommand(false, 1, 3));
    }
    
    private static class nested extends CommandGroup {
    	public nested(){
			addSequential(new DriveCommand(-32, 1, 26, 1.5));
			addSequential(new TurnCommand(134, 1, 1));
		};
    }
}
