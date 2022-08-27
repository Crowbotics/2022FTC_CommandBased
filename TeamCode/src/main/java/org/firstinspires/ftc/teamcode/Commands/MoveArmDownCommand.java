import com.arcrobotics.ftclib.command.CommandBase;

public class MoveArmDownCommand extends CommandBase {

    private final ArmSubsystem m_arm;

    public MoveArmDownCommand(ArmSubsystem subsystem){
        m_arm = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_arm.moveArmDown();
    }

}
