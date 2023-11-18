package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.TankDriveTrain;

import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class AutonTest1 extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    Servo yourServo;
    boolean servoPositionIsZero = true;
    TankDriveTrain drive;

    @Override
    public void runOpMode() throws InterruptedException {
        hubs = hardwareMap.getAll(LynxModule.class);
        drive = new TankDriveTrain(hardwareMap);
        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();

            // Go forward
            drive.setPower(1.0, 0.0);
            sleep(1000); // Adjust the duration based on your requirements

            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements

            // Go left
            drive.setPower(0.0, -1.0);
            sleep(1300); // Adjust the duration based on your requirements

            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements

            drive.setPower(1.0, 0.0);
            sleep(800); // Adjust the duration based on your requirements

           /* // Stop
            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements

            // Go right
            drive.setPower(0.0, 1.0);
            sleep(1300); // Adjust the duration based on your requirements

            // Stop
            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements

            // Go left
            drive.setPower(0.0, -1.0);
            sleep(1300); // Adjust the duration based on your requirements

            // Stop
            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements

            // Go right again
            drive.setPower(0.0, 1.0);
            sleep(1300); // Adjust the duration based on your requirements

            // Stop
            drive.setPower(0.0, 0.0);
            sleep(500); // Adjust the duration based on your requirements*/

            myTelemetry.update();
        }
    }
}
