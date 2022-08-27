import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Command Based Op Mode")
public class CommandBasedOpMode extends CommandOpMode {

    MotorEx m_left, m_right;
    DriveSubsystem m_drive;
    GamepadEx m_driverGamepad, m_auxGamepad;
    Button m_armUpButton, m_armDownButton;
    ArmSubsystem m_arm;
    //CollectorSubsystem


    //Commands
    ArcadeDriveCommand m_arcadeDriveCommand;
    MoveArmDownCommand m_armDownCommand;
    MoveArmUpCommand m_armUpCommand;
    StopArmCommand m_stopArmCommand;
    HoldArmCommand m_holdArmCommand;
    //IntakeCommand
    //ReleaseCommand

    Crowbot m_crowbot;



    @Override
    public void initialize() {

        m_crowbot = new Crowbot(Crowbot.OpModeType.TELEOP);

        m_drive = new DriveSubsystem(hardwareMap,
                "leftDrive",
                "rightDrive",
                3.5,
                telemetry);

        m_arm = new ArmSubsystem(hardwareMap,"arm", telemetry);

        m_driverGamepad = new GamepadEx(gamepad1);
        m_auxGamepad = new GamepadEx(gamepad2);
        m_armUpButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.A);
        m_armDownButton = new GamepadButton(m_driverGamepad, GamepadKeys.Button.B);

        m_arcadeDriveCommand = new ArcadeDriveCommand(m_drive,
                ()->m_driverGamepad.getLeftY(),
                ()->m_driverGamepad.getRightX());

        m_armUpCommand = new MoveArmUpCommand(m_arm);
        m_armDownCommand = new MoveArmDownCommand(m_arm);
        m_stopArmCommand = new StopArmCommand(m_arm);
        m_holdArmCommand = new HoldArmCommand(m_arm);



        register(m_drive);
        register(m_arm);
        m_drive.setDefaultCommand(m_arcadeDriveCommand);
        m_armUpButton.whenHeld(m_armUpCommand);
        m_armDownButton.whenHeld(m_armDownCommand);
        m_arm.setDefaultCommand(m_holdArmCommand);

        //schedule(new RunCommand(telemetry::update));
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
