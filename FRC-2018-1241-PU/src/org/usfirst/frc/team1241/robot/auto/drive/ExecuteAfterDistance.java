package org.usfirst.frc.team1241.robot.auto.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ExecuteAfterDistance extends CommandGroup {

    public ExecuteAfterDistance(int subsystem, double setpoint, double timeout, Command command) {
        addSequential(new EncoderWaitCommand(setpoint, timeout, subsystem));
        addSequential(command);
    }
    
    public ExecuteAfterDistance(int subsystem, double setpoint, double timeout, Command command1, Command command2) {
        addSequential(new EncoderWaitCommand(setpoint, timeout, subsystem));
        addParallel(command1);
        addSequential(command2);
    }
}
