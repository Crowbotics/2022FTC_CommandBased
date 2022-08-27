import com.arcrobotics.ftclib.command.CommandBase;

public class StopArmCommand extends CommandBase {

    private final ArmSubsystem m_arm;

    public StopArmCommand(ArmSubsystem subsystem){
        m_arm = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_arm.holdArm();
    }

}
