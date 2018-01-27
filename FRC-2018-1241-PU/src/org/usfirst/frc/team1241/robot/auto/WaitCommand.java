package org.usfirst.frc.team1241.robot.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Bryan Kristiono
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class WaitCommand extends Command {

	private double wait;
    
	public WaitCommand(double wait) {
        this.wait = wait;
    }

    protected void initialize() {
    	setTimeout(wait);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
