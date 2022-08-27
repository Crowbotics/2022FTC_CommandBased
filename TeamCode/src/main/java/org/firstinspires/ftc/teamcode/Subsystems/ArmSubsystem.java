import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmSubsystem extends SubsystemBase {

    private Telemetry telemetry;

    private final Motor arm;
    private int targetPosition;

    public ArmSubsystem(HardwareMap hMap, final String armMotorName, Telemetry t) {

        arm = new MotorEx(hMap, armMotorName);
        arm.setRunMode(Motor.RunMode.PositionControl);
        targetPosition = getEncoderValue();
        telemetry = t;

        arm.setPositionTolerance(10);
        arm.setPositionCoefficient(0.025);
    }

    public int getEncoderValue() {
        return arm.encoder.getPosition();
    }

    public int getTargetPosition()
    {
        return targetPosition;
    }

    public void resetEncoders() {
        arm.encoder.reset();
    }

    private void moveArmToTarget()
    {
        telemetry.addData("At Position?", arm.atTargetPosition());
        arm.set(0.5);
    }

    public void moveArmUp() {
        targetPosition++;
        arm.setTargetPosition(targetPosition);
        moveArmToTarget();
    }

    public void moveArmDown() {
        targetPosition--;
        arm.setTargetPosition(targetPosition);
        moveArmToTarget();
    }

    //DOESN'T WORK YET
    public void holdArm() {
        moveArmToTarget();
    }

    @Override
    public void periodic() {
        telemetry.addData("Arm Target: ", getTargetPosition());
        telemetry.addData("Arm Actual: ", getEncoderValue());
        telemetry.addData("Arm Actual: ", arm.getCurrentPosition());
    }
}
