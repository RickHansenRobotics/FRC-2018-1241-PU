package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;
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
public class RightSwitchLeftScale extends CommandGroup {

    public RightSwitchLeftScale(int position) {
    	if (position == 0) {
			
			
		} else if (position == 1) {
			
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
   		 addParallel(new IntakePistonCommand(true));
    
    		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
    		30,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 3)));
     
         	 addSequential(new DriveCommand(121, 1, 22 ,4, 4));
          	addSequential(new SetIntakeSpeedCommand(false, 0.7,0.75));
          	addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 1, 3));

          	addSequential (new DriveCommand (-90, 1, 35, 3, -80, 0, 1, 4));
          
          	//addSequential (new TurnCommand(0, 1, 2, 4));
          	addParallel(new SetIntakeSpeedCommand(true, 1, 3, true, true));
          	addSequential(new DriveCommand(40, 1, 0, 2, 20, 0, 1, 3));
          
          	addParallel(new SetIntakeSpeedCommand(true, 0.2, 3, true));
          	addSequential (new DriveCommand(-50, 1, 0, 1.5, 3));
         	 addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
    		100 ,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.slowElevatorSpeed,1, 3)));
         	 
         	addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
            		200,1.5, new ElevatorSetpoint(NumberConstants.scaleHighPosition,NumberConstants.maxElevatorSpeed,1, 3)));
             
         	 addSequential(new QuinticBezierDrivePath(new Point(0,0),new Point(-86, 32), new Point(-182, 97), new Point(-95,130), new Point(-105,166), new Point(-55, 280), 20, 0.05, 5, 1));
          	//addSequential(new DriveCommand(135, 1, 50, 4, 130, 0, 1, 4));
          	//Below add another EAD LIKE ABOVE TO GET TO SCALE
          
          //	addSequential(new DriveCommand(150, 1, 0, 4, 130, -25, 1, 4));
          	addSequential(new SetIntakeSpeedCommand(false, 0.65,0.75));
          	addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			addSequential(new DriveCommand(-50, 1, 10,2 ));

		} else if (position == 2) {
			
		}
    }
}
