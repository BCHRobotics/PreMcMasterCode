/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.cscore.MjpegServer;
//import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
//import edu.wpi.first.wpilibj.SerialPort;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private static final String krightRocketFrontHatch = "Right Rocket Front Hatch";
  private static final String krightRocketFrontHatchCowabunga = "Right Rocket Front Hatch Cowabunga";
  private static final String kmiddleCargoStraight = "Middle Cargo Straight";
  private static final String krightMiddleCargo = "Right Cargo Middle";
  private static final String ktestAuto = "Test Auto";

  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static Drivetrain m_drivetrain = null;
  public static OI m_oi = null;
  public static Grabber m_grabber = null;
  public static Lift m_lift = null;
  //public static Auto m_auto = null;
  public static Climber m_climber = null;
  public static Teleop m_teleop = null;
  //public static AutoCode m_autoCode = null;

  //Gyro
  public ADXRS450_Gyro m_gyro = null;
  //public AHRS ahrs = null;

  public double pidTime = 0;

  public double lastTimeAuto = 0;

  public boolean autoRan = false;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
/*
    UsbCamera usbCamera1 = new UsbCamera("cam1", 0);
    MjpegServer mjpegServer1 = new MjpegServer("mcam1", 1181);
    mjpegServer1.setSource(usbCamera1);
    CameraServer.getInstance().addCamera(usbCamera1);
  */

    //UsbCamera usbCamera2 = new UsbCamera("USBcam2", 1);
    //CameraServer.getInstance().addCamera(usbCamera2);
    //.setResolution(160, 120)
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
  


    SmartDashboard.putBoolean("psHatch", false);
    SmartDashboard.putBoolean("psBall", false);
    SmartDashboard.putBoolean("rHatch1", false);
    SmartDashboard.putBoolean("rHatch2", false);
    SmartDashboard.putBoolean("rHatch3", false);
    SmartDashboard.putBoolean("rBall1", false);
    SmartDashboard.putBoolean("rBall2", false);
    SmartDashboard.putBoolean("rBall3", false);

    SmartDashboard.putNumber("ERROR", 454);
    SmartDashboard.putNumber("AUTO", 666);
    SmartDashboard.putNumber("ClimberAvg", 420);
    SmartDashboard.putNumber("EncoderLeg", 420);
    
    SmartDashboard.putNumber("EncoderFR", 6969);
    SmartDashboard.putNumber("EncoderBR", 6969);
    SmartDashboard.putNumber("EncoderFL", 6969);
    SmartDashboard.putNumber("EncoderBL", 6969);
    SmartDashboard.putNumber("EncoderAvg", 6969);

    SmartDashboard.putNumber("EncoderFRlast", 2222);

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    m_chooser.addOption("My Auto", krightRocketFrontHatch);
    m_chooser.addOption("My Auto", krightRocketFrontHatchCowabunga);
    m_chooser.addOption("My Auto", kmiddleCargoStraight);
    m_chooser.addOption("My Auto", krightMiddleCargo);
    m_chooser.addOption("My Auto", ktestAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    SmartDashboard.putNumber("NAVX", 4546);

    m_drivetrain = new Drivetrain();
    m_oi = new OI();
    m_grabber = new Grabber();
    m_lift = new Lift();   
    m_gyro = new ADXRS450_Gyro();
    //m_auto = new Auto();
    m_climber = new Climber();
    m_teleop = new Teleop();
    //m_autoCode = new AutoCode();

    //ahrs = new AHRS(SerialPort.Port.kUSB);

    //m_gyro.reset();
    //m_gyro.calibrate();

    m_drivetrain.resetEncoders();
    m_climber.resetEncoderLEG();
    m_climber.resetEncoders();
    m_lift.resetEncoder();

    /*
    m_drivetrain.encoderBLlast = 0;
    m_drivetrain.encoderBRlast = 0;
    m_drivetrain.encoderFLlast = 0;
    m_drivetrain.encoderFRlast = 0;
    */

   // m_auto.resetGyro();

    m_grabber.compressorOn();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    

    Robot.m_lift.liftIndicatiors();

    SmartDashboard.putNumber("EncoderLift", m_lift.getEncoder());
    SmartDashboard.putNumber("ClimberAvg", m_climber.getEncoderAvg());
    SmartDashboard.putNumber("EncoderLeg", m_climber.getEncoderLEG());

    SmartDashboard.putBoolean("CLIMBING?", m_teleop.funModeClimb);

    //SmartDashboard.putNumber("Nav Gyro", m_auto.getGyro());
    SmartDashboard.putNumber("EncoderFR", m_drivetrain.getEncoderFR());
    SmartDashboard.putNumber("EncoderBR", m_drivetrain.getEncoderBR());
    SmartDashboard.putNumber("EncoderFL", m_drivetrain.getEncoderFL());
    SmartDashboard.putNumber("EncoderBL", m_drivetrain.getEncoderBL());
    SmartDashboard.putNumber("EncoderAvg", m_drivetrain.getEncoderAvg());

    SmartDashboard.putNumber("EncoderFRlast", m_drivetrain.encoderFRlast);

  }

  @Override
  public void autonomousInit() {

    m_drivetrain.resetEncoders();
    lastTimeAuto = 0;
    m_autoSelected = m_chooser.getSelected();
    m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

   //m_auto.resetGyro();

   m_drivetrain.resetEncoders();
   m_grabber.compressorOn();
   m_climber.resetEncoderLEG();
   m_climber.resetEncoders();
   m_lift.resetEncoder();
  }

  @Override
  public void autonomousPeriodic() {

    m_teleop.driveStick();
    m_teleop.funStick();

    //Auto Only buttons

    if(Robot.m_oi.ButtonGOTOautoHatch.get() == true){
      Robot.m_lift.gotoAutoHatch();
      m_teleop.lastLiftEncoder = Robot.m_lift.getEncoder();
    } else if(Robot.m_oi.ButtonGOTOautoBall.get() == true){
      Robot.m_lift.gotoAutoBall();
      m_teleop.lastLiftEncoder = Robot.m_lift.getEncoder();
    }

  }

  //Reset gyro when the robot is disabled
  @Override
  public void disabledInit(){
    /*
    m_gyro.reset();
    m_auto.resetGyro();
    
    m_drivetrain.resetEncoders();
    lastTimeAuto = 0;

    m_grabber.compressorOn();
    
    m_climber.resetEncoderLEG();
    m_climber.resetEncoders();
    m_lift.resetEncoder();
    */
  }

  
  //Reset gyro when the robot is entering teleop
  @Override
  public void teleopInit() {
    super.teleopInit();
    //m_gyro.reset();
    /*
    m_auto.resetGyro();

    m_drivetrain.resetEncoders();
    m_grabber.compressorOn();
    m_climber.resetEncoderLEG();
    m_climber.resetEncoders();
    m_lift.resetEncoder();
    */

    m_climber.resetEncoders();

    m_drivetrain.setRamp(0.3);
  }

  @Override
  public void teleopPeriodic() {

    

    //Calls the teleop joystick functions
    m_teleop.driveStick();
    m_teleop.funStick();

    }

  @Override
  public void testPeriodic() {

  }
}



