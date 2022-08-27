import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveSubsystem extends SubsystemBase {

    private Telemetry telemetry;

    private final DifferentialDrive m_drive;
    private final MotorEx m_leftMotor, m_rightMotor;
    private final Motor.Encoder m_leftEncoder, m_rightEncoder;
    private final double WHEEL_DIAMETER;

    public DriveSubsystem(HardwareMap hMap,
                          final String leftMotorName,
                          final String rightMotorName,
                          final double diameter,
                          Telemetry t) {

        m_leftMotor = new MotorEx(hMap, leftMotorName);
        m_rightMotor = new MotorEx(hMap, leftMotorName);
        m_leftEncoder = m_leftMotor.encoder;
        m_rightEncoder = m_rightMotor.encoder;
        WHEEL_DIAMETER = diameter;
        telemetry = t;

        m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    }

    public void drive(double fwd, double turn) {
        m_drive.arcadeDrive(fwd, turn);
    }

    public double getLeftEncoderVal() {
        return m_leftEncoder.getPosition();
    }

    public double getRightEncoderVal() {
        return m_rightEncoder.getPosition();
    }

    public double getLeftEncoderDistance(){
        return m_leftEncoder.getRevolutions() * WHEEL_DIAMETER * Math.PI;
    }

    public double getRightEncoderDistance(){
        return m_rightEncoder.getRevolutions() * WHEEL_DIAMETER * Math.PI;
    }

    public void resetEncoders(){
        m_leftEncoder.reset();
        m_rightEncoder.reset();
    }

    public double getAverageEncoderDistance(){
        return(getLeftEncoderDistance() + getRightEncoderDistance())/2.0;
    }

    public void periodic()
    {
    }

}
