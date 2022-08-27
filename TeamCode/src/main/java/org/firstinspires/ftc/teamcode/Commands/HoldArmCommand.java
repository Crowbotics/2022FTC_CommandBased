import com.arcrobotics.ftclib.command.CommandBase;

//DOESN'T WORK YET
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
