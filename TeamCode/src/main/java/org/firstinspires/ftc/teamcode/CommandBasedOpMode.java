package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Command Based Op Mode")
public class CommandBasedOpMode extends CommandOpMode {

    //Declare Subsystems
    DriveSubsystem m_drive;
    //ArmSubsystem m_arm;
    //IntakeSubsystem m_intake;
    //CollectorSubsystem

    RevIMU gyro;
    //Declare Buttons
    GamepadEx m_driverGamepad, m_auxGamepad;
    Button m_armUpButton, m_armDownButton, m_intakeButton, m_reverseIntakeButton;

    //Commands
    MecanumDriveCommand m_mecanumDriveCommand;
    //MoveArmDownCommand m_armDownCommand;
    //MoveArmUpCommand m_armUpCommand;
    //HoldArmCommand m_holdArmCommand;
    //IntakeCommand m_intakeCommand, m_reverseIntakeCommand, m_stopIntakeCommand;
    //IntakeCommand
    //ReleaseCommand

    Crowbot m_crowbot;

    @Override
    public void initialize() {

        m_crowbot = new Crowbot(Crowbot.OpModeType.TELEOP);

        gyro = new RevIMU(hardwareMap, "imu");

        gyro.init();

        m_drive = new DriveSubsystem(hardwareMap,
                "frontLeft",
                "rearLeft",
                "frontRight",
                "rearRight",
                3.5,
                telemetry);

        //m_arm = new ArmSubsystem(hardwareMap,"arm", telemetry);
        //m_intake = new IntakeSubsystem(hardwareMap, "intake", telemetry);

        m_driverGamepad = new GamepadEx(gamepad1);
        m_auxGamepad = new GamepadEx(gamepad2);
        m_armUpButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.A);
        m_armDownButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.B);

        m_intakeButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.X);
        m_reverseIntakeButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.Y);

        m_mecanumDriveCommand = new MecanumDriveCommand(m_drive,
                ()->m_driverGamepad.getLeftX(),
                ()->m_driverGamepad.getLeftY(),
                ()->m_driverGamepad.getRightX(),
                ()->gyro.getHeading());

        //m_armUpCommand = new MoveArmUpCommand(m_arm);
        //m_armDownCommand = new MoveArmDownCommand(m_arm);
       // m_holdArmCommand = new HoldArmCommand(m_arm);

        //This is a test of my new branch

        //m_intakeCommand = new IntakeCommand(m_intake, IntakeCommand.Mode.IN);
        //m_reverseIntakeCommand = new IntakeCommand(m_intake, IntakeCommand.Mode.OUT);
        //m_stopIntakeCommand = new IntakeCommand(m_intake, IntakeCommand.Mode.STOP);

        register(m_drive);
        //register(m_arm);

        //Default Commands
        m_drive.setDefaultCommand(m_mecanumDriveCommand);
        //m_arm.setDefaultCommand(m_holdArmCommand);
        //m_intake.setDefaultCommand(m_stopIntakeCommand);

        //Button Bindings
        //m_armUpButton.whenHeld(m_armUpCommand);
        //m_armDownButton.whenHeld(m_armDownCommand);
        //m_reverseIntakeButton.whenHeld(m_reverseIntakeCommand);
        //m_intakeButton.whenHeld(m_intakeCommand);

        schedule(new RunCommand(() -> telemetry.update()));

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        // run the scheduler
        while (!isStopRequested() && opModeIsActive()) {
            m_crowbot.run();
        }
        m_crowbot.reset();
    }

}
