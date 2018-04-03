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
public class RightSwitchRightScale extends CommandGroup {

	public RightSwitchRightScale(int position) {
		if (position == 0) {
			//Intake the cube while bringing the intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 1));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to driving position after 100 inches 
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
					 100,1.5, new ElevatorSetpoint(NumberConstants.portalPosition,NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to the right scale
			addSequential(new DriveCommand(220, 1, 0, 2.5, 215, 90, 0.8));
			addSequential (new DriveCommand (218, 1, 90, 2.7, 212, -25, 0.8));
			//Bring the elevator to scale high position while drving to the scale
			addParallel(new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed, 0.4, 2));
			addSequential(new DriveCommand(45, 1, -25, 2));
			//Outake the cube onto the scale
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 0.5));
			//Bring the elevator to intaking position
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Drive back from the scale and turn to switch
			addSequential(new leftNestedTwo());
			//Intake while drving towards the switch to pick up cube
			addParallel(new SetIntakeSpeedCommand(true, 1, 3));
			addSequential(new DriveCommand(34, 1, -135, 2, 20, -135, 0.6));
			//Bring the elevator to switch position and drive towards switch
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 2));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 1.1, new DriveCommand(23, 1, -155, 1)));
			//Outake the cube into the switch
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));
			
		} else if (position == 1) {
			
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
    		 addParallel(new IntakePistonCommand(true));
     
     		addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
     		30,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 3)));
      
          	 addSequential(new DriveCommand(121, 0.8, 27.5,2));
           	addSequential(new SetIntakeSpeedCommand(false, 0.7,1));
             
           	addSequential (new DriveCommand (-100, 1, 27.5, 2));
           
           	addParallel (new TurnCommand(0, 1, 1));
           	addSequential(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed, 1, 3));
           	addParallel(new SetIntakeSpeedCommand(true, 1, 2, false, true));
           	addSequential(new DriveCommand(53, 1, 0, 2, 20, 0, 0.6));
           
           	addSequential (new DriveCommand(-50, 0.7, 0, 1.5));
          	 addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE,
     		100 ,1.5, new ElevatorSetpoint(NumberConstants.switchPosition,NumberConstants.maxElevatorSpeed,1, 3)));
           	//To get to side of switch
           	addSequential(new DriveCommand(170, 0.8, 50, 4, 150, 0, 0.8));
           	//Below add another EAD LIKE ABOVE TO GET TO SCALE
           	//addSequential(new DriveCommand(170, 0.8, -50, 2, 150, 0, 0.8));

		} else if (position == 2) {
			//Intake Cube while brigning intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 70,3.25,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,0.5, 2.5)));
			//Drive to scale while turning to 25 degrees after 195 inches
			
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 265,3.25,
					new SetIntakeSpeedCommand(false, 1, 0.5, true)));
			
			addSequential(new DriveCommand(270, 0.865, 0, 4.5, 195, 25, 0.65, 3));
			//Outake the cube 
			//addSequential(new SetIntakeSpeedCommand(false, 0.7, 0.5));
			//Bring the elevator to intaking position 
			//Drive back and turn to switch
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.maxElevatorSpeed,0.35, 2));
			addSequential(new rightNestedTwo());
			/*addSequential(new TurnCommand(160, 0.6, 2.5, 3));
			
			addParallel(new DriveCommand(65, 1, 160, 3));
			addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));*/
			
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 0.5, 2));
			addSequential(new DriveCommand(-10, 1, -164, 4, 4));
			addSequential(new DriveCommand(10, 1, -164, 4, 4));
			addSequential(new SetIntakeSpeedCommand(false, 0.7, 3));
			
			/*//Intake the cube while bringing the intake down
			addParallel(new SetIntakeSpeedCommand(true, 0.75, 2));
			addParallel(new IntakePistonCommand(true));
			//Bring elevator to scale high position aftr 34 inches
			addParallel(new ExecuteAfterDistance(EncoderWaitCommand.DRIVE, 34,1.5,
					new ElevatorSetpoint(NumberConstants.scaleHighPosition, NumberConstants.maxElevatorSpeed,1, 2.5)));
			//Drive to scale, turning to -25 degrees after 195 inches
			addSequential(new DriveCommand(270, 1, 0, 4, 195, -25, 0.8));
			//Outake the cube onto the scale
			addSequential(new SetIntakeSpeedCommand(false, 0.5, 0.5));
			//Bring the elevator to intaking position
			addParallel(new ElevatorSetpoint(NumberConstants.intakingPosition, NumberConstants.slowElevatorSpeed, 1, 2));
			//Dribe back from scale and turn to switch
			addSequential(new rightNested());
			//Intake while driving forward towards the switch
			addParallel(new SetIntakeSpeedCommand(true, 1, 2.5));
			addSequential(new DriveCommand(47, 1, -135, 3, 20, -135, 0.7));
			//Bring the elevator to switch position and drive forward towards switch
			addSequential(new DriveCommand(-14, 1, -135, 0.7));
			addParallel(new ElevatorSetpoint(NumberConstants.switchPosition+5, NumberConstants.maxElevatorSpeed, 0.35, 0.65));
			addSequential(new ExecuteAfterDistance(EncoderWaitCommand.ELEVATOR, NumberConstants.switchPosition-15, 0.65, new DriveCommand(31, 1, -135, 0.65)));
			//Outake cube into switch
			addSequential(new SetIntakeSpeedCommand(false, 0.50, 3));*/
		}
	}
	
	private static class leftNested extends CommandGroup {
    	public leftNested(){
			addSequential(new DriveCommand(-44, 1, -26, 1.5));
			addSequential(new TurnCommand(-134, 1, 1));
		};
    }
	
	private static class rightNested extends CommandGroup {
    	public rightNested(){
			addSequential(new DriveCommand(-42, 1, -26, 1.5));
			addSequential(new TurnCommand(-135, 1, 1));
		};
    }
	   private static class rightNestedTwo extends CommandGroup {
			public rightNestedTwo() {
				addSequential(new TurnCommand(-164, 0.7, 2.5, 4));	
				addParallel(new DriveCommand(67, 1, -164, 3));
				addSequential(new SetIntakeSpeedCommand(true, 1, 2.5, true, true));
			}
		}
	    
	
	private static class leftNestedTwo extends CommandGroup {
    	public leftNestedTwo(){
			addSequential(new DriveCommand(-40, 1, -25, 1.5));
			addSequential(new TurnCommand(-135, 1, 1.5));
		};
    }
	
	
}
