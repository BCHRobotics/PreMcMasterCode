package frc.robot;

public class RobotMap {

    //RobotMap holds all the global variables for where things are plugged in


    //MOTORS

    //DriveTrain
    public static int SPARKLEFTFRONT = 11;
    public static int SPARKLEFTBACK = 12;

    public static int SPARKRIGHTFRONT = 15;
    public static int SPARKRIGHTBACK = 16;

    //Lift
    public static int SPARKLIFT = 51; //Extra Spark Flashed and set to 51
    public static int TALONLIFT = 31;

    //Climber
    public static int SPARKARMLEFT = 41; 
    public static int SPARKARMRIGHT = 42; 
    public static int SPARKLEG = 43; 
    public static int TALONLEGWHEEL = 44; 
    //Grabber 
    public static int TALONGRABBERTILT = 21;
    public static int TALONGRABBEREXTEND = 22;
    public static int TALONINTAKELEFT = 62;
    public static int TALONINTAKERIGHT = 61;

    /********************************* */

    //CONTROLLERS
    public static int OI_DRIVESTICK = 0;
    public static int OI_FUNSTICK = 1;
    public static int OI_TESTSTICK = 2;

    //DriveStick AXIS
    public static int OI_DRIVESTICK_MOVEX = 1;
    public static int OI_DRIVESTICK_MOVEY = 0;
    public static int OI_DRIVESTICK_ROTATE = 4; //2 for ps4
    public static int OI_DRIVESTICK_TURBO = 3; //4 for ps4
    public static int OI_DRIVESTICK_SNAIL = 2; //3 for ps4


    //FunStick AXIS
    public static int OI_FUNSTICK_LIFT = 1;
    public static int OI_FUNSTICK_Scissor = 5;
    public static int OI_FUNSTICK_IntakeIn = 3;
    public static int OI_FUNSTICK_IntakeOut = 2;
    public static int OI_FUNSTICK_PegLeg = 5;
    public static int OI_FUNSTICK_Arms = 1;
    public static int OI_FUNSTICK_PegLegForward = 3;
    public static int OI_FUNSTICK_PegLegBackward = 2; 

    //BUTTONS
    public static int OI_FUNSTICK_GrabOpen = 5; 
    public static int OI_FUNSTICK_GrabClose = 6; 

    public static int OI_FUNSTICK_Push = 1;


    public static int OI_DRIVESTICK_GrabOpen = 5; 
    public static int OI_DRIVESTICK_GrabClose = 6; 

    public static int OI_FUNSTICK_LayoutSwitch = 8;
    public static int OI_FUNSTICK_SeqClimbOut = 1;
    public static int OI_FUNSTICK_SeqClimbIn = 2;

    public static int OI_CANCEL_AUTO = 3;
    public static int OI_FUNSTICK_Toggle = 6;

    public static int OI_GOTO_PSBALL = 9;
    public static int OI_GOTO_PSHATCH = 10;

    public static int OI_GOTO_RHATCH3 = 4;
    public static int OI_GOTO_RHATCH2 = 3;

    public static int OI_GOTO_AUTOHATCH = 2;
    public static int OI_GOTO_AUTOBALL = 1;
    /********************************* */

    //PHENUMATICS
    public static int GRABBER_SOLENOID_OPEN = 0;
    public static int GRABBER_SOLENOID_CLOSE = 1;

    public static int PUSHER_SOLENOID_OPEN = 3;
    public static int PUSHER_SOLENOID_CLOSE = 2;

     /********************************* */

    //PID VALUES

    public static double SETPOINT = 1;
    //public static double PIDspeed = 0.003;


    //ADXRS450 Gyro PID
    public static double GYRO_P = 1;
    public static double GYRO_I = 1;
    public static double GYRO_D = 1;

    //NavX Gyro PID
    public static double NAV_GYRO_P = 0.1; // 0.031
    public static double NAV_GYRO_I = 0.00281; // 0.00283
    public static double NAV_GYRO_D = 0.0001; // 0
    
    //Encoder PID
    public static double ENCODER_P = 0.8;
    public static double ENCODER_I = 0;
    public static double ENCODER_D = 0;

    //Vision PID
    public static double VISION_P = 1;
    public static double VISION_I = 1;
    public static double VISION_D = 1;

    //Lift PID
    public static double LIFT_P = 1;
    public static double LIFT_I = 1;
    public static double LIFT_D = 1;

}