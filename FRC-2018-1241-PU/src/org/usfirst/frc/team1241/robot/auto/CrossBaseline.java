package org.usfirst.frc.team1241.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossBaseline extends CommandGroup {

    public CrossBaseline() {
    	addSequential(new DriveCommand(10, 1.0, 0, 15));
    }
}
