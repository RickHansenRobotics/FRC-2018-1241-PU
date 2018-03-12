package org.usfirst.frc.team1241.robot.subsystems;

import org.usfirst.frc.team1241.robot.ElectricalConstants;
import org.usfirst.frc.team1241.robot.Robot;
import org.usfirst.frc.team1241.robot.commands.IntakeCommand;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 10/01/18
 */
public class Intake extends Subsystem {

	WPI_TalonSRX leftWheel;
	WPI_TalonSRX rightWheel;

	DoubleSolenoid piston;
	DigitalInput optical;
	private boolean contains = false;
	
	public int maxCurrentDraw = 28;
	public int maxCurrentDuration = 1000;
	public int continuousCurrentLimit = 15;
	
	public double intakeSpeed;
	public double lowOuttake, highOuttake, regOuttake, slowOuttake;

	public Intake() {
		leftWheel = new WPI_TalonSRX(ElectricalConstants.LEFT_INTAKE_MOTOR);
		rightWheel = new WPI_TalonSRX(ElectricalConstants.RIGHT_INTAKE_MOTOR);

		piston = new DoubleSolenoid(ElectricalConstants.LEFT_INTAKE_PISTON_A,
				ElectricalConstants.LEFT_INTAKE_PISTON_B);
		
		optical = new DigitalInput(0);
		
		//current limit enable
		leftWheel.enableCurrentLimit(true);
		rightWheel.enableCurrentLimit(true);
					///
		//max current limit in amps (non-zero timeout is to check for t seconds if current is over maxIntakeCurrent)
		leftWheel.configPeakCurrentLimit(maxCurrentDraw, 1000);
		rightWheel.configPeakCurrentLimit(maxCurrentDraw, 1000);
		
		//duration for max current draw
		leftWheel.configPeakCurrentDuration(maxCurrentDuration, 1000);
		rightWheel.configPeakCurrentDuration(maxCurrentDuration, 1000);
		
		//continuous allowable current draw
			//if current is > max Amps for > max Duration(ms), drop to continuousCurrentLimit
		leftWheel.configContinuousCurrentLimit(continuousCurrentLimit, 1000);
		rightWheel.configContinuousCurrentLimit(continuousCurrentLimit, 1000);
					///
	}

	public void intake(double power) {
		leftWheel.set(-power);
		rightWheel.set(power);

	}
	
	public void intakeCurrent(double amps) {
		leftWheel.set(ControlMode.Current, -amps);
		rightWheel.set(ControlMode.Current, amps);	
	}

	public void spinCube() {
		leftWheel.set(0.35);
		rightWheel.set(0.35);
	}

	public void outtake(double power) {

		leftWheel.set(power);
		rightWheel.set(-power);
	}

	public void stop() {
		leftWheel.set(0);
		rightWheel.set(0);
	}
	
	public void extendIntakePistons(){
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractIntakePistons(){
		piston.set(DoubleSolenoid.Value.kReverse);
	}

	public double getLeftVoltage() {
		return leftWheel.getMotorOutputVoltage();
	}

	public double getRightVoltage() {
		return rightWheel.getMotorOutputVoltage();
	}

	public double getLeftCurrent() {
		return leftWheel.getOutputCurrent(); //spike >22 then drop
	}
	
	public double getRightCurrent() {
		return rightWheel.getOutputCurrent();
	}
	
	public double getAverageCurrent() {
		double averageCurrent = (getLeftCurrent() + getRightCurrent())/2;
		return averageCurrent;
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
	
	public boolean currentCubeIn() {
		if (getAverageCurrent() > maxCurrentDraw) {
			return true;
		} else {
			return false;
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
}
