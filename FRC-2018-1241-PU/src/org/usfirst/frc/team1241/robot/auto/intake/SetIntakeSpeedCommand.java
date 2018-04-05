package org.usfirst.frc.team1241.robot.auto.intake;

import org.usfirst.frc.team1241.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntakeSpeedCommand extends Command {

	private double timeout;
	private double speed;
	private boolean intake;
	private boolean continueIntaking;
	private boolean useOptical;
	Timer timer;
	boolean started = false;
	
	public SetIntakeSpeedCommand(boolean intake, double speed, double timeout) {
    	this(intake, speed, timeout, false, false);
    	requires(Robot.intake);
    }
	
	public SetIntakeSpeedCommand(boolean intake, double speed, boolean useOptical) {
    	this(intake, speed, 1, false, useOptical);
    	requires(Robot.intake);
    }
    public SetIntakeSpeedCommand(boolean intake, double speed, double timeout, boolean continueIntaking) {
    	this(intake, speed, 1, continueIntaking, false);
    	requires(Robot.intake);
    }
	
	
    public SetIntakeSpeedCommand(boolean intake, double speed, double timeout, boolean continueIntaking, boolean useOptical) {
    	this.timeout = timeout;
    	this.speed = speed;
    	this.intake = intake;
    	this.continueIntaking = continueIntaking;
    	this.useOptical = useOptical;
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeout);
    	timer = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(intake)
    		Robot.intake.intake(speed);
    	else
    		Robot.intake.outtake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(!Robot.intake.getOptic() && started == false) {
    		timer.start();
    		started = true;
    	}
		
		if(!Robot.intake.getOptic() && timer.get()>0.5) {
    		timer.reset();
    		timer.stop();
    		Robot.intake.setContains(true);
    		Robot.intake.stop();
    	}
		if(Robot.intake.getOptic()) {
    		started = false;
    		timer.reset();
    		timer.stop();
    		Robot.intake.setContains(false);
    	}
    	
    	if(useOptical){
    		return Robot.intake.getContains();
    	} else{
            return isTimedOut();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(!continueIntaking)
    		Robot.intake.stop();
    	if(!Robot.intake.getOptic())
    		Robot.logger.logd("INTAKE", "Got Cube");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if(!continueIntaking)
    		Robot.intake.stop();
    }
}
