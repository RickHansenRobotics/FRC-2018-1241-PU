package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 10/01/18
 */
public class Intake extends Subsystem {

	WPI_TalonSRX leftWheel;
	WPI_TalonSRX rightWheel;

	DigitalInput optical;
	private boolean contains = false;

	public Intake() {
		leftWheel = new WPI_TalonSRX(ElectricalConstants.LEFT_INTAKE_MOTOR);
		rightWheel = new WPI_TalonSRX(ElectricalConstants.RIGHT_INTAKE_MOTOR);

		optical = new DigitalInput(0);
	}

	public void intake() {
		leftWheel.set(0.6);
		rightWheel.set(-0.75);

	}
	
	public void spinCube(){
		leftWheel.set(0.35);
		rightWheel.set(0.35);
	}

	public void outtake() {
		
		leftWheel.set(-0.6);
		rightWheel.set(0.75);
	}

	public void stop() {
		leftWheel.set(0);
		rightWheel.set(0);
	}

	public double getLeftVoltage() {
		return leftWheel.getMotorOutputVoltage();
	}

	public double getRightVoltage() {
		return rightWheel.getMotorOutputVoltage();
	}
	
	public void setContains(boolean state) {
		this.contains = state;
	}

	public boolean getContains() {
		return contains;
	}

	public boolean getOptic() {
		return optical.get();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
}
