package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 10/01/2018
 */
public class Intake extends Subsystem {

    TalonSRX leftWheel;
    TalonSRX rightWheel;
    
    public Intake(){
    	leftWheel = new TalonSRX(0);
    	rightWheel = new TalonSRX(1);
    }
    
    public void intake(){
    	leftWheel.set(ControlMode.PercentOutput, 1);
    	rightWheel.set(ControlMode.PercentOutput, -1);
    	
    }
    
    public void outtake(){
    	leftWheel.set(ControlMode.PercentOutput, -1);
    	rightWheel.set(ControlMode.PercentOutput, 1);
    }
    
    public void stop(){
    	leftWheel.set(ControlMode.PercentOutput, 0);
    	rightWheel.set(ControlMode.PercentOutput, 0);
    }
    
    public double getLeftVoltage(){
    	return leftWheel.getMotorOutputVoltage();
    }
    
    public double getRightVoltage(){
    	return rightWheel.getMotorOutputVoltage();
    }

    public void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }
}

