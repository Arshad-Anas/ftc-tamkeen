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


@Autonomous(group = "23925 MainAutonomous", preselectTeleOp = "Auton_blueBlackboardStrieght")
public class blueBlackboardStrieght extends LinearOpMode {

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
        blueBackboardStraight();
    }

    public void blueBackboardStraight() {
        long startTime = System.currentTimeMillis();
        long durationForward = 850; // 2 seconds

        while (opModeIsActive() && System.currentTimeMillis() - startTime < durationForward) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            drive.setPower(-1.0, 0.0); // Go forward at full power
            myTelemetry.update();
        }

        drive.setPower(0.0, 0.0); // Stop the robot
        sleep(500); // Add a delay to prevent rapid toggling
    }
}