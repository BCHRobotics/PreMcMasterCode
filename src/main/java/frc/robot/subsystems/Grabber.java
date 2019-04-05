/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Grabber extends Subsystem {
	
	//Init grabber variable
	DoubleSolenoid grabberSol = null;
	//DoubleSolenoid pusherSol = null;

	TalonSRX TALONGRABBERTILT = new TalonSRX(RobotMap.TALONGRABBERTILT);
	//TalonSRX TALONGRABBEREXTEND = new TalonSRX(RobotMap.TALONGRABBEREXTEND);
	
	TalonSRX TALONINTAKELEFT = new TalonSRX(RobotMap.TALONINTAKELEFT);
	TalonSRX TALONINTAKERIGHT = new TalonSRX(RobotMap.TALONINTAKERIGHT);

	Compressor m_compressor = new Compressor(0);

	public Grabber() {

		grabberSol = new DoubleSolenoid(RobotMap.GRABBER_SOLENOID_OPEN,
			RobotMap.GRABBER_SOLENOID_CLOSE);

		/*
		pusherSol = new DoubleSolenoid(RobotMap.PUSHER_SOLENOID_OPEN,
			RobotMap.PUSHER_SOLENOID_CLOSE);
		*/

		TALONINTAKELEFT.setInverted(false);
		TALONINTAKERIGHT.setInverted(true);

		TALONINTAKELEFT.configClosedloopRamp(0);
		TALONINTAKERIGHT.configClosedloopRamp(0);

		TALONINTAKERIGHT.follow(TALONINTAKELEFT);

	}

	public void compressorOn(){
		m_compressor.setClosedLoopControl(true);
	}

	public void compressorOff(){
		m_compressor.setClosedLoopControl(false);
	}

	public void grabberOpen() {
        grabberSol.set(Value.kForward);
        
	}

	public void grabberClose() {
        grabberSol.set(Value.kReverse);
        
	}

	/*
	public void pusherOpen() {
        pusherSol.set(Value.kForward);
        
	}

	public void pusherClose() {
        pusherSol.set(Value.kReverse);
        
	}

	*/
	public void tilt(double moveSpeed) {

		TALONGRABBERTILT.set(ControlMode.PercentOutput, moveSpeed);

	}

	public void intake (double moveSpeed) {

		TALONINTAKELEFT.set(ControlMode.PercentOutput, moveSpeed);
		

	}

	/*

	public void extend(double moveSpeed){

		TALONGRABBEREXTEND.set(ControlMode.PercentOutput, moveSpeed);

	}*/

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
