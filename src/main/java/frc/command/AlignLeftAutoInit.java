package frc.command;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.subsystems.SwerveSubsystem;
import frc.util.TargetingSystem;
import frc.util.TargetingSystem.ReefBranchSide;
import edu.wpi.first.wpilibj.Timer;

public class AlignLeftAutoInit extends Command{
    Timer timer = new Timer();
    public AlignLeftAutoInit(SwerveSubsystem drivebase, TargetingSystem targetingSystem){
            Commands.defer(() -> targetingSystem.autoTargetCommand(drivebase::getPose)
            .andThen(targetingSystem.setBranchSide(ReefBranchSide.LEFT))
            .andThen(Commands.runOnce(()->{drivebase.getSwerveDrive().field.getObject("target").setPose(targetingSystem.getCoralTargetPose());}))
            .andThen(drivebase.driveToPose(targetingSystem.getCoralTargetPose())),
            Set.of(drivebase));
    }

    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public boolean isFinished(){
        return timer.get()>0.25;
    }
}
