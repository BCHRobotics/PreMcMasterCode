package frc.robot.subsystems;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


/* Code written by Noah and modified by Sean
 * February 15, 2019                        */

public class Climber extends Subsystem{

    double ARMencoderCal = 1.172;
    double LEGencoderCal = 0.088;

    double encoderARM1last = 0;
    double encoderARM2last = 0;

    double encoderLEGlast = 0;

    CANSparkMax ARMLEFT = new CANSparkMax(RobotMap.SPARKARMLEFT, MotorType.kBrushless);
    CANSparkMax ARMRIGHT = new CANSparkMax(RobotMap.SPARKARMRIGHT, MotorType.kBrushless);

    CANSparkMax SPARKLEG = new CANSparkMax(RobotMap.SPARKLEG, MotorType.kBrushless);
    TalonSRX TALONLEGWHEEL = new TalonSRX(RobotMap.TALONLEGWHEEL);

    
    public CANEncoder encoderLEG = new CANEncoder(SPARKLEG);
    public CANEncoder encoderARMLEFT = new CANEncoder(ARMLEFT);
    public CANEncoder encoderARMRIGHT = new CANEncoder(ARMRIGHT);

    //Variables for correcting errors with the climber arms for the HAB -Sean
    double errorAllowed = 3;
    double slowSpeed = 0.05;

    public Climber(){

        ARMLEFT.setInverted(true);
        ARMRIGHT.setInverted(false);

        SPARKLEG.setInverted(true);
        TALONLEGWHEEL.setInverted(false);

    }

    public void armsMove(double speed){

        speed = -speed;

        //Set arm speeds after calculations and apply them
        if(getEncoderAvg() <= 25){
            ARMLEFT.set(Math.abs(speed));
            ARMRIGHT.set(Math.abs(speed));
        } else if(getEncoderAvg() >= 208) {
            ARMLEFT.set(-Math.abs(speed));
            ARMRIGHT.set(-Math.abs(speed));
        } else {
            ARMLEFT.set(speed);
            ARMRIGHT.set(speed);
        }

    }

    public void legExtend(double speed){

        speed = -speed;

        if(getEncoderLEG() <= 0){
            SPARKLEG.set(Math.abs(speed));
        } else if(getEncoderLEG() >= 24){
            SPARKLEG.set(-Math.abs(speed));
        } else{
            SPARKLEG.set(speed);
        }

    }

    public void legWheel(double speed){

        TALONLEGWHEEL.set(ControlMode.PercentOutput, speed);

    }

    public void autoClimb(){

        if(getEncoderAvg() < 100){
            armsMove(-1);
        } else if(getEncoderAvg() < 136){
            armsMove(-0.25);
        } else if(getEncoderAvg() < 180){
            armsMove(-0.18);
            legExtend(-1);
        } else if(getEncoderAvg() < 208){
            armsMove(-0.2);
            legExtend(-0.8);
        } 

    }

    public double getEncoderARM1(){

        double encoderVal = -((encoderARMLEFT.getPosition() * ARMencoderCal) - encoderARM1last);

        return encoderVal;
    }

    public double getEncoderARM2(){

        double encoderVal = ((encoderARMRIGHT.getPosition() * ARMencoderCal) - encoderARM2last);

        return encoderVal;
    }

    public double getEncoderAvg(){

        double result = (getEncoderARM1() + getEncoderARM2()) / 2;

        return result;
    }

    public double getEncoderLEG(){ 

        double encoderVal = -((encoderLEG.getPosition() * LEGencoderCal) - encoderLEGlast);

        return encoderVal;
    }

    public void resetEncoderLEG(){

        encoderLEGlast = encoderLEG.getPosition() * LEGencoderCal;

    }

    public void resetEncoders(){

        encoderARM1last = encoderARMLEFT.getPosition() * ARMencoderCal;
        encoderARM2last = encoderARMRIGHT.getPosition() * ARMencoderCal;
            
    }
    
    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}

}