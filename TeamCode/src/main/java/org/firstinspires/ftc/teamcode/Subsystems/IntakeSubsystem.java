import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    //private final Servo intake;
    private Telemetry telemetry;

    public IntakeSubsystem(ServoImplEx servo) {
        //intake = servo;
    }

    public IntakeSubsystem(HardwareMap hMap, final String intakeServoName) {

    }


}
