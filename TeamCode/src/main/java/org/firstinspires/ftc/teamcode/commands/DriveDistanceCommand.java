package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.BooleanSupplier;

public class DriveDistanceCommand extends CommandBase {

    private final DriveSubsystem m_drive;
    private final double m_distance;
    private final double m_speed;

    public DriveDistanceCommand(DriveSubsystem subsystem, double distance, double speed)
    {
        m_drive = subsystem;
        m_distance = distance;
        m_speed = speed;

        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        m_drive.resetEncoders();
        m_drive.drive(m_speed, 0);
    }

    @Override
    public void end(boolean interrupted) {
        m_drive.drive(0,0);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_drive.getAverageEncoderDistance() - m_distance) < 3;
    }
}
