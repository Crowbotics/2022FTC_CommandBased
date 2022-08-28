package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.ArmSubsystem;

public class HoldArmCommand extends CommandBase {

    private final ArmSubsystem m_arm;

    public HoldArmCommand(ArmSubsystem subsystem){
        m_arm = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_arm.holdArm();
    }

}
