package org.usfirst.frc.team1241.robot.commands;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorCommand extends Command {
	
	public static int scaleLevel;
	public static boolean holdPosition; 

    public ElevatorCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	scaleLevel = 0;
    	holdPosition = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (!Robot.elevator.bottomHardstop()){
    		if (holdPosition){
    			Robot.elevator.elevatorHoldPosition(0.5);
    		}
    		if (Robot.oi.getToolLeftX() > 0){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleLowPosition);
    			holdPosition = true;
    		}
    		if (Robot.oi.getToolLeftY() > 0 ){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleMidPosition);
    			holdPosition = true;
    		}
    		if (Robot.oi.getToolLeftY() < 0){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleHighPosition);
    			holdPosition = true;
    		}
    		
    		if (Robot.oi.getToolRightX() > 0){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.switchLowPosition);
    			holdPosition = true;

    		}
    		if (Robot.oi.getToolRightY() > 0 ){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.switchMidPosition);
    			holdPosition = true;

    		}
    		if (Robot.oi.getToolRightY() < 0){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.switchHighPosition);
    			holdPosition = true;
    		}
    		
    		if (Robot.oi.getToolAButton()){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.intakingPosition);
    			holdPosition = true;
    		}
    		
    		if (Robot.oi.getToolBButton()){
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.portalPosition);
    			holdPosition = true;
    		}
    		/*if(Robot.oi.getToolLeftX() > 1){
    			scaleLevel += 1;
    			
    			switch (scaleLevel){
    			case 1:
    			holdPosition = false;
        		Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleLowPosition);
        		holdPosition = true;
        		break;
    			case 2:
        		holdPosition = false;
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleMidPosition);
        		holdPosition = true;
    			break;
    			case 3: 
    			holdPosition = true;
    			Robot.elevator.runElevatorMotionMagic(NumberConstants.scaleHighPosition);
        		holdPosition = true;
    				}
    			if (scaleLevel == 3){
    				scaleLevel = 0;
    			}
    			}*/
    		
    	}
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
