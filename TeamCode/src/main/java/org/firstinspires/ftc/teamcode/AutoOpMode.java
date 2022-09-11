package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ArcadeDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCommand;
import org.firstinspires.ftc.teamcode.commands.HoldArmCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.MoveArmDownCommand;
import org.firstinspires.ftc.teamcode.commands.MoveArmUpCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

@Autonomous(name="Drive Forward")
public class AutoOpMode extends CommandOpMode {

    //Declare Subsystems
    DriveSubsystem m_drive;
    ArmSubsystem m_arm;
    IntakeSubsystem m_intake;

    //Declare Buttons
    GamepadEx m_driverGamepad, m_auxGamepad;
    Button m_armUpButton, m_armDownButton;

    //Commands
    ArcadeDriveCommand m_arcadeDriveCommand;
    MoveArmDownCommand m_armDownCommand;
    MoveArmUpCommand m_armUpCommand;
    HoldArmCommand m_holdArmCommand;
    //IntakeCommand
    //ReleaseCommand

    Crowbot m_crowbot;

    @Override
    public void initialize() {

        m_crowbot = new Crowbot(Crowbot.OpModeType.AUTO);

        m_drive = new DriveSubsystem(hardwareMap,
                "frontLeft",
                "rearLeft",
                "frontRight",
                "rearRight",
                3.5,
                telemetry);

        m_arm = new ArmSubsystem(hardwareMap,"arm", telemetry);

        m_armUpCommand = new MoveArmUpCommand(m_arm);
        m_armDownCommand = new MoveArmDownCommand(m_arm);
        m_holdArmCommand = new HoldArmCommand(m_arm);

        m_intake = new IntakeSubsystem(hardwareMap, "intake", telemetry);

        register(m_drive);
        register(m_arm);

        schedule(new RunCommand(() -> telemetry.update()));

    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        schedule(new DriveDistanceCommand(m_drive, 15, 0.3)
                .andThen(m_armDownCommand)
                .andThen(new IntakeCommand(m_intake, IntakeCommand.Mode.IN))
                .andThen(m_armUpCommand)
                .andThen(new DriveDistanceCommand(m_drive, 15, 0.3)));


    while (!isStopRequested() && opModeIsActive()) {
            m_crowbot.run();
        }
        m_crowbot.reset();
    }


}
