package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.auto.drive.DrivePath;
import org.usfirst.frc.team1241.robot.auto.elevator.ElevatorSetpoint;
import org.usfirst.frc.team1241.robot.auto.intake.SetIntakeSpeedCommand;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftLeftSwitch extends CommandGroup {

    public LeftLeftSwitch() {
    	addParallel(new ElevatorSetpoint(NumberConstants.switchPosition, NumberConstants.maxElevatorSpeed, 1, 2));
    	addSequential(new DrivePath(new Point(0,0), new Point(-10,40), new Point(0,75), new Point(36,110), 1.5, 1));
        
        
        addSequential(new SetIntakeSpeedCommand(false, 0.65, 1));
    }
}
