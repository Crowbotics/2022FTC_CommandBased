package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    private Telemetry telemetry;
    private ServoEx intake;


    public IntakeSubsystem(ServoEx servo) {
        this.intake = servo;
    }

    public IntakeSubsystem(HardwareMap hMap, final String intakeServoName, Telemetry t) {
        this(new SimpleServo(hMap, intakeServoName, 0, 360));
        telemetry = t;
    }

    public void activateIntake() {
       intake.setPosition(1);
    }

    public void reverseIntake() {
        intake.setPosition(0);
    }

    public void stopIntake() {
        intake.setPosition(0.5);
    }

    @Override
    public void periodic() {
        telemetry.addData("Intake Angle: ", intake.getAngle());
        telemetry.addData("Intake Position: ", intake.getPosition());
    }
}
