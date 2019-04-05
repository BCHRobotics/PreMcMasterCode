package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

    //Init Driver and Functional Mech Joysticks
    public Joystick driveStick = new Joystick(RobotMap.OI_DRIVESTICK);
    public Joystick funStick = new Joystick(RobotMap.OI_FUNSTICK);
    public Joystick testStick = new Joystick(RobotMap.OI_TESTSTICK);

    //Set the buttons
    public Button ButtonGrabOpen = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_GrabOpen);
    public Button ButtonGrabClose = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_GrabClose);

    public Button ButtonPush = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_Push);

    public Button ButtonDriveGrabOpen = new JoystickButton(driveStick, RobotMap.OI_DRIVESTICK_GrabOpen);
    public Button ButtonDriveGrabClose = new JoystickButton(driveStick, RobotMap.OI_DRIVESTICK_GrabClose);

    public Button ButtonLayoutSwitch = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_LayoutSwitch);
    public Button ButtonSeqClimbOut = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_SeqClimbOut);
    public Button ButtonSeqClimbIn = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_SeqClimbIn);

    public Button ButtonCancelAutoD = new JoystickButton(driveStick, RobotMap.OI_CANCEL_AUTO);

    public Button ButtonGOTOBall = new JoystickButton(funStick, RobotMap.OI_GOTO_PSBALL);
    public Button ButtonGOTOHatch = new JoystickButton(funStick, RobotMap.OI_GOTO_PSHATCH);

    public Button ButtonGOTOrHatch3 = new JoystickButton(funStick, RobotMap.OI_GOTO_RHATCH3);
    public Button ButtonGOTOrHatch2 = new JoystickButton(funStick, RobotMap.OI_GOTO_RHATCH2);
    
    public Button ButtonGOTOautoHatch = new JoystickButton(funStick, RobotMap.OI_GOTO_AUTOHATCH);
    public Button ButtonGOTOautoBall = new JoystickButton(funStick, RobotMap.OI_GOTO_AUTOBALL);

    public Button ButtonToggle = new JoystickButton(funStick, RobotMap.OI_FUNSTICK_Toggle);
    
    
}