import com.arcrobotics.ftclib.command.CommandBase;

public class MoveArmUpCommand extends CommandBase {

    private final ArmSubsystem m_arm;

    public MoveArmUpCommand(ArmSubsystem subsystem){
        m_arm = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_arm.moveArmUp();
    }

}
