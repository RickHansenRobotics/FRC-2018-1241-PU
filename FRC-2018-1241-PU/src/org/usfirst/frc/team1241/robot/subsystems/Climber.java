package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.NumberConstants;
import org.usfirst.frc.team1241.robot.commands.ClimberCommand;
import org.usfirst.frc.team1241.robot.pid.PIDController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	WPI_TalonSRX climberArm;
	DoubleSolenoid piston;
	
	AnalogInput armPot;
	
	public PIDController armPID;
	
	public boolean ptoHanger = false;

	public Climber(){		
		climberArm = new WPI_TalonSRX(ElectricalConstants.CLIMBER_ARM_MOTOR);
		piston = new DoubleSolenoid(ElectricalConstants.HANGER_PISTON_A, ElectricalConstants.HANGER_PISTON_B);
		
		armPot = new AnalogInput(ElectricalConstants.ARM_POTENTIOMETER);		
		armPID = new PIDController(NumberConstants.pARM, NumberConstants.iARM, NumberConstants.dARM);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ClimberCommand());
	}
	
	public void setPtoHanger(boolean posistion){
		ptoHanger = posistion;
	}
	
	public boolean ptoPosistionHanger(){
		return ptoHanger;
	}

	// method raises or lowers the climber arm
	public void runClimberArm(double pwmVal) {
		climberArm.set(pwmVal);
	}
	
	/** Sets wedge position using setpoints */
	public void setArmAngle(double angle, double speed) {
		// Set up sensor methods
		double output = armPID.calcPID(angle, getArmPot(), 5);
		runClimberArm(-output * speed);
	}

	public double getArmPot() {
		return armPot.getVoltage()/5 * 1080;
	}

	public boolean onTarget(double target) {
		if (target - 10 <= getArmPot() && target + 10 >= getArmPot()) {
			return true;
		} else {
			return false;
		}
	}

	// Extends or retracts pistons for detaching the arm
	public void extendPiston() {
		piston.set(DoubleSolenoid.Value.kForward);
	}
		
	public void retractPiston() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void stopPiston() {
		piston.set(DoubleSolenoid.Value.kOff);
	}
}
