package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The robot does not move during autonomous 
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class NoAuto extends Command {

    public NoAuto() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
