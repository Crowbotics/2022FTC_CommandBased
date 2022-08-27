import com.arcrobotics.ftclib.command.CommandBase;

import java.util.function.DoubleSupplier;

public class ArcadeDriveCommand extends CommandBase {

    private final DriveSubsystem m_drive;
    private final DoubleSupplier m_forward;
    private final DoubleSupplier m_turn;

    public ArcadeDriveCommand(DriveSubsystem subsystem,
                              DoubleSupplier forward,
                              DoubleSupplier turn){

        m_drive = subsystem;
        m_forward = forward;
        m_turn = turn;
        addRequirements(subsystem);
    }

    @Override
    public void execute(){
        m_drive.drive(m_forward.getAsDouble(), m_turn.getAsDouble());
    }

}
