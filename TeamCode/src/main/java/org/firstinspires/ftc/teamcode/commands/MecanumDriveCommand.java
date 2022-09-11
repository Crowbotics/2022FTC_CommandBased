package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class MecanumDriveCommand extends CommandBase {

    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_strafe;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_turn;
    private final DoubleSupplier m_angle;

    public MecanumDriveCommand(DriveSubsystem subsystem,
                               DoubleSupplier strafe,
                               DoubleSupplier forward,
                               DoubleSupplier turn,
                               DoubleSupplier angle){

        m_strafe = strafe;
        m_drive = subsystem;
        m_forward = forward;
        m_turn = turn;
        m_angle = angle;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_drive.drive(m_strafe.getAsDouble(), m_forward.getAsDouble(), m_turn.getAsDouble(), m_angle.getAsDouble());
    }

}
