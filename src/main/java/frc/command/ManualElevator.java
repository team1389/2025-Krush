package frc.command;

import java.util.function.Supplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.subsystems.ElevatorArmSubsystem;

public class ManualElevator extends Command{
    ElevatorArmSubsystem elevatorArm;
    Supplier<Double> power;

    public ManualElevator(ElevatorArmSubsystem elevatorArm, Supplier<Double> power){
        this.elevatorArm = elevatorArm;
        this.power = power;
        addRequirements(elevatorArm);
    }

    @Override
    public void execute(){
        // if(elevatorArm.getRightRelElevatorPos() < 0 || elevatorArm.getRightRelElevatorPos() > 50){
        //     elevatorArm.stop();
        // } else{
        elevatorArm.setManualElevator(MathUtil.clamp(power.get(), -1, 1));//}
    }

}
