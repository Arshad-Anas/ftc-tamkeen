package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.TankDriveTrain;

import java.util.List;


@Autonomous(group = "23925 MainAutonomous", preselectTeleOp = "Auton_blueBlackboard")
public class blueBlackboard extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    Servo yourServo;
    boolean servoPositionIsZero = true;
    TankDriveTrain drive;

    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        drive = new TankDriveTrain(hardwareMap);
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();
        blueBackboard();
    }

    public void blueBackboard() {
        long startTime = System.currentTimeMillis();
        long durationForward = 450; // 2 seconds
        long durationTurn = 1650; // 1 second (adjust as needed)
        long durationForwardAfterTurnBackboard = 650;

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(-1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationTurn) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(0.0, -1.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling

        startTime = System.currentTimeMillis(); // Reset the start time

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForwardAfterTurnBackboard) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(-1.0, 0.0); // Turn right in place (adjust power as needed)
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }
}