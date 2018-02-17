package org.usfirst.frc.team1241.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTest extends CommandGroup {

	public DriveTest() {
		addSequential(new DriveCommand(10, 0.6, 0, 15));
	}
}
