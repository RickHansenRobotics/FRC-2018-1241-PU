package org.usfirst.frc.team1241.robot.auto;

import org.usfirst.frc.team1241.robot.auto.drive.QuinticBezierDrivePath;
import org.usfirst.frc.team1241.robot.utilities.Point;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class QuinticBezierTest extends CommandGroup {

    public QuinticBezierTest() {
    	addSequential(new QuinticBezierDrivePath (new Point(0,0), new Point (8,87), new Point (6,129), new Point (10,159), new Point (8, 183), new Point(24,224), 50,.02, 3, 1 ));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
