package org.usfirst.frc.team1241.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ElevatorTest extends CommandGroup {

	public ElevatorTest() {
		addSequential(new ElevatorPIDSetpoint(45, 0.6, 15, 0.5));
	}
}
