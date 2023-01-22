package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.math.controller.PIDController;

class WheelDrive {
    private TalonFX angleMotor;
    private TalonFX speedMotor;
    private PIDController pid;
    private final double MAX_VOLTS = 5;

    public WheelDrive(int angleMotor, int speedMotor, int encoder) {
        this.angleMotor = new TalonFX(angleMotor);
        this.speedMotor = new TalonFX(speedMotor);
        PIDController pid = new PIDController(1, 0, 0);
        pid.enableContinuousInput(-180, 180);
    }

    public void drive(double speed, double angle) {
        speedMotor.set(ControlMode.PercentOutput, speed);

        double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5); 
        if (setpoint < 0) {
            setpoint = MAX_VOLTS + setpoint;
        }
        if (setpoint > MAX_VOLTS) {
            setpoint = setpoint - MAX_VOLTS;
        }

        pid.setSetpoint(setpoint);
    }
}
