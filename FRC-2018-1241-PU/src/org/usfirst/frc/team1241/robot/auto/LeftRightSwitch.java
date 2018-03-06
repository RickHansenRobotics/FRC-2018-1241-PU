package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
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
public class LeftRightSwitch extends CommandGroup {

    public LeftRightSwitch() {
    	/*addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
		addParallel(new IntakePistonCommand(true));
    	addParallel(new QuinticBezierDrivePath (new Point(0,0), new Point (0,88), new Point (0,192), new Point (0,212), new Point (0, 242), new Point(115,240), 20, 0.05, 3, 1 ));
    */
    	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
		addParallel(new IntakePistonCommand(true));
    	addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
				new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
		addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (-38,134), new Point (41,132), new Point (-67,242), new Point (97, 201), new Point(222,216), 20, 0.05, 5, 1 ));
		addSequential (new TurnCommand (180, 1, 1));
		addSequential(new SetIntakeSpeedCommand(false, 0.65, 1));

    }
}
