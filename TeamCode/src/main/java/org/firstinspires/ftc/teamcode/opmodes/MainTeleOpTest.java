package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivetrain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class MainTeleOpTest extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    @Override
    public void runOpMode() throws InterruptedException {

        MotorEx m1 = new MotorEx(hardwareMap, "left", 537.7, 312);
        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DifferentialDrive drivetrain = new DifferentialDrive(m1);
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            run(
                    drivetrain,
                    Gamepad1.getLeftX(),
                    Gamepad1.getLeftY(),
                    Gamepad1.getRightX()
            );
            myTelemetry.update();
        }
    }

    public void run(DifferentialDrive drivetrain, double xCommand, double yCommand, double turnCommand) {
        // normalize inputs
        double max = Collections.max(Arrays.asList(xCommand, yCommand, turnCommand, 1.0));
        xCommand /= max;
        yCommand /= max;
        turnCommand /= max;

        drivetrain.arcadeDrive(xCommand, yCommand);
    }
}
