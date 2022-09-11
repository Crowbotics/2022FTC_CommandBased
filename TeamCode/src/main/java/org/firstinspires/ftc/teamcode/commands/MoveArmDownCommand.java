package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class MoveArmDownCommand extends CommandBase {

    private final ArmSubsystem m_arm;

    public MoveArmDownCommand(ArmSubsystem subsystem){
        m_arm = subsystem;
        addRequirements(subsystem);
    }

    /**
    public MoveArmDownCommand(MoveArmDownCommand m_armDownCommand, double v, double v1) {

        m_arm =
    }
     */

    @Override
    public void execute(){
        m_arm.moveArmDown();
    }

}
