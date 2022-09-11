package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

import java.util.Timer;

public class IntakeCommand extends CommandBase {

    public enum Mode {
        IN, OUT, STOP;
    }

    private final IntakeSubsystem m_intake;
    private final Mode mode;

    public IntakeCommand(IntakeSubsystem subsystem, Mode mode){
        m_intake = subsystem;
        this.mode = mode;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        if(this.mode == Mode.IN) {
            m_intake.activateIntake();
        } else if(this.mode == Mode.OUT) {
            m_intake.reverseIntake();
        } else if(this.mode == Mode.STOP){
            m_intake.stopIntake();
        }

    }

}
