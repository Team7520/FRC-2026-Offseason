package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;

public class ModuleIO {
    // This class will read and write data to the swerve modules
    TalonFX driveTalon;
    TalonFX turnTalon;
    CANcoder cancoder;

    private final VelocityVoltage velocityVoltageRequest = new VelocityVoltage(0.0);
    private final PositionVoltage positionVoltageRequest = new PositionVoltage(0.0);

    public ModuleIO(SwerveModuleConstants<TalonFXConfiguration, TalonFXConfiguration, CANcoderConfiguration>
          constants
          ) {
        driveTalon = new TalonFX(constants.DriveMotorId);
        turnTalon = new TalonFX(constants.SteerMotorId);
        cancoder = new CANcoder(constants.EncoderId);
    }

    public void setTurnPotition(Rotation2d rotation) {
        turnTalon.setControl(positionVoltageRequest.withPosition(rotation.getRotations()));
    }

    public void setDriveVelocity(double velocityRadPerSec) {
        double velocityRotPerSec = Units.radiansToRotations(velocityRadPerSec);
        driveTalon.setControl(velocityVoltageRequest.withVelocity(velocityRotPerSec));
  }
}
