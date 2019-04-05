package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;

public class Teleop extends Subsystem{

    
    public double y = 0;           //variable for forward/backward movement
    public double x = 0;           //variable for side to side movement
    public double turn = 0;        //variable for turning movement
    public double deadzone = 0.07;	//variable for amount of deadzone

    public boolean funModeClimb = false;

    public double idleLift = -0.19;

    public double liftDeadzone = 0.25;
    public double liftAdjustSpeed = 0.25;

    public double lastLiftEncoder = 0;

    public Teleop(){

    }

    public void funStick(){

        if(funModeClimb == true){

            if(Robot.m_oi.ButtonLayoutSwitch.get() == true){
              funModeClimb = false;
              Timer.delay(0.25);
            }
      
            //CLIMB FUNC MECH
      
            if(Robot.m_oi.ButtonSeqClimbOut.get() == true){
              //Do Seq Climb Out
            }
      
            if(Robot.m_oi.ButtonSeqClimbIn.get() == true){
              //Do Seq Climb In
            }
      
            if(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_PegLeg) > deadzone || Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_PegLeg) < -deadzone) {
              Robot.m_climber.legExtend(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_PegLeg));
            } else {
              Robot.m_climber.legExtend(0);
            }
      
            if(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_Arms) > deadzone || Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_Arms) < -deadzone) {
              Robot.m_climber.armsMove(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_Arms));
            } else {
              Robot.m_climber.armsMove(0);
            }
      
      
            double pegLegSpeed = Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_PegLegBackward) + -Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_PegLegForward);
      
            Robot.m_climber.legWheel(pegLegSpeed);

            Robot.m_lift.MoveLift(0);

            if(Robot.m_oi.ButtonSeqClimbIn.get() == true){
              Robot.m_climber.autoClimb();
            }
      
          } else if(funModeClimb == false) {
      
            if(Robot.m_oi.ButtonLayoutSwitch.get() == true){
              funModeClimb = true;
              Timer.delay(0.25);
            }
      
            //REG FUNC MECH
      
            double intakeSpeed = - Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_IntakeOut) + Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_IntakeIn);

            Robot.m_grabber.intake(intakeSpeed);

            if(Robot.m_oi.ButtonGrabOpen.get() == true){
              Robot.m_grabber.grabberOpen();
            } else if(Robot.m_oi.ButtonGrabClose.get() == true){
              Robot.m_grabber.grabberClose();
            } 

           /* if(Robot.m_oi.ButtonPush.get() == true){
              Robot.m_grabber.pusherOpen();
            } else if(Robot.m_oi.ButtonPush.get() == false){
              Robot.m_grabber.pusherClose();
            } */
      
            if(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT) > deadzone || Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT) < -deadzone) {
              Robot.m_lift.MoveLift(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_LIFT));
              lastLiftEncoder = Robot.m_lift.getEncoder();
            } else if(Robot.m_lift.getEncoder() < 1){
              
              Robot.m_lift.MoveLift(0);

            } else {

              double height = Robot.m_lift.getEncoder();

              if(height >= lastLiftEncoder - liftDeadzone && lastLiftEncoder + liftDeadzone >= height){

              } else if(height > lastLiftEncoder){
                Robot.m_lift.MoveLift(-0.07);
              } else if(height < lastLiftEncoder){
                Robot.m_lift.MoveLift(-0.25);
              } else {
                Robot.m_lift.MoveLift(-0.08);
              }
            }
      
            Robot.m_grabber.tilt(Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_Scissor)*0.8);
      
            //double extendSpeed = -Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_TiltDown) + Robot.m_oi.funStick.getRawAxis(RobotMap.OI_FUNSTICK_TiltUp);
            //Robot.m_grabber.extend(extendSpeed*-0.85);

            if(Robot.m_oi.ButtonGOTOBall.get() == true){
              Robot.m_lift.gotoBall();
              lastLiftEncoder = Robot.m_lift.getEncoder();
            } else if(Robot.m_oi.ButtonGOTOHatch.get() == true){
              Robot.m_lift.gotoHatch();
              lastLiftEncoder = Robot.m_lift.getEncoder();
            } else if(Robot.m_oi.ButtonGOTOrHatch3.get() == true){
              Robot.m_lift.gotorHatch3();
              lastLiftEncoder = Robot.m_lift.getEncoder();
            } else if(Robot.m_oi.ButtonGOTOrHatch2.get() == true){
              Robot.m_lift.gotorHatch2();
              lastLiftEncoder = Robot.m_lift.getEncoder();
            }
      
          }

    }

    public void driveStick(){

        
    double turboSpeed = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_TURBO)*0.3 + 0.7;
    double snailSpeed = -Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_SNAIL)*0.3 + 0.7;

    double moveSpeed = turboSpeed + snailSpeed;
    


    if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY) < -deadzone) {
        y = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEY);
    } else {
        y = 0;
    }

    if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX) < -deadzone) {
        x = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_MOVEX);
    } else {
        x = 0;
    }

    if(Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE) > deadzone || Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE) < -deadzone){
        turn = Robot.m_oi.driveStick.getRawAxis(RobotMap.OI_DRIVESTICK_ROTATE);
    } else {
        turn = 0;
    }
    
     // m_drivetrain.mecanumDrive(y, x, turn, ahrs.getAngle(), moveSpeed);

    Robot.m_drivetrain.mecanumDrive(y, x, turn, 0, moveSpeed);
   
    if(Robot.m_oi.ButtonDriveGrabOpen.get() == true){
        Robot.m_grabber.grabberOpen();
    } else if(Robot.m_oi.ButtonDriveGrabClose.get() == true){
        Robot.m_grabber.grabberClose();
    } 

    }

    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new DriveArcade());
	}
}