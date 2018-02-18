package org.usfirst.frc.team1241.robot.auto.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StartingConfigSequence extends CommandGroup {

    public StartingConfigSequence() {
        addSequential(new SetIntakeSpeedCommand(true, 0.5, 1));
    }
}
