package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
import org.usfirst.frc.team1241.robot.auto.drive.EncoderWaitCommand;
import org.usfirst.frc.team1241.robot.auto.drive.ExecuteAfterDistance;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.IntakePistonCommand;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.auto.intake.WaitUntilDetected;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchRightScale extends CommandGroup {

    public LeftSwitchRightScale(int position) {
    	if (position == 0) {
    		//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 60,1.5,
					new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			addSequential(new DriveCommand(220, 1, 0, 4, 208, 145, 0.8));
			/*//Outake the cube 
			addSequential(new SetIntakeSpeedCommand(false, 0.60, 0.5));
			//Bring the elevator to intaking position 
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back and turn to switch
			addSequential(new leftNested());
			//Intake while drving towards the switch to intake a cube
			addParallel(new WaitUntilDetected(1.5));
			addSequential(new intakingNested());
			//Bring elevator to switch position and druive forward to score
			addSequential(new DriveCommand(-14, 1, 145, 0.7));

			
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 0.75));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 0.75, new DriveCommand(31, 1, 145, 0.75)));
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));*/
			
		} else if (position == 1) {

		} else if (position == 2) {
			
		}
    }
}
