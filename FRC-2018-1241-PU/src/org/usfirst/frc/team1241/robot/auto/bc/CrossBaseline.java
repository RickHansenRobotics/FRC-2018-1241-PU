package org.usfirst.frc.team1241.robot.auto.bc;

import org.usfirst.frc.team1241.robot.auto.drive.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaseline extends CommandGroup {

    public CrossBaseline() {
    	//Cross the Baseline 150 inches
    	addSequential(new DriveCommand(150, 1.0, 0, 3));
    }
}
