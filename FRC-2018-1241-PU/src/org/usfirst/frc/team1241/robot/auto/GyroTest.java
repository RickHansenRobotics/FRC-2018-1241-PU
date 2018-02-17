package org.usfirst.frc.team1241.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GyroTest extends CommandGroup {

	public GyroTest() {
		addSequential(new TurnCommand(90, 0.6, 15, 0.5));
	}
}
