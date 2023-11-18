package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TankDriveTrain {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public TankDriveTrain(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "rightd");
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double straight, double turn) {
        leftMotor.setPower(straight + turn);
        rightMotor.setPower(straight - turn);
    }

    // Placeholder for getRawExternalHeading (without gyro)
    public double getRawExternalHeading() {
        // Implement the logic to get the raw external heading without a gyro
        // For example, return a constant value or another sensor reading
        return 0.0;
    }

    // Placeholder for getExternalHeadingVelocity (without gyro)
    public double getExternalHeadingVelocity() {
        // Implement the logic to get the heading velocity without a gyro
        // For example, return a constant value or another sensor reading
        return 0.0;
    }
}
