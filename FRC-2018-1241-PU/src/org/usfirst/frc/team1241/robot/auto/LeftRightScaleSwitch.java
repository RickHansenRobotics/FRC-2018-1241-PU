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
public class LeftRightScaleSwitch extends CommandGroup {

    public LeftRightScaleSwitch() {
    	
    	addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
		addParallel(new IntakePistonCommand(true));
    	/*addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 380,1.5,
				new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));*/
		addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (-38,134), new Point (41,132), new Point (-67,242), new Point (97, 201), new Point(292,210), 20, 0.05, 6, 1 ));
		addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 1, 2));
		addSequential (new TurnCommand (-15, 1, 2));
		addSequential (new DriveCommand (28, 1, -15, 1));
		addSequential(new SetIntakeSpeedCommand(false, 0.5, 1));
		addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
		addSequential(new nested());
		
		addParallel(new SetIntakeSpeedCommand(true, 0.865, 3));
		addSequential(new DriveCommand(20, 1, -134, 1.5));
		//addSequential(new ContinousMotion(17, 0.5, -134, 1.5, true));

		addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
		addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, -145, 1)));
		addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
		
		/*addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
		addSequential (new TurnCommand (180, 1 , 2 ));*/
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
    
    private static class nested extends CommandGroup {
    	public nested(){
			addSequential(new DriveCommand(-44, 1, -26, 1.5));
			addSequential(new TurnCommand(-134, 1, 1));
		};
    }
}
